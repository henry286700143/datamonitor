package com.viewstar.jsdatamonitor.constants;


public class Constants {

//平台登录错误信息存储位置
 public static final String LOGINLOG = "/home/loginLog/";
   
 public static final String [] JSC3ZIPFILENAME = {"XXXX_CATALOG.zip", "XXXX_PAYPROGRAM.zip", "XXXX_PROGRAM.zip", "XXXX_SERIESONE.zip", 
		 "XXXX_SERIES.zip", "XXXX_SUBJECT.zip", "XXXX_VOD.zip", "JSBC_SSSS.zip"};
 
 public static final String [] JSC3LOGFILENAME = {"bestvLiveTV_SSSS.log", "Category_SSSS.log", "cbestvLiveTV_SSSS.log", "Contentviewlog_SSSS.log", 
	 "JsbcCategory_SSSS.log", "JsbcContentviewlog_SSSS.log", "JsbcOrderlog_SSSS.log", "JsbcSchedule_SSSS.log", "JsbcUserinfo_SSSS.log",
	 "Orderlog_SSSS.log", "Schedule_SSSS.log"};
 
 public static final String PATH = "/home/hadoop/processdata/everyday/henrytest/";
 
 public static final String OUTPUTNAME = "hadoopInfo.txt";
 
 public static final String CMDSHNAME = "hadoopreport.sh";
	
 public static final String[] HADOOPFIELD = {"总容量","当前容量","HDFS 剩余","HDFS 被使用","HDFS 被使用占比"};
	
}
