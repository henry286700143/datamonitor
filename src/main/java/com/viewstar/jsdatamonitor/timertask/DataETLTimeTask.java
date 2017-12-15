package com.viewstar.jsdatamonitor.timertask;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viewstar.jsdatamonitor.service.MonitorFileService;
import com.viewstar.jsdatamonitor.util.DateUtil;

@Service
public class DataETLTimeTask {

	private String yyyyMMdd = "yyyyMMdd";
	
	@Resource
	MonitorFileService fileService;
	
	public void exec() throws Exception{
		System.out.println("DataETLTimeTask");

		SimpleDateFormat sf = new SimpleDateFormat(yyyyMMdd);
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String date = sf.format(new Date());
		
		DateUtil du = new DateUtil();
		
		String pretime = du.addDay(date, yyyyMMdd, -1);
		String beforepretime = du.addDay(date, yyyyMMdd, -2);
		
		System.out.println(sf2.format(new Date()) + "开始运行前天数据: " + beforepretime);

		int flag = processbeforepre(beforepretime, pretime);
		
		System.out.println(sf2.format(new Date()) + "结束运行前天数据: " + beforepretime);

		System.out.println("----------------------------------");
		
		if(flag == 0){
			
//			System.out.println(sf2.format(new Date()) + "begin waitting 3 hours------------------------------");
			
//			Thread.currentThread().sleep(1000*60*60*2);
			
			System.out.println(sf2.format(new Date()) + "开始运行昨天数据: " + pretime);
			
			processpre(pretime);
			
			System.out.println(sf2.format(new Date()) + "结束运行昨天数据: " + pretime);
			
		}
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
