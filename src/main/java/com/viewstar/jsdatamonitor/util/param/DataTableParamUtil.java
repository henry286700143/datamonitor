package com.viewstar.jsdatamonitor.util.param;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
/**
 * DataTable的参数辅助类
 * @author liwei
 *
 */
public class DataTableParamUtil {
	/**
	 * 下标的正则表达式
	 */
	public static Pattern indexPattern = Pattern.compile("\\[(\\d)+\\]");
	/**
	 * 类型的正则表达式
	 */
	public static Pattern fieldPattern = Pattern.compile("\\]\\[(\\w|\\W)+\\]");

	public static DataTableReciveParam createReciveParam(HttpServletRequest request) {
		Integer startRow = Integer.parseInt(request.getParameter("start")) + 1;
		Integer endRow = startRow + Integer.parseInt(request.getParameter("length")) - 1;
		DataTableReciveParam dataTableReciveParam = new DataTableReciveParam();
		dataTableReciveParam.setStartRow(startRow);
		dataTableReciveParam.setEndRow(endRow);
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		Iterator<Entry<String, String[]>> it = request.getParameterMap().entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			if (entry.getKey().contains("columns")) {
				String key = entry.getKey();
				String value = request.getParameter(key);
				Matcher matcher = indexPattern.matcher(key);
				matcher.find();
				String index = matcher.group().replace("[", "").replace("]", "");
				matcher = fieldPattern.matcher(key);
				matcher.find();
				String field = matcher.group().replace("[", "").replace("]", "");
				if (!map.containsKey(index)) {
					Map<String, String> temp = new HashMap<String, String>();
					temp.put(field, value);
					map.put(index, temp);
				} else {
					Map<String, String> temp = map.get(index);
					temp.put(field, value);
					map.put(index, temp);
				}
			}
		}
		Iterator<Entry<String, Map<String, String>>> fieldIt = map.entrySet().iterator();
		Map<String, String> orderMap = new HashMap<String, String>();
		while (fieldIt.hasNext()) {
			Entry<String, Map<String, String>> entry = fieldIt.next();
			if (entry.getValue().get("orderable").equals("true")) {
				orderMap.put(entry.getKey(), entry.getValue().get("data"));
			}
		}
		for (int i = 0; i < orderMap.entrySet().size(); i++) {
			dataTableReciveParam.getOrderList().add(orderMap.get(String.valueOf(i)));
		}
		return dataTableReciveParam;
	}

}
