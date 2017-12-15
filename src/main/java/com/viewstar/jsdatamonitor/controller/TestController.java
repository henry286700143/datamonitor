package com.viewstar.jsdatamonitor.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.viewstar.jsdatamonitor.constants.Constants;
import com.viewstar.jsdatamonitor.service.MonitorFileService;
import com.viewstar.jsdatamonitor.util.CmdUtil;
import com.viewstar.jsdatamonitor.util.DateUtil;

@Controller
public class TestController {

	@Resource
	MonitorFileService fileService;
	
	@RequestMapping(value = "runExecVod")
	public ModelAndView runExecVod(@RequestParam("begintime") String begintime) throws Exception {
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CmdUtil cmdUtil = new CmdUtil(Constants.PATH);
		
		System.out.println("begin:"+sf2.format(new Date()));
//		System.out.println("sh "+ Constants.PATH + "jshadoopjareveryday.sh " + begintime + " " + begintime);
//		cmdUtil.cmd("sh "+ Constants.PATH + "jshadoopjareveryday.sh " + begintime + " " + begintime);
		System.out.println("sh "+ Constants.PATH + "jshadoopjareveryday.sh " + begintime);
		cmdUtil.cmd("sh "+ Constants.PATH + "jshadoopjareveryday.sh " + begintime);
		System.out.println("end:"+sf2.format(new Date()));
		return null;
	}
	
	@RequestMapping(value = "runExecLog")
	public ModelAndView runExecLog(@RequestParam("begintime") String begintime,@RequestParam("endtime") String endtime) throws Exception {
		System.out.println("DataETLTimeTask:"+begintime+"   "+endtime);
	
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		DateUtil du = new DateUtil();
		
		List<String> list = du.getDayList(begintime, endtime,"yyyyMMdd");
		System.out.println("list:"+list);
		for (int i = 0; i < list.size(); i++) {
			String time = list.get(i);
			
			String pretime = du.addDay(time, "yyyyMMdd", -1);
			String beforepretime = du.addDay(time, "yyyyMMdd", -2);
			
			System.out.println(sf2.format(new Date()) + "开始运行前天数据: " + beforepretime);
		
			int flag = processbeforepre(beforepretime, pretime);
		
			System.out.println(sf2.format(new Date()) + "结束运行前天数据: " + beforepretime);
		
			System.out.println("----------------------------------");
			
			if(flag == 0){
				
		//		System.out.println(sf2.format(new Date()) + "begin waitting 3 hours------------------------------");
				
		//		Thread.currentThread().sleep(1000*60*60*2);
				
				System.out.println(sf2.format(new Date()) + "开始运行昨天数据: " + pretime);
				
				processpre(pretime);
				
				System.out.println(sf2.format(new Date()) + "结束运行昨天数据: " + pretime);
				
			}
			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
		return null;
	}
	
	public int processbeforepre(String beforepretime,String pretime) throws Exception {

		fileService.deleteC3Log(pretime);

		fileService.transformC3Log(pretime, pretime);

		if (fileService.checkC3Log(pretime) != 0) {
			System.out.println(pretime+" 日志有问题，停止运行");
			return 1;
		}else {
			// run
			fileService.runC3Log(beforepretime);
			System.out.println(beforepretime+" 日志 数据运行完毕！");
			return 0;
		}
	}
	
	public void processpre(String time) throws Exception {
		// run
		fileService.runC3Log(time);
		System.out.println(time+" 日志 数据运行完毕！");
	}
}
