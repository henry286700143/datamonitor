package com.viewstar.jsdatamonitor.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.viewstar.jsdatamonitor.constants.Constants;
import com.viewstar.jsdatamonitor.util.DateUtil;
import com.viewstar.jsdatamonitor.util.FileUtil;

@Controller
public class ReportInfoController {

	/**
	 * 实现各种单据根据查询条件进行统计的逻辑
	 */
	DateUtil du = new DateUtil();
	
	FileUtil fu = new FileUtil();

	@RequestMapping(value = "getReportInfo")
	public ModelAndView getReportInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String begintime = request.getParameter("begintime");
		String endtime = request.getParameter("endtime");
		String licensePlate = request.getParameter("licensePlate");
		String principal = request.getParameter("principal");
		
		if(!fu.isExist(Constants.PATH)){
			fu.mkdir(Constants.PATH);
		}
		
		List<String> dateList = du.getDayList(begintime, endtime);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < dateList.size(); i++) {
			List<String> childList = fu.getChildFolder(Constants.PATH + dateList.get(i));
			for (int j = 0; j < childList.size(); j++) {
				File file = new File(childList.get(j));
				Map<String,String> map = new HashMap<String,String>();
				
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),"GBK");
		        BufferedReader br = new BufferedReader(read);
		        String temp = "";
		        
		        while((temp = br.readLine()) != null){
	        		int index = temp.indexOf(":");
		        	map.put(temp.substring(0, index), temp.substring(index, temp.length()));
		        }
		        
		        if(map.get("licensePlate").contains(licensePlate) && map.get("principal").contains(principal)){
		        	list.add(map);
	        	}
			}
		}
		
		
		
		request.setAttribute("list", list);
		return new ModelAndView("analysis/getReportInfo");
	}
}
