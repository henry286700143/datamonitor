package com.viewstar.jsdatamonitor.persistence;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;



/**
 * ��ѯ�����ļ�
 * @author gh
 *
 */
public interface MonitorHadoopMapper {
	/**
	 * ��ѯ�����ļ�
	 * @param beginDateStart
	 * @param beginDateOver
	 * @param endDateStart
	 * @param endDateOver
	 * @return Filename�� �ļ�ʵ������, Starttime����ʼʱ��, Endtime�� ����ʱ��,State:״̬ ,Updatetime ����ʱ�� 
	 */
	public List<Map<String, Object>> getMonItorHadoop(@Param(value="beginDate")String beginDate,
			@Param(value="endDate")String endDate,
			@Param(value="content")String content,
			@Param(value="beginRow")Integer beginRow,
			@Param(value="endRow")Integer endRow);

	/**
	 * ��ѯ�����ļ���¼��
	 * @param beginDateStart
	 * @param beginDateOver
	 * @param endDateStart
	 * @param beginRow
	 * @param endRow
	 * @return Filename�� �ļ�ʵ������, Starttime����ʼʱ��, Endtime�� ����ʱ��,State:״̬ ,Updatetime ����ʱ�� 
	 */
	public Integer getMonItorHadoopCount(@Param(value="beginDate")String beginDate,
			@Param(value="endDate")String endDate,
			@Param(value="content")String content,
			@Param(value="beginRow")Integer beginRow,
			@Param(value="endRow")Integer endRow);

	public void insertHadoopInfo(@Param(value="time")String time,
			@Param(value="content")String content);
}
