package com.viewstar.jsdatamonitor.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.viewstar.jsdatamonitor.constants.Constants;
import com.viewstar.jsdatamonitor.util.DateUtil;
import com.viewstar.jsdatamonitor.util.FileUtil;
import com.viewstar.jsdatamonitor.util.H2JdbcUtil;

@Controller
public class ReportViewController {
	DateUtil du = new DateUtil();
	
	FileUtil fu = new FileUtil();
	
	H2JdbcUtil h2u = new H2JdbcUtil();
	
	@RequestMapping(value = "addCheckRecord")
	public ModelAndView addCheckRecord(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("reportexport/addCheckRecord");
	}
	@RequestMapping(value = "addCheckRecordResult")
	public ModelAndView getCheckRecordResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String contractId = request.getParameter("contractId");
		String taskId = request.getParameter("taskId");
		String supplyUnit = request.getParameter("supplyUnit");
		String date = request.getParameter("date");
		String projectName = request.getParameter("projectName");
		String projectPosition = request.getParameter("projectPosition");
		String requester = request.getParameter("requester");
		String strength = request.getParameter("strength");
		String impervious = request.getParameter("impervious"); 
		String transportModel = request.getParameter("transportModel");
		String others = request.getParameter("others");
		String supplyNum = request.getParameter("supplyNum");
		String slump = request.getParameter("slump");
		String slumpReal = request.getParameter("slumpReal");
		String ratioId = request.getParameter("ratioId");
		String ratioContent = request.getParameter("ratioContent");
		String distance = request.getParameter("distance");
		String carNum = request.getParameter("carNum");
		String driver = request.getParameter("driver");
		String outboundTime = request.getParameter("outboundTime");
		String turnUpTime = request.getParameter("turnUpTime");
		String temperature = request.getParameter("temperature");
		String beginPourTime = request.getParameter("beginPourTime");
		String endPourTime = request.getParameter("endPourTime");
		String slumpSite = request.getParameter("slumpSite");
		String name1 = request.getParameter("name1");
		String name2 = request.getParameter("name2");
		
		String sql = "INSERT INTO CHECKRECORD VALUES('" + contractId+ "','" + taskId+ "','" + supplyUnit+ "',"
				+ "'" + date+ "','" + projectName+ "','" + projectPosition+ "','" + requester+ "','" + strength+ "',"
				+ "'" + impervious+ "','" + transportModel+ "','" + others+ "','" + supplyNum+ "','" + slump+ "',"
				+ "'" + slumpReal+ "','" + ratioId+ "','" + ratioContent+ "','" + distance+ "','" + carNum+ "',"
				+ "'" + driver+ "','" + outboundTime+ "','" + turnUpTime+ "','" + temperature+ "','" + beginPourTime+ "',"
				+ "'" + endPourTime+ "','" + slumpSite+ "','" + name1+ "','" + name2+ "',CURRENT_TIMESTAMP)";
		int flag = h2u.executeUpdate(sql);
		
		if(flag != 0){
			request.setAttribute("result", "success");
		}else{
			request.setAttribute("result", "failure");
		}
		
		return new ModelAndView("ajaxresult");
	}
	
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
