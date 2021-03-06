package com.viewstar.jsdatamonitor.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.viewstar.jsdatamonitor.blogic.MonitorHadoopBLogic;
import com.viewstar.jsdatamonitor.util.param.DataTableParamUtil;
import com.viewstar.jsdatamonitor.util.param.DataTableReciveParam;
import com.viewstar.jsdatamonitor.util.param.DataTableSendParam;


/**
 * 查询运行文件
 * @author gh
 *
 */
@Controller
public class MonitorHadoopController {
	/**
	 * 查询运行文件BLogic
	 */
	@Resource
	private MonitorHadoopBLogic monitorHadoopBLogic;
	
	private Gson gson = new Gson();
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/**
	 * 进入页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getMonItorHadoop")
	public ModelAndView readSOALabel(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("analysis/getMonItorHadoop");
	}
	/**
	 *进入页面
	 * @param beginDateStart:开始 开始时间
	 * @param beginDateOver 开始 结束时间
	 * @param endDateStart 结束 开始时间
	 * @param endDateOver 结束 结束时间
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getMonItorHadoopInfo")
	public ModelAndView getSoaInterface(@RequestParam("beginDate") String beginDate,@RequestParam("endDate") String endDate,
			@RequestParam("content") String content,
			HttpServletRequest request) throws Exception {
		DataTableReciveParam dataTableReciveParam = DataTableParamUtil.createReciveParam(request);
		Integer beginRow = dataTableReciveParam.getStartRow();
		Integer endRow = dataTableReciveParam.getEndRow();
		//查询记录
		List<Map<String, Object>> list = monitorHadoopBLogic.getMonItorHadoop(beginDate,endDate,content,beginRow,endRow);
		//查询记录数
		Integer count = monitorHadoopBLogic.getMonItorHadoopCount(beginDate,endDate,content,beginRow,endRow);
		DataTableSendParam dataTableSendParam = new DataTableSendParam(count, count, list);
		request.setAttribute("result", gson.toJson(dataTableSendParam));
		return new ModelAndView("ajaxresult");
	}

}
