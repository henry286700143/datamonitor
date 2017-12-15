package com.viewstar.jsdatamonitor.timertask;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viewstar.jsdatamonitor.service.MonitorDBService;
import com.viewstar.jsdatamonitor.service.MonitorHadoopService;
import com.viewstar.jsdatamonitor.util.DateUtil;

@Service
public class DataMonitorTimeTask {
	
	@Resource
	private MonitorDBService monitorDBService;
	@Resource
	private MonitorHadoopService monitorHadoopService;
	
	
	public void exec() throws Exception {
		System.out.println("DataMonitorTimeTask");

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sf.format(new Date());

		DateUtil du = new DateUtil();
		
		String pretime = du.addDay(time, "yyyy-MM-dd", -1);
		
		System.out.println(time + "开始处理数据: " + pretime);

		monitorHadoopService.shHadoopReport(pretime);
		
		monitorDBService.checkOracleData(pretime);

		System.out.println(time + "结束处理数据: " + pretime);

		System.out.println("----------------------------------");

	}
}
