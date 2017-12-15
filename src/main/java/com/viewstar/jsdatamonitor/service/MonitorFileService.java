package com.viewstar.jsdatamonitor.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viewstar.jsdatamonitor.blogic.MonitorFileBLogic;
import com.viewstar.jsdatamonitor.constants.Constants;
import com.viewstar.jsdatamonitor.util.CmdUtil;

@Service
public class MonitorFileService {

	public CmdUtil cmdUtil = new CmdUtil(Constants.PATH);

	public static String c3LogPath = "/home/software/c3log/";

	public SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	@Resource
	private MonitorFileBLogic monitorFileBLogic;

	// ClassPathXmlApplicationContext bean = new
	// ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
	//
	// private MonitorFileBLogic monitorFileBLogic = (MonitorFileBLogic)
	// bean.getBean("monitorFileBLogic");

	// run c3log filer
	public void runC3Log(String date,String type) throws Exception {
		
		System.out.println("sh /home/hadoop/processdata/everyday/henrytest/jshadoopjareveryday.sh " + date + " " + date + " " + type);
		
		cmdUtil.cmd("sh /home/hadoop/processdata/everyday/henrytest/jshadoopjareveryday.sh " + date + " " + date + " " + type);
	}
	
	public void runC3Log(String date) throws Exception {
		System.out.println("sh "+ Constants.PATH + "jshadoopjareveryday.sh " + date);
		
		cmdUtil.cmd("sh "+ Constants.PATH + "jshadoopjareveryday.sh " + date);
	}

	// delete c3log filer
	public void deleteC3Log(String date) throws Exception {
		cmdUtil.cmd("rm -rf " + c3LogPath + date);
	}

	// run jsupload.sh
	public void transformC3Log(String begindate, String enddate)
			throws Exception {
		cmdUtil.cmd("sh " + Constants.PATH + "newjsupload.sh " + begindate + " "
				+ enddate);
	}

	// check c3log
	public int checkC3Log(String date) throws Exception {
		// 对比zip文件数目，大小
		Map<String, Long> map = getZipFiles(c3LogPath + date);

		String[] zipname = Constants.JSC3ZIPFILENAME;

		int flag = 0;

		flag = checkFileNums("播控", map, zipname, date);

		if (flag == 1) {
			return flag;
		}

		// 对比JSBC_中的文件数据，相应大小
		Map<String, Long> jsbcmap = getZipFiles(c3LogPath + date + "/JSBC_"
				+ date);

		String[] logname = Constants.JSC3LOGFILENAME;

		flag = checkFileNums("收视", jsbcmap, logname, date);

		return flag;
	}

	private int checkFileNums(String name, Map<String, Long> map,
			String[] zipname, String date) throws Exception {
//		DateUtil dateutil = new DateUtil();
//		8月4日开始切换到新播控ftp，启用新规则，文件名变为当天日期
//		String nextday = dateutil.addDay(date, "yyyyMMdd", 1);

		int flat = 0;
		String log = "";
		String[] tempzipname = new String[zipname.length];
		for (int i = 0; i < zipname.length; i++) {
			tempzipname[i] = zipname[i].replaceAll("XXXX", date).replaceAll(
					"SSSS", date);

			if (map.containsKey(tempzipname[i])) {
				Long length = map.get(tempzipname[i]);
				if (length < 208) {
					flat = 1;
					log = log + tempzipname[i] + " 文件过小;";
				}
			} else {
				flat = 1;
				log = log + "缺少 " + tempzipname[i] + " 文件;";
			}
		}

		if (flat == 0) {
			monitorFileBLogic.insertMonitorFileInfo(date, name + "文件正常;");
//			System.out.println(date + " " + name + "文件正常;");
		} else {
			monitorFileBLogic.insertMonitorFileInfo(date, log);
//			System.out.println(date + " " + name + log);
		}
		return flat;
	}

	private Map<String, Long> getZipFiles(String path) throws Exception {

		Map<String, Long> map = new HashMap<String, Long>();

		File f = new File(path);

		if (!f.exists()) {
			return null;
		}
		File[] temp = f.listFiles();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].exists() && temp[i].isFile()) {
				map.put(temp[i].getName(), temp[i].length());
			}
		}
		return map;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MonitorFileService fileService = new MonitorFileService();
//		fileService.checkC3Log("20170123");
		fileService.runC3Log("20170123", "1");
	}
}
