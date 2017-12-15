package com.viewstar.jsdatamonitor.persistence;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;



/**
 * 查询运行文件
 * @author gh
 *
 */
public interface MonitorDBMapper {
	/**
	 * 查询运行文件
	 * @param beginDateStart
	 * @param beginDateOver
	 * @param endDateStart
	 * @param endDateOver
	 * @return Filename： 文件实例名称, Starttime：开始时间, Endtime： 结束时间,State:状态 ,Updatetime 更新时间 
	 */
	public List<Map<String, Object>> getMonItorDb(@Param(value="beginDate")String beginDate,
			@Param(value="endDate")String endDate,
			@Param(value="tablename")String tablename,
			@Param(value="content")String content,
			@Param(value="beginRow")Integer beginRow,
			@Param(value="endRow")Integer endRow);

	/**
	 * 查询运行文件记录数
	 * @param beginDateStart
	 * @param beginDateOver
	 * @param endDateStart
	 * @param beginRow
	 * @param endRow
	 * @return Filename： 文件实例名称, Starttime：开始时间, Endtime： 结束时间,State:状态 ,Updatetime 更新时间 
	 */
	public Integer getMonItorDbCount(@Param(value="beginDate")String beginDate,
			@Param(value="endDate")String endDate,
			@Param(value="tablename")String tablename,
			@Param(value="content")String content,
			@Param(value="beginRow")Integer beginRow,
			@Param(value="endRow")Integer endRow);

	public Map<String, Object> getOracleDataBySql(@Param(value="sql")String sql);

	public void insertMonitorDBInfo(@Param(value="time")String time,
			@Param(value="tablename")String tablename,
			@Param(value="content")String content);
}
