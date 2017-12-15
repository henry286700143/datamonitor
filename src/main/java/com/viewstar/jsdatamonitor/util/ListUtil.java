package com.viewstar.jsdatamonitor.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

/**
 * list������
 *
 * @author liwei
 *
 */
public class ListUtil {
	/**
	 * mapת��Ϊlist,���Ҹı�key������
	 *
	 * @param map
	 *            ԭʼmap
	 * @param name
	 *            ԭʼkey����
	 * @param newname
	 *            �µ�key��������
	 * @param keyName
	 *            list�е�map��key����
	 * @param valueName
	 *            list�е�map��value����
	 * @return
	 */
	public List<Map<String, Object>> mapToList(Map<String, Object> map, String[] name, String[] newname, String keyName, String valueName) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < name.length; i++) {
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put(keyName, newname[i]);
			temp.put(valueName, map.get(name[i]));
			list.add(temp);
		}
		return list;
	}

	/**
	 * Map<String, List<String>>ת��Ϊlist,ÿһ�еĸ�ʽ"key,value"
	 *
	 * @param map
	 * @return
	 */
	public List<String> mapToList(Map<String, List<String>> map) {
		List<String> result = new ArrayList<String>();
		Iterator<Entry<String, List<String>>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, List<String>> entry = it.next();
			List<String> list = entry.getValue();
			for (int i = 0; i < list.size(); i++) {
				result.add(entry.getKey() + "," + list.get(i));
			}
		}
		return result;
	}
	/**
	 * �ַ������ݷָ�������List
	 * @param s
	 * @param split
	 * @param defaultStr ���ֶ���Ч
	 * @return
	 */
	public List<String> stringToList(String s, String split, String defaultStr) {
		List<String> list = new ArrayList<String>();
		if (s == null || s.indexOf(split) == -1) {
			return null;
		}
		s = s.substring(0, s.lastIndexOf(split));
		String[] result = s.split(split);
		for (int i = 0; i < result.length; i++) {
			list.add(result[i]);
		}
		return list;
	}
	/**
	 * list�����ַ�������
	 * @param list
	 * @return
	 */
	public String[] listToStringArray(List<String> list) {
		String[] result = new String[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i);
		}
		return result;
	}
	/**
	 * setתlist
	 * @param set
	 * @return
	 */
	public List<String> setToList(Set<String> set) {
		List<String> list = new ArrayList<String>();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}
	/**
	 * listתset
	 * @param set
	 * @return
	 */
	public Set<String> listToSet(List<String> list) {
		Set<String> set = new HashSet<String>();
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			set.add(it.next());
		}
		return set;
	}
	/**
	 * setת String[]
	 * @param set
	 * @return
	 */
	public String[] setToStringArray(Set<String> set) {
		String[] result = new String[set.size()];
		Iterator<String> it = set.iterator();
		int i = 0;
		while (it.hasNext()) {
			result[i++] = it.next();
		}
		return result;
	}

	/**
	 * �ϲ�����list��ֵ������ȥ��
	 * @param aList
	 * @param bList
	 * @return
	 */
	public List<String> mergeList(List<String> aList, List<String> bList) {
		List<String> list = new ArrayList<String>();
		list.addAll(aList);
		for (int i = 0; i < bList.size(); i++) {
			if (!list.contains(bList.get(i))) {
				list.add(bList.get(i));
			}
		}
		return list;
	}
	/**
	 * listȥ��
	 * @param list
	 * @return
	 */
	public List<String> listUnique(List<String> list) {
		List<String> templist = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (!templist.contains(list.get(i))) {
				templist.add(list.get(i));
			}
		}
		return templist;
	}
	/**
	 * listȥ�أ�������ȥ�ؽ���͸���?
	 * @param list
	 * @return
	 */
	public Object[] listUniqueAndNum(List<String> list) {
		List<String> templist = new ArrayList<String>();
		Map<String, Long> map = new HashMap<String, Long>();
		for (int i = 0; i < list.size(); i++) {
			if (!templist.contains(list.get(i))) {
				templist.add(list.get(i));
			}
			if (map.containsKey(list.get(i))) {
				map.put(list.get(i), map.get(list.get(i)) + 1);
			} else {
				map.put(list.get(i), new Long(1));
			}
		}
		String[] listUniqueArray = new String[templist.size()];
		double[] numArray = new double[templist.size()];
		for (int i = 0; i < templist.size(); i++) {
			listUniqueArray[i] = templist.get(i);
			numArray[i] = map.get(templist.get(i));
		}
		Object[] object = new Object[2];
		object[0] = listUniqueArray;
		object[1] = numArray;
		return object;
	}

	/**
	 *  list���� ð��
	 * @param list
	 * @param sortKey ��Ҫ�����key
	 * @param order ASC��С���� �� DESC�ɴ�С
	 * @param beginIndex �������� begin
	 * @param endIndex �������� end
	 */
	public List<Map> SortList (List<Map> list, String sortKey, String order, int beginIndex, int endIndex) {
		List<Map> totoal = new ArrayList<Map>();
		if (list != null && list.size() > 0) {
			List<Map> newList = list.subList(beginIndex, endIndex);
			/**
			 * ��С����
			 */
			for (int i = 0; i < newList.size(); i++) {

				for (int j = 1; j < newList.size() - i; j++) {
					Map map1 = newList.get(j-1);
					Double value1 = Double.valueOf(map1.get(sortKey).toString());

					Map map2 = newList.get(j);
					Double value2 = Double.valueOf(map2.get(sortKey).toString());

					if (value1 > value2) {
						Map map = newList.get(j);
						newList.set(j, newList.get(j - 1));
						newList.set(j - 1, map);
					}
				}
			}

			/**
			 * ����
			 */
			if (order.equals("DESC") || order.equals("desc")) {
				Collections.reverse(newList);
			}
			if (beginIndex > 0) {
				beginIndex = beginIndex - 1;
			}
			totoal.addAll(list.subList(0, beginIndex));
			totoal.addAll(newList);
			totoal.addAll(list.subList(endIndex, list.size()));
		} else {
			totoal = list;
		}
		return totoal;
	}

	/**
	 * �����list[] ת��json ����lists
	 * @param lists ����Ķ��list
	 * @return list<String>
	 */
	public String listtoJsonLists (List<Map>...lists) {
		List<List> list = new ArrayList<List>();
		Gson gson = new Gson();
		for (int i = 0; i < lists.length; i++) {
			List list2 = lists[i];
			if(list2.size() > 0){
				list.add(list2);
			}
		}
		return gson.toJson(list);
	}
	public String listtoJsonLists2 (List<List<Map>> lists) {
		List<List> list = new ArrayList<List>();
		Gson gson = new Gson();
		for (int i = 0; i < lists.size(); i++) {
			list.add(lists.get(i));
		}
		return gson.toJson(list);
	}
	/**
	 * �����list[] ת��json ����lists
	 * @param lists ����Ķ��list
	 * @return list<String>
	 */
	public String listMaptoJsonLists (List<List<Map>> lists) {
		List<List> list = new ArrayList<List>();
		Gson gson = new Gson();
		for (int i = 0; i < lists.size(); i++) {
			List list2 = lists.get(i);
			list.add(list2);
		}
		return gson.toJson(list);
	}
	/**
	 * �����ַ������ݿ�ʼ�����ַ���ȡ�ַ����б�
	 * @param content
	 * @param startTag
	 * @param endTag
	 * @return
	 */
	public static List<String> getGroup(String content,String startTag,String endTag){
		List<String> list = new ArrayList<String>();
		if(endTag.equals("")){
			list.add(content.split(startTag)[1]);
			return list;
		}
		if(startTag.equals("")){
			list.add(content.split(endTag)[0]);
			return list;
		}
		Pattern pattern = Pattern.compile(startTag+"([\\w|\\W|\\s|\\S|\\d|\\D]*?)"+endTag);
		Matcher matcher = pattern.matcher(content);
		while(matcher.find()){
			list.add(matcher.group().replaceAll(startTag, "").replaceAll(endTag, ""));
		}
		return list;
	}
	/**
	 * ��List<Map<String, String>>������ɾ����map��value=channel&&value=program���Ǹ�map
	 * @param list
	 * @param channel
	 * @param program
	 * @return
	 */
	public static List<Map<String, String>> deleteMapFromList(List<Map<String, String>> list, String channel, String program,String time,String user){
		if(list.size() == 0){
			return list;
		}
		
		for(int i = list.size()-1; i >=0; i--){
			Map<String, String> map = list.get(i);
			String pString=map.get("program").replace("&mh;", ":").replace("&fxg;", "/").replace("&xg;", "\\");
			if(channel.equals(map.get("channel")) && program.equals(pString)&& time.equals(map.get("time"))&& user.equals(map.get("user"))){
				list.remove(i);
			}
		}
		return list;
	}
	/**
	 * ��List��ɾ��key=key��value������map.get(key)��map
	 * 
	 * @param list
	 * @param key
	 * @param value
	 * @return
	 */
	public static List<Map<String, String>> deleteMapFromList(List<Map<String, String>> list, String key, String value){
		if(list.size() == 0){
			return list;
		}
		
		for(int i = list.size()-1; i >=0; i--){
			Map<String, String> map = list.get(i);
			if(map!= null && !value.contains(map.get(key))){
				list.remove(i);
			}
		}
		return list;
	}

	 // Empty checks
    //-----------------------------------------------------------------------
    /**
     * <p>Checks if a List is empty (null).</p>
     *
     * <pre>
     * ListUtil.isEmpty(null)      = true
     * </pre>
     *
     *
     * @param list  the List to check, may be null
     * @return <code>true</code> if the List is empty or null
     */
    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }
}
