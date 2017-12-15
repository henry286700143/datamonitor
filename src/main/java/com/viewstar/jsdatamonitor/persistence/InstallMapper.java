package com.viewstar.jsdatamonitor.persistence;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * °²×°³ÌÐò
 * @author liwei
 *
 */
public interface InstallMapper {

	public void installScript(Hashtable<String,String> param);

	public List<Map<String, Object>> installexecuteSQL(Hashtable<String, String> param);

	public void InsertServiceDataBak(Hashtable<String, String> param);

	public List<Map<String, Object>> getServiceDataBak();

	public void deleteFileBakState(Hashtable<String, String> param);
}
