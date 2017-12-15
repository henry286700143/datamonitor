package com.viewstar.jsdatamonitor.blogic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viewstar.jsdatamonitor.persistence.MonitorFileMapper;

@Service
public class MonitorFileBLogic {
	@Autowired
	private MonitorFileMapper monitorFileMapper;

	/**
	 * 查询运行文件
	 * @param beginDateStart
	 * @param beginDateOver
	 * @param endDateStart
	 * @param endDateOver
	 * @return Filename： 文件实例名称, Starttime：开始时间, Endtime： 结束时间,State:状态 ,Updatetime 更新时间 
	 */
	public List<Map<String, Object>> getMonItorFile(String beginDate,String endDate,String content,
			Integer beginRow,Integer endRow){
		return monitorFileMapper.getMonItorFile(beginDate,endDate,content,beginRow,endRow);
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
	public Integer getMonItorFileCount(String beginDate, String endDate,String content,
			Integer beginRow,Integer endRow){
		return monitorFileMapper.getMonItorFileCount(beginDate,endDate,content,beginRow,endRow);
	}
	
	public void insertMonitorFileInfo(String time, String content) {
		monitorFileMapper.insertMonitorFileInfo(time, content);
	}
}
