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
	 * ��ѯ�����ļ�
	 * @param beginDateStart
	 * @param beginDateOver
	 * @param endDateStart
	 * @param endDateOver
	 * @return Filename�� �ļ�ʵ������, Starttime����ʼʱ��, Endtime�� ����ʱ��,State:״̬ ,Updatetime ����ʱ�� 
	 */
	public List<Map<String, Object>> getMonItorFile(String beginDate,String endDate,String content,
			Integer beginRow,Integer endRow){
		return monitorFileMapper.getMonItorFile(beginDate,endDate,content,beginRow,endRow);
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
	public Integer getMonItorFileCount(String beginDate, String endDate,String content,
			Integer beginRow,Integer endRow){
		return monitorFileMapper.getMonItorFileCount(beginDate,endDate,content,beginRow,endRow);
	}
	
	public void insertMonitorFileInfo(String time, String content) {
		monitorFileMapper.insertMonitorFileInfo(time, content);
	}
}
