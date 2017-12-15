package com.viewstar.jsdatamonitor.blogic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viewstar.jsdatamonitor.persistence.MonitorHadoopMapper;

@Service
public class MonitorHadoopBLogic {
	@Autowired
	private MonitorHadoopMapper monitorHadoopMapper;

	/**
	 * 查询运行文件
	 * @param beginDateStart
	 * @param beginDateOver
	 * @param endDateStart
	 * @param endDateOver
	 * @return Filename： 文件实例名称, Starttime：开始时间, Endtime： 结束时间,State:状态 ,Updatetime 更新时间 
	 */
	public List<Map<String, Object>> getMonItorHadoop(String beginDate,String endDate,String content,
			Integer beginRow,Integer endRow){
		return monitorHadoopMapper.getMonItorHadoop(beginDate,endDate,content,beginRow,endRow);
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
	public Integer getMonItorHadoopCount(String beginDate, String endDate,String content,
			Integer beginRow,Integer endRow){
		return monitorHadoopMapper.getMonItorHadoopCount(beginDate,endDate,content,beginRow,endRow);
	}

	public void insertHadoopInfo(String time, String content) {
		 monitorHadoopMapper.insertHadoopInfo(time,content);
	}
	

}
