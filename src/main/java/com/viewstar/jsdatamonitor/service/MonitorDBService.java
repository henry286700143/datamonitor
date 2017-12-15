package com.viewstar.jsdatamonitor.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viewstar.jsdatamonitor.blogic.MonitorDBBLogic;
import com.viewstar.jsdatamonitor.util.DateUtil;
import com.viewstar.jsdatamonitor.util.FileUtil;

@Service
public class MonitorDBService {

//	ClassPathXmlApplicationContext bean = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
//	
//	private MonitorDBBLogic monitorDBBLogic = (MonitorDBBLogic) bean.getBean("monitorDBBLogic");
	
	public SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	
	public DecimalFormat decimalFormat = new DecimalFormat("##0.00");
	
	private String date;
	
	private String predate;
	
	@Resource
	private MonitorDBBLogic monitorDBBLogic;

	//遍历tablemap
	public int checkOracleData(String date) throws Exception{
		DateUtil du = new DateUtil();
		this.date = date;
		this.predate = du.addDay(date, "yyyy-MM-dd", -1);
		HashMap<String, String> tableMap = new HashMap<String, String>();
		
		try {
			String path = this.getClass().getResource("/").getPath();

			String readFile = new FileUtil().readFile(path + "oracleCheckSql.txt");
			
			String[] num = readFile.split("\n");

			for (int i = 0; i < num.length; i++) {
				tableMap.put(num[i].split("\t")[0], num[i].split("\t")[1]);
			}
			
		} catch (Exception e) {
			tableMap.put("", "");
		}
		
		Map<String,Object> map;
		Map<String,Object> premap;
		int flag = 0;
		
		Iterator<Entry<String, String>> it = tableMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> next = it.next();
			String tablename = next.getKey();
			String sql = next.getValue();
			
			map = getOracleData(sql, date);
			premap = getOracleData(sql, predate);
			
			flag += compareOracleData(map, premap, tablename);
		}
		return flag;
	}
	
	//比较oracle数据
	private int compareOracleData(Map<String,Object> map, Map<String,Object> premap, String tablename){
		
		if(map.size() == 0){
			monitorDBBLogic.insertMonitorDBInfo(date, tablename, "数据为空;");
			return 1;
		}
		if(premap.size() == 0){
			monitorDBBLogic.insertMonitorDBInfo(date, tablename, "前一天数据为空;");
			return 2;
		}
		
		int flag = 0;
		Iterator<Entry<String, Object>> it = premap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> next = it.next();
			String field = next.getKey();
			BigDecimal prevalue = (BigDecimal)next.getValue();
			
			BigDecimal value = (BigDecimal)(map.get(field) == null?0:map.get(field));
			
			flag += transformValue(value.doubleValue(),prevalue.doubleValue(),field,tablename);
		}
		return flag;
	}
	
	//insert
	private int transformValue(double value, double prevalue,String field,String tablename) {
		int flag = 0;
		if(value == prevalue){
			monitorDBBLogic.insertMonitorDBInfo(date, tablename, "数据与前一天相同，异常;");
			flag++;
		}else if(value <= (prevalue*0.5)){
			monitorDBBLogic.insertMonitorDBInfo(date, tablename, field+"字段比前一天数据少一倍，异常;");
			flag++;
		}else if(value >= (prevalue*1.5)){
			monitorDBBLogic.insertMonitorDBInfo(date, tablename, field+"字段比前一天数据多一倍，异常;");
			flag++;
		}else{
			monitorDBBLogic.insertMonitorDBInfo(date, tablename, field+"字段正常，值为"+decimalFormat.format(value)+";");
		}
		return flag;
	}

	//查询oracle数据
	private Map<String,Object> getOracleData(String sql, String date){
		Map<String,Object> map = monitorDBBLogic.getOracleDataBySql(sql.replaceAll("\\$\\{time\\}", date));
		if(map == null){
			return map = new HashMap<String,Object>();
		}
		return map;
	}
	
	public static void main(String[] args) throws Exception {
		MonitorDBService ms = new MonitorDBService();
		
		ms.checkOracleData("2016-05-14");
		
	}
}
