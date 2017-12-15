package com.viewstar.jsdatamonitor.blogic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viewstar.jsdatamonitor.persistence.MonitorDBMapper;

@Service
public class MonitorDBBLogic {
	@Autowired
	private MonitorDBMapper monitorDBMapper;

	/**
	 * 查询运行文件
	 * @param beginDateStart
	 * @param beginDateOver
	 * @param endDateStart
	 * @param endDateOver
	 * @return Filename： 文件实例名称, Starttime：开始时间, Endtime： 结束时间,State:状态 ,Updatetime 更新时间 
	 */
	public List<Map<String, Object>> getMonItorDb(String beginDate,String endDate,String tablename,String content,
			Integer beginRow,Integer endRow){
		return monitorDBMapper.getMonItorDb(beginDate,endDate,tablename,content,beginRow,endRow);
	}
	
	/**
	 * 查询运行文件记录数
	 * @param beginDateStart
	 * @param beginDateOver
	 * @param endDateStart
	 * @param endDateOver
	 * @param beginRow
	 * @param endRow
	 * @return Filename： 文件实例名称, Starttime：开始时间, Endtime： 结束时间,State:状态 ,Updatetime 更新时间 
	 */
	public Integer getMonItorDbCount(String beginDate, String endDate,String tablename,String content,
			Integer beginRow,Integer endRow){
		return monitorDBMapper.getMonItorDbCount(beginDate,endDate,tablename,content,beginRow,endRow);
	}

	public Map<String, Object> getOracleDataBySql(String sql) {
		return monitorDBMapper.getOracleDataBySql(sql);
	}
	
	public void insertMonitorDBInfo(String time,String tablename,String content) {
		monitorDBMapper.insertMonitorDBInfo(time,tablename,content);
	}
}
