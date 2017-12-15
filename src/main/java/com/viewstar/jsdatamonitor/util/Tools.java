package com.viewstar.jsdatamonitor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Tools {
	
	public static void putMapPara(Map map,String key,Object value){
		if(null != value){
			map.put(key, value);
		}
	}
	
	public static void putMapParaEmpty(Map map,String key,Object value){
		if(value != null){
			map.put(key, value);
		}else {
			map.put(key, "");
		}
	}
	
	public Map<String, Double> CustomProgramFrameDetailToResult(
			List<Map<String, String>> list) {
		String ruleLine = list.get(0).get("RULE");
		Map<String, Map<String, Double>> resultMap = new HashMap<String, Map<String,Double>>();
		if(null != ruleLine){
			String[] rules = ruleLine.split(";");
			for (int i = 0; i < rules.length; i++) {
				if(rules[i].contains(":")){
					String[] rule = rules[i].split("=");
					Map<String, Double> specifiedValue = this.getSpecifiedValue(rule[1], list);
					resultMap.put(rule[0], specifiedValue);
				}else{
					String[] rule = rules[i].split("=");
					Map<String, Double> resultValue = this.getResultValue(rule[1], resultMap);
					resultMap.put(rule[0], resultValue);
				}
			
			}
		}else{
			Map<String, Double> resultValue = new HashMap<String, Double>();
			resultValue.put("RTG", Double.valueOf("-1"));
			resultValue.put("SHR", Double.valueOf("-1"));
			resultMap.put("RESULT", resultValue);
		}
		
		
		return resultMap.get("RESULT");
	}
	
	public Map<String, Double> getSpecifiedValue(String rule,
			List<Map<String, String>> list) {
		List<Map<String, Double>> resultList = new ArrayList<Map<String, Double>>();
		double rtg = 0.0;
		double shr = 0.0;
		Map<String, Double> result = new HashMap<String, Double>();
		String[] split = rule.substring(rule.indexOf("(") + 1,
				rule.indexOf(")")).split("\\,");
		for (int i = 0; i < split.length; i++) {
			for (int j = 0; j < list.size(); j++) {
				if(split[i].contains("-")){
					if(split[i].split(":")[0].equals(list.get(j).get("RULEBEGINTIME"))&&Integer.valueOf(split[i].split(":")[1].split("-")[0])<=DateUtil.dayOfWeek(list.get(j).get("AIRDATE"))&&Integer.valueOf(split[i].split(":")[1].split("-")[1])>=DateUtil.dayOfWeek(list.get(j).get("AIRDATE"))){
						Map<String, Double> map = new HashMap<String, Double>();
						if(null!=list.get(j).get("RTG")&&null!=list.get(j).get("SHR")){
							map.put("RTG", Double.valueOf(list.get(j).get("RTG")));
							map.put("SHR",  Double.valueOf(list.get(j).get("SHR")));
							resultList.add(map);
						}else{
							map.put("RTG", Double.valueOf("0.0"));
							map.put("SHR", Double.valueOf("0.0"));
						}
						
						
					}
				}else{
					if(split[i].split(":")[0].equals(list.get(j).get("RULEBEGINTIME"))&&split[i].split(":")[1].equals(String.valueOf(DateUtil.dayOfWeek(list.get(j).get("AIRDATE"))))){
						Map<String, Double> map = new HashMap<String, Double>();
						if(null!=list.get(j).get("RTG")&&null!=list.get(j).get("SHR")){
							map.put("RTG", Double.valueOf(list.get(j).get("RTG")));
							map.put("SHR",  Double.valueOf(list.get(j).get("SHR")));
							resultList.add(map);
						}else{
							map.put("RTG", Double.valueOf("0.0"));
							map.put("SHR", Double.valueOf("0.0"));
						}
					}
				}
			}
			
		}
		if(resultList.size()>=1){
			if(rule.substring(0,3).equals("AVG")){
				for (int i = 0; i < resultList.size(); i++) {
					rtg = rtg + resultList.get(i).get("RTG");
					shr = shr + resultList.get(i).get("SHR");
				}
				rtg = rtg/resultList.size();
				shr = shr/resultList.size();
			}else if (rule.substring(0,3).equals("MAX")){
				for (int i = 0; i < resultList.size(); i++) {
					if(rtg< resultList.get(i).get("RTG")){
						rtg = resultList.get(i).get("RTG");
					}
					if(shr< resultList.get(i).get("SHR")){
						shr = resultList.get(i).get("SHR");
					}
				}
			}
			result.put("RTG", rtg);
			result.put("SHR", shr);
		}else{
			result.put("RTG", Double.valueOf("0.0"));
			result.put("SHR", Double.valueOf("0.0"));
		}
		
		return result;
	}
	
	public Map<String, Double> getResultValue(String rule,Map<String, Map<String, Double>> map){
		Map<String, Double> result = new HashMap<String, Double>();
		String[] split = rule.substring(rule.indexOf("(") + 1,
				rule.indexOf(")")).split("\\,");
		double rtg = 0.0;
		double shr = 0.0;
		System.out.println(rule.contains("AVG"));
		System.out.println(rule.contains("MAX"));
		if(rule.substring(0,3).equals("AVG")){
			for (int i = 0; i < split.length; i++) {
				rtg = rtg + map.get(split[i]).get("RTG");
				shr = shr + map.get(split[i]).get("SHR");
			}
			
			rtg = rtg/map.size();
			shr = shr/map.size();
		}else if (rule.substring(0,3).equals("MAX")){
			for (int i = 0; i < split.length; i++) {
				if(rtg< map.get(split[i]).get("RTG")){
					rtg = map.get(split[i]).get("RTG");
				}
				if(shr< map.get(split[i]).get("SHR")){
					shr = map.get(split[i]).get("SHR");
				}
			}
		}
		result.put("RTG", rtg);
		result.put("SHR", shr);
		return result;
	}
	public static void main(String[] args) {
		System.out.println("MAX(1258:3,2159:4)".substring("MAX(1258:3,2159:4)".indexOf("(") + 1,
				"MAX(1258:3,2159:4)".indexOf("(")).split("\\,"));
	}
}
