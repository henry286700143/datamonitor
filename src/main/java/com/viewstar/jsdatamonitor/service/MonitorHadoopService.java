package com.viewstar.jsdatamonitor.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viewstar.jsdatamonitor.blogic.MonitorHadoopBLogic;
import com.viewstar.jsdatamonitor.constants.Constants;
import com.viewstar.jsdatamonitor.util.CmdUtil;

@Service
public class MonitorHadoopService {

//	ClassPathXmlApplicationContext bean = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
//
//	private MonitorHadoopBLogic monitorHadoopBLogic = (MonitorHadoopBLogic) bean.getBean("monitorHadoopBLogic");

	public CmdUtil cmdUtil = new CmdUtil(Constants.PATH);
	
	@Resource
	private MonitorHadoopBLogic monitorHadoopBLogic;

	// 删除文件
	public void delOutPutFile() throws Exception {
		cmdUtil.cmd("rm -rf " + Constants.PATH + Constants.OUTPUTNAME);
	}
	
	public int shHadoopReport(String time) throws Exception {

		List<String> list = cmdUtil.cmdInFile(Constants.PATH + Constants.CMDSHNAME, Constants.PATH + Constants.OUTPUTNAME);
		String content = "";
		if(list == null){
			return 1;
		}else{
			for (int i = 0; i < 4; i++) {
				String temp = list.get(i);
				
				content = content + Constants.HADOOPFIELD[i] + ":" + temp.substring(temp.indexOf("(")+1, temp.indexOf(")")) + ";";
			}
			
			content = content + Constants.HADOOPFIELD[4] + ":" + list.get(4).substring(list.get(4).indexOf(": ")+1, list.get(4).lastIndexOf("%")+1) + ";";
			
			monitorHadoopBLogic.insertHadoopInfo(time, content);
			
			return 0;
		}
	}
	
	public static void main(String[] args) throws Exception {
		MonitorHadoopService ms =new MonitorHadoopService();
		ms.shHadoopReport("2017-01-01");
	}
	
}
