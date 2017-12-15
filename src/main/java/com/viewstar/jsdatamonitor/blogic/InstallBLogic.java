package com.viewstar.jsdatamonitor.blogic;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viewstar.jsdatamonitor.persistence.InstallMapper;

@Service
public class InstallBLogic {
	@Autowired
	private InstallMapper installMapper;

	public void installScript(String script) {
		Hashtable<String,String> param = new Hashtable<String,String>();
		if(script != null){
			param.put("script", script);
		}
		installMapper.installScript(param);
	}

	/**
	 * ִ��SQL��ѯ
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> installexecuteSQL(String sql) {
		Hashtable<String,String> param = new Hashtable<String,String>();
		if(sql != null){
			param.put("sql", sql);
		}
		return installMapper.installexecuteSQL(param);
	}

	/**
	 * ������ݱ���״̬.
	 * @param bakTime
	 * @param fileName
	 */
	public void InsertServiceDataBak(String bakTime, String fileName) {
		Hashtable<String,String> param = new Hashtable<String,String>();
		if(bakTime != null){
			param.put("bakTime", bakTime);
		}

		if(fileName != null){
			param.put("fileName", fileName);
		}
		installMapper.InsertServiceDataBak(param);
	}

	/**
	 * ���ݱ���״̬.
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> getServiceDataBak() {

		return installMapper.getServiceDataBak();
	}

	public void deleteFileBakState (String fileName) {
		Hashtable<String,String> param = new Hashtable<String,String>();

		if(fileName != null){
			param.put("fileName", fileName);
		}
		installMapper.deleteFileBakState(param);
	}


}
