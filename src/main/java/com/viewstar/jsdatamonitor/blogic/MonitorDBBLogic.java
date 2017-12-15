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
	 * ��ѯ�����ļ�
	 * @param beginDateStart
	 * @param beginDateOver
	 * @param endDateStart
	 * @param endDateOver
	 * @return Filename�� �ļ�ʵ������, Starttime����ʼʱ��, Endtime�� ����ʱ��,State:״̬ ,Updatetime ����ʱ�� 
	 */
	public List<Map<String, Object>> getMonItorDb(String beginDate,String endDate,String tablename,String content,
			Integer beginRow,Integer endRow){
		return monitorDBMapper.getMonItorDb(beginDate,endDate,tablename,content,beginRow,endRow);
	}
	
	/**
	 * ��ѯ�����ļ���¼��
	 * @param beginDateStart
	 * @param beginDateOver
	 * @param endDateStart
	 * @param endDateOver
	 * @param beginRow
	 * @param endRow
	 * @return Filename�� �ļ�ʵ������, Starttime����ʼʱ��, Endtime�� ����ʱ��,State:״̬ ,Updatetime ����ʱ�� 
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
