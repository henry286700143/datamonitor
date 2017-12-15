package com.viewstar.jsdatamonitor.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ParameterHelper {

	/**
	 * ����displaytag��ҳ
	 *
	 * @param parametername
	 * @param attributename
	 * @param request
	 * @return
	 */
	public static String getDisplaytagPageEncode(String parametername, String attributename, HttpServletRequest request) {
		// �õ�ģ����ѯ�Ĳ���ֵ
		String name = request.getParameter(parametername) == null || request.getParameter(parametername).equals("") ? null : request.getParameter(parametername).trim();

		// �������Ϊ���򲻱���?
		if (name == null) {
			request.getSession().setAttribute(attributename, null);
		}
		// ����ύ�Ĳ���ֵ��Ϊ�������еĲ���ֵ��Ϊ��?
		if (name != null && request.getSession().getAttribute(attributename) != null) {

			String tempname = request.getSession().getAttribute(attributename).toString();
			// ����������ύ�����¸��?
			if (request.getParameter("displayAjax") == null) {
				tempname = name;
			}
			name = tempname;
			request.getSession().setAttribute(attributename, name);

		}
		// ����ǵ�һ��ģ����ѯ�򱣴��?
		if (name != null && request.getSession().getAttribute(attributename) == null) {
			request.getSession().setAttribute(attributename, name);
		}

		return name;

	}

	public static String getParameter(HttpServletRequest request) {
		String para = "";
		Iterator it = request.getParameterMap().keySet().iterator();
		while (it.hasNext()) {
			String key = it.next().toString();
			para += "&" + key + "=" + request.getParameter(key);
		}
		// System.out.println(para);
		return para;
	}

	/**
	 * ��ò�ѯ����URL
	 *
	 * @param request
	 * @param map
	 *            ��Ҫ��session��ȡ�����Ĳ����б�
	 * @return
	 */
	public static String getParameter(HttpServletRequest request, Map map) {
		String para = "";
		Iterator it = request.getParameterMap().keySet().iterator();
		while (it.hasNext()) {
			String key = it.next().toString();
			if (!map.containsKey(key))
				para += "&" + key + "=" + request.getParameter(key);
		}

		it = map.keySet().iterator();
		while (it.hasNext()) {
			String paramName = it.next().toString();
			String attributeName = map.get(paramName).toString();
			String paramValue = getDisplaytagPageEncode(paramName, attributeName, request);
			para += "&" + paramName + "=" + (paramValue == null ? "" : paramValue);
		}
		// System.out.println(para);
		return para;
	}

	//���������request �����ѯ���String
	//getAreaRealCodeList
	public static String getAreaRealCodeQueryString(HttpServletRequest request){
		String areaCode = request.getParameter("citycategory");
		if(areaCode == null || "".equals(areaCode) || "null".equals(areaCode)){
			return null;
		}
		String resultString = "AND ( ";
		String[] area = areaCode.split("-");
		for (int i = 0; i < area.length; i++) {
			resultString += "AREACODE = '"+area[i] +"' OR ";
		}
		resultString = resultString.substring(0, resultString.lastIndexOf("OR"));
		resultString += ")";
		return resultString;
	}

	public static String getAreaRealCodeQueryString(HttpServletRequest request, String pre){
		String areaCode = request.getParameter("citycategory");
		if(areaCode == null || "".equals(areaCode) || "null".equals(areaCode)){
			return null;
		}
		String resultString = pre + " ( ";
		String[] area = areaCode.split("-");
		for (int i = 0; i < area.length; i++) {
			resultString += "AREACODE = '"+area[i] +"' OR ";
		}
		resultString = resultString.substring(0, resultString.lastIndexOf("OR"));
		resultString += ")";
		return resultString;
	}
	
	public static List<String> getAreaRealCodeQueryArray(HttpServletRequest request, String requestName){
		String areaCode = request.getParameter(requestName);
		if(areaCode == null || "".equals(areaCode) || "null".equals(areaCode)){
			return null;
		}
		String[] area = areaCode.split("-");
		List<String> list = Arrays.asList(area);
		return list;
	}
	
	/**
	 * ����request�����ԭCode,��ʽ��0001-0002
	 * @param request
	 * @return
	 */
	public static String getAreaCodeQueryString(HttpServletRequest request){
		String areaCode = request.getParameter("citycategory");
		if(areaCode == null || "".equals(areaCode) || "null".equals(areaCode)){
			return null;
		}
		return areaCode;
	}

	
	/**
	 * �ж϶����Ƿ�Ϊ�գ�null,"","null"��
	 * @param object
	 * @return
	 */
	public static boolean isNull(Object object) {
		return ("".equals(object) || object == null || "null".equals(object));
	}
}
