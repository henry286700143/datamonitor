package com.viewstar.jsdatamonitor.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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
public class ReportViewController {
	DateUtil du = new DateUtil();
	
	FileUtil fu = new FileUtil();
	/**
	 * 实现各种单据在浏览器中展示，提交，保存的逻辑
	 */
	@RequestMapping(value = "addReport")
	public ModelAndView addReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String address = request.getParameter("address");
		String time = request.getParameter("time");
		String licensePlate = request.getParameter("licensePlate");
		String principal = request.getParameter("principal");
		
		String curday = du.getCurDayOnCurMonth();
		if(!fu.isExist(Constants.PATH)){
			fu.mkdir(Constants.PATH);
		}
		
		if(!fu.isExist(Constants.PATH + curday)){
			fu.mkdir(Constants.PATH + curday);
		}
		
		StringBuffer programBuffer = new StringBuffer("");
		
		programBuffer.append("地址:").append(address).append("\n");
		programBuffer.append("时间:").append(time).append("\n");
		programBuffer.append("车票:").append(licensePlate).append("\n");
		programBuffer.append("负责人:").append(principal).append("\n");
		
		fu.saveAppendFile(programBuffer.toString(), Constants.PATH + curday + "/" + curday + "_" + principal + ".csv", "GBK");

		programBuffer.setLength(0);
		
		request.setAttribute("result", "success");
		return new ModelAndView("ajaxresult");
	}
	
	@RequestMapping(value = "deleteReport")
	public ModelAndView deleteReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String time = request.getParameter("time");
		String principal = request.getParameter("principal");
		
		if(fu.isExist(Constants.PATH + time + "/" + time + "_" + principal + ".csv")){
			fu.delFile(Constants.PATH + time + "/" + time + "_" + principal + ".csv");
		}
		
		request.setAttribute("result", "success");
		return new ModelAndView("ajaxresult");
	}
	
	@RequestMapping(value = "modifyReport")
	public ModelAndView modifyReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String address = request.getParameter("address");
		String time = request.getParameter("time");
		String licensePlate = request.getParameter("licensePlate");
		String principal = request.getParameter("principal");
		
		String curday = du.getCurDayOnCurMonth();
		if(!fu.isExist(Constants.PATH)){
			fu.mkdir(Constants.PATH);
		}
		
		if(!fu.isExist(Constants.PATH + curday)){
			fu.mkdir(Constants.PATH + curday);
		}
		
		StringBuffer programBuffer = new StringBuffer("");
		
		programBuffer.append("地址:").append(address).append("\n");
		programBuffer.append("时间:").append(time).append("\n");
		programBuffer.append("车票:").append(licensePlate).append("\n");
		programBuffer.append("负责人:").append(principal).append("\n");
		
		fu.delFile(Constants.PATH + curday + "/" + curday + "_" + principal + ".csv");
		
		fu.saveAppendFile(programBuffer.toString(), Constants.PATH + curday + "/" + curday + "_" + principal + ".csv", "GBK");

		programBuffer.setLength(0);
		
		request.setAttribute("result", "success");
		return new ModelAndView("ajaxresult");
	}
	
	@RequestMapping(value = "getReport")
	public ModelAndView getReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filename = request.getParameter("filename");
		
		File file = new File(filename);
		Map<String,String> map = new HashMap<String,String>();
		
		InputStreamReader read = new InputStreamReader(new FileInputStream(file),"GBK");
        BufferedReader br = new BufferedReader(read);
        String temp = "";
        
        while((temp = br.readLine()) != null){
        	int index = temp.indexOf(":");
        	map.put(temp.substring(0, index), temp.substring(index, temp.length()));
        }
		
		request.setAttribute("map", map);
		return new ModelAndView("analysis/getReport");
	}
	
	@RequestMapping(value = "getReportList")
	public ModelAndView getReportList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String time = request.getParameter("time");
		
		if(!fu.isExist(Constants.PATH)){
			fu.mkdir(Constants.PATH);
		}
		
		List<String> list = fu.getChildFolder(Constants.PATH + time);
		request.setAttribute("list", list);
		return new ModelAndView("analysis/getReportList");
	}
}
