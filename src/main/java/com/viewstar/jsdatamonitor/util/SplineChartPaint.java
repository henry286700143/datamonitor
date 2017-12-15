package com.viewstar.jsdatamonitor.util;

import java.util.List;
import java.util.Map;

/**
 * ����ͼ��ͼ������
 * @author wangsc
 *
 */
public class SplineChartPaint {
	
	/**
	 * �õ�y�������?
	 * @param dataList  һ���Ǵ����ݿ��в������list
	 * @param nameList  �������Ƶ�list����fieldList��Ӧ
	 * @param fieldList ����ֶ�list
	 * @return
	 */
	public static String getYAxis(List<Map<String, Object>> dataList, List nameList, List fieldList) {
		String series = "[";
		for (int i = 0; i < fieldList.size(); i++) {
			series += "{";
			StringBuilder numSb = new StringBuilder();
			numSb.append("[");
			for (int j = 0; j < dataList.size(); j++) {
				Map<String, Object> tempMap = dataList.get(j);
				numSb.append(tempMap.get(fieldList.get(i)).toString()).append(",");
			}
			numSb.deleteCharAt(numSb.length() - 1);
			numSb.append("]");
			series += "name: '" + nameList.get(i) + "',data: "+numSb;
			series += "},";
		}
		series = series.substring(0,series.length()-1) + "]";
		return series;
	}
	
	/**
	 * �õ�x�������?
	 * @param dataList һ���Ǵ����ݿ��в������list
	 * @param field ����ֶ�?,һ����TIME
	 * @return
	 */
	public static String getXAxis(List<Map<String, Object>> dataList, String field) {
		StringBuilder timeSb = new StringBuilder();
		String xAxis = "";
		for (int i = 0; i < dataList.size(); i++) {
			Map<String, Object> tempMap = dataList.get(i);
			timeSb.append("'").append(tempMap.get(field).toString()).append("'").append(",");
		}
		if (timeSb != null) {
			xAxis = timeSb.substring(0,timeSb.length()-1);
		}
		return xAxis;
	}
	
}
