package com.viewstar.jsdatamonitor.controller;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.viewstar.jsdatamonitor.constants.Constants;
import com.viewstar.jsdatamonitor.util.CmdUtil;
import com.viewstar.jsdatamonitor.util.DateUtil;
import com.viewstar.jsdatamonitor.util.FileUtil;
import com.viewstar.jsdatamonitor.util.ListUtil;
import com.viewstar.jsdatamonitor.util.param.DataTableParamUtil;
import com.viewstar.jsdatamonitor.util.param.DataTableReciveParam;
import com.viewstar.jsdatamonitor.util.param.DataTableSendParam;

@Controller
public class MonItorRunController {
	
	public CmdUtil cmdUtil = new CmdUtil(Constants.PATH);
	
	public DateUtil dateUtil = new DateUtil();
		
	public FileUtil fileUtil = new FileUtil();
	
	public ListUtil listUtil = new ListUtil();
	
	private Gson gson = new Gson();
	
	/**
	 * 进入页面 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getMonItorRun")
	public ModelAndView getMonItorRun(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("analysis/getMonItorRun");
	}
	
	/**
	 * 开始运行
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "MonItorRun")
	public ModelAndView MonItorRunInfo(@RequestParam("beginDate") String beginDate, 
			@RequestParam("endDate") String endDate) throws Exception {
//		try {
//			Thread.sleep(60000);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		// 执行Shell命令
		if (!"".equals(beginDate) && !"".equals(endDate)) {
			List<String> getDayList = dateUtil.getDayList(beginDate, endDate);
			for (String date : getDayList) {
				System.out.println("开始运行" + date + "数据");
				cmdUtil.cmd("sh "+ Constants.PATH + "jshadoopjareveryday.sh " + dateUtil.formatDate(date, "yyyy-MM-dd", "yyyyMMdd"));
				System.out.println("结束运行" + date + "数据");
			}
		}
		return null;
	}
	
	/**
	 * 运行结果
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getMonItorRunInfo")
	public ModelAndView getMonItorRunInfo(HttpServletRequest request) throws Exception {
		// 读取run.log
		String content = fileUtil.readFileNotN(Constants.PATH + "/run.log");
		
		String temp = "";
		// 开始时间Map
		Map<String, String> startMap = new HashMap<String, String>();
		// 结束时间Map
		Map<String, String> finishMap = new HashMap<String, String>();
		// 处理run.log
		BufferedReader bufferedReader = fileUtil.getBufferedReader(Constants.PATH + "/run.log", "UTF-8");
		while ((temp = bufferedReader.readLine()) != null) {
			String key = temp.substring(temp.indexOf("江苏数据处理 ") + 7, temp.indexOf("江苏数据处理 ") + 15);
			String value = temp.substring(0, temp.indexOf("-----------"));
			if (temp.contains("start")) {
				startMap.put(key, value);
			} else if (temp.contains("finish")) {
				finishMap.put(key, value);
			}
		}
		
		// 获取子文件名
		List<String> childFileName = fileUtil.getChildFileName(Constants.PATH);
		
		List<Map> list = new ArrayList<Map>();
		for (String fileName : childFileName) {
			// 处理状态
			String state = "";
			// 开始时间
			String startTime = "";
			// 结束时间
			String finishTime = "";
			// 秒
			String seconds = "";
			// 处理时长
			String timeLength = "";
			
			// log文件
			if (fileName.startsWith("all_") && fileName.endsWith(".log")) {
				Map<String, Object> map = new HashMap<String, Object>();
				// date
				String date = fileName.replace("all_", "").replace(".log", ""); 
				if (!content.contains(date + " start")) {
					state = "等待处理";
				} else if (content.contains(date + " start") && content.contains(date + " finish")) {
					startTime = startMap.get(date);
					finishTime = finishMap.get(date);
					seconds = String.valueOf(dateUtil.getTimeLength(startTime, finishTime, "yyyy-MM-dd HH:mm:ss"));
					// 重跑数据
					if (Long.parseLong(seconds) < 0) {
						finishTime = "";
						seconds = String.valueOf(dateUtil.getTimeLength(startTime, dateUtil.getCurDayTime(), "yyyy-MM-dd HH:mm:ss"));
						if (Long.parseLong(seconds) > 18000) {
							state = "处理失败";
							timeLength = dateUtil.Second2Hour(seconds).replaceFirst(":", "小时").replace(":", "分") + "秒";
						} else {
							state = "正在处理";
							timeLength = "";
						}
					} else if (Long.parseLong(seconds) < 1800) {
						state = "处理失败";
						timeLength = dateUtil.Second2Hour(seconds).replaceFirst(":", "小时").replace(":", "分") + "秒";
					} else {
						state = "处理成功";
						timeLength = dateUtil.Second2Hour(seconds).replaceFirst(":", "小时").replace(":", "分") + "秒";
					}
				} else if (content.contains(date + " start") && !content.contains(date + " finish")) {
					startTime = startMap.get(date);
					seconds = String.valueOf(dateUtil.getTimeLength(startTime, dateUtil.getCurDayTime(), "yyyy-MM-dd HH:mm:ss"));
					if (Long.parseLong(seconds) > 18000) {
						state = "处理失败";
						timeLength = dateUtil.Second2Hour(seconds).replaceFirst(":", "小时").replace(":", "分") + "秒";
					} else {
						state = "正在处理";
						timeLength = "";
					}
				} else {
					state = "处理失败";
				}
				
				// 文件名
				map.put("fileName", fileName);
				// 处理状态
				map.put("state", state);
				// 开始时间
				map.put("startTime", startTime);
				// 结束时间
				map.put("finishTime", finishTime);
				// 处理时长
				map.put("timeLength", timeLength);
				// date
				map.put("date", date);
				list.add(map);
			}
		}
		
		// list排序
		list = listUtil.SortList(list, "date", "DESC", 0, list.size());
		
		// 分页
		Integer count = list.size();
		DataTableReciveParam dataTableReciveParam = DataTableParamUtil.createReciveParam(request);
		Integer beginRow = dataTableReciveParam.getStartRow();
		Integer endRow =  dataTableReciveParam.getEndRow();
		List<Map> resultList = new ArrayList<Map>();
		for (int i = 0; i < list.size(); i++) {
			if (i >= beginRow - 1 && i <= endRow - 1) {
				resultList.add(list.get(i));
			}
		}
		DataTableSendParam dataTableSendParam = new DataTableSendParam(count, count, resultList);
		request.setAttribute("result", gson.toJson(dataTableSendParam));
		return new ModelAndView("ajaxresult");
	}
	
}
