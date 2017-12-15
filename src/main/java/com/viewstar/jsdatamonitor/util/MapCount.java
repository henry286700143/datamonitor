package com.viewstar.jsdatamonitor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Map����������
 *
 * @author liwei
 *
 */
public class MapCount {

	public static String userid = "x";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * �洢key��Ӧ��set
	 *
	 * @param map
	 * @param key
	 * @param value
	 */
	public void simpleMapSet(Map<String, Set<String>> map, String key, String value) {
		if (!map.containsKey(key)) {
			Set<String> set = new HashSet<String>();
			set.add(value);
			map.put(key, set);
		} else {
			map.get(key).add(value);
		}
	}

	/**
	 * �洢key��Ӧ��List
	 *
	 * @param map
	 * @param key
	 * @param value
	 */
	public void simpleMapList(Map<String, List<String>> map, String key, String value) {
		if (!map.containsKey(key)) {
			List<String> list = new ArrayList<String>();
			list.add(value);
			map.put(key, list);
		} else {
			if (!map.get(key).contains(value)) {
				map.get(key).add(value);
			}
		}
	}

	/**
	 * map����������
	 *
	 * @param map
	 * @param key
	 */
	public void simpleMapCount(Map<String, Long> map, String key) {
		if (!map.containsKey(key)) {
			map.put(key, new Long(1));
		} else {
			map.put(key, map.get(key) + 1);
		}
	}

	/**
	 * map����������
	 *
	 * @param map
	 * @param key
	 */
	public void simpleMapCount(Map<String, Long> map, String key, long value) {
		if (!map.containsKey(key)) {
			map.put(key, new Long(value));
		} else {
			map.put(key, map.get(key) + value);
		}
	}

	/**
	 * map����������
	 *
	 * @param map
	 * @param key
	 */
	public void simpleMapCount(Map<String, Map<String, Long>> map, String key1, String key2, long value) {
		if (!map.containsKey(key1)) {
			Map<String, Long> temp = new HashMap<String, Long>();
			temp.put(key2, value);
			map.put(key1, temp);
		} else {
			Map<String, Long> map1 = map.get(key1);
			if (!map1.containsKey(key2)) {
				map1.put(key2, value);
			} else {
				map1.put(key2, map1.get(key2) + value);
			}
		}
	}

	/**
	 * map����������
	 *
	 * @param map
	 * @param key
	 */
	public void simpleMapCountUser(Map<String, Long> map, String key) {
		String s = key.toString();
		String[] vods = s.split(",");
		for (int i = 0; i < vods.length; i++) {
			if (!map.containsKey(vods[i])) {
				map.put(vods[i], new Long(1));
			} else {
				map.put(vods[i], map.get(vods[i]) + 1);
			}
		}
	}

	/**
	 * ����Map���ֵ�ĺ�?
	 *
	 * @param map
	 */
	public long sumMapValue(Map<String, Map<String, Long>> map) {
		long sum = 0;
		Iterator<Entry<String, Map<String, Long>>> level1It = map.entrySet().iterator();
		while (level1It.hasNext()) {
			Entry<String, Map<String, Long>> level1Entry = level1It.next();
			Iterator<Entry<String, Long>> level2It = level1Entry.getValue().entrySet().iterator();
			while (level2It.hasNext()) {
				sum = sum + level2It.next().getValue();
			}
		}
		return sum;
	}

	/**
	 * map������
	 *
	 * @param map
	 * @param key
	 */
	public List<Map<String, String>> getSimpleMapCountToList(Map<String, Long> map) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Iterator<Entry<String, Long>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Long> entry = it.next();
			Map<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("name", entry.getKey());
			tempMap.put("count", entry.getValue().toString());
			list.add(tempMap);
		}
		return list;
	}

	/**
	 * map������
	 * ��水���λ���ּ���
	 * @param map
	 * @param key
	 */
	public List<Map<String, String>> getSimpleMapCountToADList(Map<String,Map<String, Long>> map) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Iterator<Entry<String,Map<String, Long>>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String,Map<String, Long>> entry = it.next();
			Map<String, Long> tempMap = entry.getValue();
			Iterator<Entry<String, Long>> tempit = tempMap.entrySet().iterator();
			while (tempit.hasNext()) {
				Map<String, String> finalmap = new HashMap<String, String>();
				Entry<String, Long> tempentry = tempit.next();
				finalmap.put("name", entry.getKey());
				finalmap.put("state", tempentry.getKey());
				finalmap.put("count", tempentry.getValue().toString());
				list.add(finalmap);
			}
		}
		return list;
	}
}
