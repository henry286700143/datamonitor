package com.viewstar.jsdatamonitor.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileUtil {

	com.google.gson.Gson gson = new com.google.gson.Gson();
	public void processTest(String srcPath,String destPath) throws Exception{
		File file = new File(srcPath);
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			String dest = destPath+files[i].getName();
			if(!new File(dest.replace(".java", "Test.java")).exists()){
				new File(dest.replace(".java", "Test.java")).createNewFile();
			}
		}
	}

	public void write (List<Map<String,Object>> list,String filePath) throws Exception{
		com.google.gson.Gson gson = new com.google.gson.Gson();
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			content.append(gson.toJson(list.get(i))+"\n");
		}
		write(content,filePath);
	}
	public void write(StringBuffer content , String filePath) throws Exception{
		File f = new File(filePath);
		String path = f.getParent();
		f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		f = new File(filePath);
		f.delete();
         OutputStreamWriter osw = new  OutputStreamWriter(new FileOutputStream(filePath),"UTF-8");
         osw.write(content.toString());
         osw.flush();
	}
	public void writeAppend(List<Map<String,Object>> list,String filePath) throws Exception{
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			content.append(gson.toJson(list.get(i))+"\n");
		}
		appendMethodNoCheck(filePath, content.toString());
	}

	/**
	 * 得到一个BufferedReader
	 * @param filepath 文件名
	 * @param charSet 字符集
	 * @return
	 * @throws Exception
	 */
	public BufferedReader getBufferedReader(String filepath, String charSet) throws Exception {
		return new BufferedReader(new InputStreamReader(new FileInputStream(filepath), charSet));
	}
	/**
	 * 关闭一个BufferedReader
	 * @param bufferedReader
	 * @throws Exception
	 */
	public void clostBufferedReader(BufferedReader bufferedReader) throws Exception {
		bufferedReader.close();
	}
	public String getName(String name){

		Map<String,String> nameMap = new HashMap<String,String>();
		nameMap.put("TVOD24小时收视情况","1.jpg");
		nameMap.put("TVOD总体收视情况","2.jpg");
		nameMap.put("TVOD节目收视情况","3.jpg");
		nameMap.put("TVOD频道市场份额","4.jpg");
		nameMap.put("TVOD频道组市场份额","5.jpg");
		nameMap.put("TVOD频道组总体收视情况","6.jpg");
		nameMap.put("VOD24小时收视情况","7.jpg");
		nameMap.put("VOD总体收视情况","8.jpg");
		nameMap.put("VOD收视情况","9.jpg");
		nameMap.put("专题24小时收视情况","10.jpg");
		nameMap.put("专题名称查询","11.jpg");
		nameMap.put("专题总体收视情况","12.jpg");
		nameMap.put("专题收视情况","13.jpg");
		nameMap.put("专题类别维护","14.jpg");
		nameMap.put("专题维护","15.jpg");
		nameMap.put("专题节目单上传","16.jpg");
		nameMap.put("专题节目单导入","17.jpg");
		nameMap.put("交叉计算批量操作文件上传","18.jpg");
		nameMap.put("产品分组维护","19.jpg");
		nameMap.put("产品收视情况","20.jpg");
		nameMap.put("产品收视情况cp","21.jpg");
		nameMap.put("产品维护","22.jpg");
		nameMap.put("人员维护","23.jpg");
		nameMap.put("全平台分时段数据收视情况","24.jpg");
		nameMap.put("全平台历史数据趋势分析","25.jpg");
		nameMap.put("参数设置","26.jpg");
		nameMap.put("四色键使用习惯","27.jpg");
		nameMap.put("报表中心","28.jpg");
		nameMap.put("推荐策略管理","29.jpg");
		nameMap.put("收视情况概述","30.jpg");
		nameMap.put("数据处理任务","31.jpg");
		nameMap.put("数据抽取","32.jpg");
		nameMap.put("数据抽取配置","33.jpg");
		nameMap.put("数据预处理","34.jpg");
		nameMap.put("日志查询","35.jpg");
		nameMap.put("月报一键生成","36.jpg");
		nameMap.put("权限管理","37.jpg");
		nameMap.put("标签使用情况","38.jpg");
		nameMap.put("栏目VOD统计","39.jpg");
		nameMap.put("栏目数据分析","40.jpg");
		nameMap.put("栏目树维护","41.jpg");
		nameMap.put("栏目连续剧统计","42.jpg");
		nameMap.put("活动原始数据下载","43.jpg");
		nameMap.put("活动统计","44.jpg");
		nameMap.put("活动维护","45.jpg");
		nameMap.put("点播收视习惯","46.jpg");
		nameMap.put("用户24小时在线情况","47.jpg");
		nameMap.put("用户24小时登陆情况","48.jpg");
		nameMap.put("用户分组管理","49.jpg");
		nameMap.put("用户在线情况","50.jpg");
		nameMap.put("用户开户人数","51.jpg");
		nameMap.put("用户开机率","52.jpg");
		nameMap.put("用户操作信息批量下载","53.jpg");
		nameMap.put("用户操作信息查询","54.jpg");
		nameMap.put("用户收视查询","55.jpg");
		nameMap.put("用户活跃情况查询","56.jpg");
		nameMap.put("用户登录信息查询","57.jpg");
		nameMap.put("用户登录信息统计","58.jpg");
		nameMap.put("用户登录信息统计比较","59.jpg");
		nameMap.put("直播分时段统计","60.jpg");
		nameMap.put("直播总体收视情况(CSM)","61.jpg");
		nameMap.put("直播总体收视情况","62.jpg");
		nameMap.put("直播换台习惯","63.jpg");
		nameMap.put("直播频道市场份额","64.jpg");
		nameMap.put("直播频道收视人数","65.jpg");
		nameMap.put("直播频道收视时长统计","66.jpg");
		nameMap.put("直播频道收视率比较","67.jpg");
		nameMap.put("直播频道时段卫视频道市场份额比较","68.jpg");
		nameMap.put("直播频道时段收视率比较","69.jpg");
		nameMap.put("直播频道组市场份额","70.jpg");
		nameMap.put("直播频道组总体收视情况","71.jpg");
		nameMap.put("直播频道节目收视交叉批量计算工具","72.jpg");
		nameMap.put("直播频道节目收视交叉计算工具","73.jpg");
		nameMap.put("直播频道节目收视情况查询","74.jpg");
		nameMap.put("看吧与纯点播用户","75.jpg");
		nameMap.put("看吧用户结构分析","76.jpg");
		nameMap.put("类型维护","77.jpg");
		nameMap.put("系统更新日志","78.jpg");
		nameMap.put("联通光纤用户查询","79.jpg");
		nameMap.put("节假日收视情况","80.jpg");
		nameMap.put("节假日日期管理","81.jpg");
		nameMap.put("节目属性批量修改工具","82.jpg");
		nameMap.put("节目权重管理","83.jpg");
		nameMap.put("视频收视情况","84.jpg");
		nameMap.put("角色管理","85.jpg");
		nameMap.put("账户管理","86.jpg");
		nameMap.put("连续剧24小时收视情况","87.jpg");
		nameMap.put("连续剧子集收视情况","88.jpg");
		nameMap.put("连续剧总体收视情况","89.jpg");
		nameMap.put("连续剧收视情况","90.jpg");
		nameMap.put("页面维护","91.jpg");
		nameMap.put("预处理参数设置","92.jpg");
		nameMap.put("预处理状态管理","93.jpg");
		nameMap.put("频道组频道市场份额","94.jpg");
		return nameMap.get(name);
	}

	private static String Md5(String plainText) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buf.toString();
	}
	public void mkdir(String filepath) throws Exception {
		File file = new File(filepath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public Set<String> getSetFromFile(String filepath, String split, int col, String charset) throws Exception {
		Set<String> set = new HashSet<String>();
		InputStreamReader isr = new InputStreamReader(new FileInputStream(filepath), charset);
		BufferedReader br = new BufferedReader(isr);
		String temp = "";
		String[] tempArray = null;
		while ((temp = br.readLine()) != null) {
			if (!temp.equals("")) {
				tempArray = temp.split(split);
				if(tempArray.length>1){
					set.add(temp.split(split)[col]);
				}
			}
		}
		return set;
	}


	/**
	 * 得到一个CSV文件的某一列的去重复值的个数
	 * @param filepath 文件路径
	 * @param spilt 分隔符，一般是逗号
	 * @param col 指定是哪一列
	 * @return Map<String,Long> <值，数量>
	 * @throws Exception
	 */
	public Map<String,Long> getMapCountFromFile(String filepath,String spilt,int col) throws Exception
	{
		Map<String,Long> map=new HashMap<String,Long>();
		InputStreamReader isr = new InputStreamReader(new FileInputStream(filepath), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String temp = "";
		while ((temp = br.readLine()) != null) {
			if(!temp.equals(""))
			{
					if(!map.containsKey(temp.trim().split(spilt)[col]))
				{
					map.put(temp.trim().split(spilt)[col],new Long(1));
				}
					else
				{
					map.put(temp.trim().split(spilt)[col],map.get(temp.trim().split(",")[col])+1);
				}
			}
		}
		return map;
	}
	/**
	 * 得到一个CSV文件一列对于另外一列的MAP关系
	 * @param filepath 文件路径
	 * @param spilt 分隔符，一般是逗号
	 * @param keycol 指定作为KEY的一列
	 * @param valuecol 指定作为值的一列
	 * @return Map<String,List<String>> <一列的值,另外一列的元素列表>
	 * @throws Exception
	 */
	public Map<String,List<String>> getMapFromFile(String filepath,String spilt,int keycol,int valuecol) throws Exception
	{
		Map<String,List<String>> map=new HashMap<String,List<String>>();
		InputStreamReader isr = new InputStreamReader(new FileInputStream(filepath), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String temp = "";
		while ((temp = br.readLine()) != null) {
			if(!temp.equals("")&&temp.trim().split(",").length > Math.max(keycol, valuecol))
			{
					if(!map.containsKey(temp.trim().split(spilt)[keycol]))
				{

					List<String> list = new ArrayList<String>();
					list.add(temp.trim().split(",")[valuecol]);
					map.put(temp.trim().split(spilt)[keycol], list);
				}else{
					map.get(temp.trim().split(spilt)[keycol]).add(temp.trim().split(",")[valuecol]);
				}
			}
		}
		return map;
	}
	public Map<String,List<String>> getMapFromFile(String filepath,String spilt,int keycol,int valuecol,String charset) throws Exception
	{
		Map<String,List<String>> map=new HashMap<String,List<String>>();
		InputStreamReader isr = new InputStreamReader(new FileInputStream(filepath), charset);
		BufferedReader br = new BufferedReader(isr);
		String temp = "";
		while ((temp = br.readLine()) != null) {
			if(!temp.equals("")&&temp.trim().split(",").length > Math.max(keycol, valuecol))
			{
					if(!map.containsKey(temp.trim().split(spilt)[keycol]))
				{

					List<String> list = new ArrayList<String>();
					list.add(temp.trim().split(",")[valuecol]);
					map.put(temp.trim().split(spilt)[keycol], list);
				}else{
					map.get(temp.trim().split(spilt)[keycol]).add(temp.trim().split(",")[valuecol]);
				}
			}
		}
		return map;
	}
	public Map<String,List<String>> getMapFromFileWithConvert(String filepath,String spilt,int keycol,int valuecol,String charset,Map<String, String> leftmap1, Map<String, String> leftmap2, Map<String, String> rightmap1, Map<String, String> rightmap2) throws Exception
	{
		Map<String,List<String>> map=new HashMap<String,List<String>>();
		InputStreamReader isr = new InputStreamReader(new FileInputStream(filepath), charset);
		BufferedReader br = new BufferedReader(isr);
		String temp = "";
		while ((temp = br.readLine()) != null) {
			if(!temp.equals("")&&temp.trim().split(spilt).length > Math.max(keycol, valuecol))
			{
				String[] temparray = temp.trim().split(spilt);
					if(!map.containsKey(leftmap2.get(leftmap1.get(temparray[keycol]))))
				{
					List<String> list = new ArrayList<String>();
					list.add(rightmap2.get(rightmap1.get(temparray[valuecol])));
					map.put(leftmap2.get(leftmap1.get(temparray[keycol])), list);
				}else{
					map.get(leftmap2.get(leftmap1.get(temparray[keycol]))).add(rightmap2.get(rightmap1.get(temparray[valuecol])));
				}
			}
		}
		return map;
	}
	public Map<String,String> getUnqMapFromFile(String filepath,String spilt,int keycol,int valuecol,String charset) throws Exception
	{
		Map<String,String> map=new HashMap<String,String>();
		InputStreamReader isr = new InputStreamReader(new FileInputStream(filepath), charset);
		BufferedReader br = new BufferedReader(isr);
		String temp = "";
		while ((temp = br.readLine()) != null) {
			if(!temp.equals("")&&temp.trim().split(",").length > Math.max(keycol, valuecol))
			{
				map.put(temp.trim().split(spilt)[keycol], temp.trim().split(",")[valuecol]);
			}
		}
		return map;
	}
	public Map<String,List<String>> getUnqMapFromFile(String filepath,String spilt,int keycol,int valuecol) throws Exception
	{
		Map<String,List<String>> map=new HashMap<String,List<String>>();
		InputStreamReader isr = new InputStreamReader(new FileInputStream(filepath), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String temp = "";
		while ((temp = br.readLine()) != null) {
			if(!temp.equals("")&&temp.trim().split(",").length > Math.max(keycol, valuecol))
			{
					if(!map.containsKey(temp.trim().split(spilt)[keycol]))
				{

					List<String> list = new ArrayList<String>();
					list.add(temp.trim().split(",")[valuecol]);
					map.put(temp.trim().split(spilt)[keycol], list);
				}else{
					if(!map.get(temp.trim().split(spilt)[keycol]).contains(temp.trim().split(",")[valuecol])) {
						map.get(temp.trim().split(spilt)[keycol]).add(temp.trim().split(",")[valuecol]);
					}
				}
			}
		}
		return map;
	}

	public Map<String, String> readFileTxtToMap(String path) throws Exception{

		Map<String, String> map = new HashMap<String, String>();

		InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String temp = null;

		while ((temp = br.readLine()) != null) {
			String[] content = temp.split("--");
			map.put(content[0], content[1]);
		}
		return map;
	}

	public void saveFileList(List list,String filepath) throws Exception {
		String content = "";
		for (int i = 0; i < list.size(); i++) {
			content = content + list.get(i).toString().replaceAll("\\{info=", "").replaceAll("\\}", "") + "\n";
		}
		saveFile(content,filepath);
	}
	public void saveFileList(List list,String filepath, String charSet) throws Exception {
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			content = content.append(list.get(i)).append("\n");
		}
		saveFile(content.toString(),filepath,charSet);
	}
	/**
	 * 保存为CSV格式
	 * @param list List<Map<String,String>> 数据格式
	 * @param value 数据表头名称
	 * @param filepath 保存文件路径
	 * @throws Exception
	 */
	public void saveFileList(List<Map<String,String>> list,String[] value,String filepath) throws Exception {
		File f = new File(filepath);
		String path = f.getParent();
		f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		f = new File(filepath);
		f.createNewFile();
		FileWriter fr = new FileWriter(f);
		String content = "";
		for (int i = 0; i < value.length; i++) {
			content = content + "\"" + value[i].replace("-", "@") + "\"" + ",";
		}
		content = content.substring(0, content.length() - 1) + "\n";
		fr.write(content);
		for (int i = 0; i < list.size(); i++) {
			content = "";
			Map<String, String> map = list.get(i);
				for (int j = 0; j < value.length; j++)
			{
					if(j==0)
				{
					content = content+ "userid\"" + map.get(value[j]) + "\"" + ",";
				}
					else
				{
					content = content+ "\"" + map.get(value[j]) + "\"" + ",";
				}

			}
				content = content.substring(0, content.length()-1) + "\n";
				fr.write(content);
		}
		fr.flush();
		fr.close();

	}
	/**
	 * 保存为CSV格式 用户活跃性.
	 * @param list List<Map> 数据格式
	 * @param filepath 保存文件路径
	 * @throws Exception 异常
	 */
	public void saveFileList_userinfo(List<Map> list, String filepath) throws Exception {
		File f = new File(filepath);
		String path = f.getParent();
		f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		f = new File(filepath);
		f.createNewFile();
		FileWriter fr = new FileWriter(f);
		String content = "";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < list.size(); i++) {
			content = "";
			content = content +  "userid\"" + list.get(i).get("USERID") + "\"" + ",";
			content = content + "\"" + sf.format(list.get(i).get("LOGINTIME")) + "\"" + ",";
			content = content.substring(0, content.length() - 1) + "\n";
			fr.write(content);
		}
		fr.flush();
		fr.close();
	}
	/**
	 * 把list保存为csv文件
	 * @param list 要保存的list
	 * @param fields list里面的map字段名
	 * @param file 保存的文件名
	 * @throws Exception
	 */
	public void saveCSVList(List<Map<String, String>> list, String[] fields, String[] fieldType, String file) throws Exception {
		delFile(file);
		File f = new File(new File(file).getParent());
		if (!f.exists()) {
			f.mkdirs();
		}
		f = new File(file);
		if (!f.exists()) {
			f.createNewFile();
		}
		List<String> csvList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> tempMap = list.get(i);
			String tempStr = "";
			for (int j = 0; j < fields.length; j++) {
				if (j == 0) {
					if (fieldType[j].equals("String")) {
						tempStr = tempStr + "\"" + tempMap.get(fields[j]) + "\"";
					} else {
						tempStr = tempStr + tempMap.get(fields[j]);
					}
				} else {
					if (fieldType[j].equals("String")) {
						tempStr = tempStr + ",\"" + tempMap.get(fields[j]) + "\"";
					} else {
						tempStr = tempStr + "," + tempMap.get(fields[j]);
					}
				}
			}
			csvList.add(tempStr);
			if (csvList.size() == 10000) {
				this.saveFileListAppendNoCheck(csvList, file);
				csvList.clear();
			}
		}
		this.saveFileListAppendNoCheck(csvList, file);
	}
	/**
	 * 把list保存为csv文件,带表头
	 * @param list 要保存的list
	 * @param fields list里面的map字段名
	 * @param file 保存的文件名
	 * @throws Exception
	 */
	public void saveCSVListWithHead(List<Map<String, Object>> list, String[] fields, String[] fieldType, String file) throws Exception {
		delFile(file);
		File f = new File(new File(file).getParent());
		if (!f.exists()) {
			f.mkdirs();
		}
		f = new File(file);
		if (!f.exists()) {
			f.createNewFile();
		}
		List<String> csvList = new ArrayList<String>();
		String tempStr = "";
		for (int i = 0; i < fields.length; i++) {

			if (i == 0) {
				tempStr = tempStr + "\"" + fields[i] + "\"";
			}else{
				tempStr = tempStr + ",\"" + fields[i] + "\"";
			}
		}
		csvList.add(tempStr);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> tempMap = list.get(i);
			tempStr = "";
			for (int j = 0; j < fields.length; j++) {
				if (j == 0) {
					if (fieldType[j].equals("String")) {
						tempStr = tempStr + "\"" + tempMap.get(fields[j]) + "\"";
					} else {
						tempStr = tempStr + tempMap.get(fields[j]);
					}
				} else {
					if (fieldType[j].equals("String")) {
						tempStr = tempStr + ",\"" + tempMap.get(fields[j]) + "\"";
					} else {
						tempStr = tempStr + "," + tempMap.get(fields[j]);
					}
				}
			}
			csvList.add(tempStr);
			if (csvList.size() == 10000) {
				this.saveFileListAppendNoCheck(csvList, file);
				csvList.clear();
			}
		}
		this.saveFileListAppendNoCheck(csvList, file);
	}
	/**
	 * 保存List<String>到文件末尾.
	 * @param list 字符串list
	 * @param filepath 文件路径
	 * @throws Exception 异常
	 */
	public void saveFileListAppend(List<String> list, String filepath) throws Exception {
		String content = "";
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			content = content + list.get(i).toString().replaceAll("\\{info=", "").replaceAll("\\}", "") + "\n";
			index ++;
			if(index==100)
			{
				this.appendMethod(filepath,content);
				index = 0;
				content = "";
			}
		}
		this.appendMethod(filepath,content);
	}
	public void saveFileListAppendNoReplace(List<String> list, String filepath) throws Exception {
		File f = new File(new File(filepath).getParent());
		if (!f.exists()) {
			f.mkdirs();
		}
		f = new File(filepath);
		if (!f.exists()) {
			f.createNewFile();
		}
		String content = "";
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			content = content + list.get(i).toString() + "\n";
			index ++;
			if(index==100)
			{
				this.appendMethod(filepath,content);
				index = 0;
				content = "";
			}
		}
		this.appendMethod(filepath,content);
	}
	public void saveFileListAppendNoCheck(List<String> list, String filepath) throws Exception {
		StringBuffer content = new StringBuffer();
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			content.append(list.get(i).toString()).append("\n");
			index ++;
			if(index==10000)
			{
				this.appendMethodNoCheck(filepath,content.toString());
				index = 0;
				content.setLength(0);
			}
		}
		if(content.length()!=0) {
			this.appendMethodNoCheck(filepath,content.toString());
		}
	}
	/**
	 * 合并多个文件到一个文件
	 * @param files
	 * @param filePath
	 * @throws Exception
	 */
	public void mergeFiles(String[] files,String filePath) throws Exception {
		for (int i = 0; i < files.length; i++) {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(files[i]), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String temp = "";
			List<String> list = new ArrayList<String>();
			while ((temp = br.readLine()) != null) {
				if(!temp.equals("")){
					list.add(temp);
					if(list.size()==10000){
						saveFileListAppendNoCheck(list,filePath);
						list.clear();
					}
				}
			}
			saveFileListAppendNoCheck(list,filePath);
		}
	}
	/**
	 * 将一组文件转换成另外一组文件
	 * @param srcFiles 源文件
	 * @param destFilePaths 目标文件
	 * @param convertMap 转换规则
	 * @param srcIdField 源文件与转换规则关联的ID
	 * @param srcFields 源文件字段
	 * @param destFields 目标文件字段
	 * @throws Exception
	 */
	public void convertAndSaveFile(Map<String,List<String>> map,String[] srcFiles, String[] destFile, Map<String, Map<String, String>> convertMap, int srcIdField, String[] srcFields, String[] destFields, String[] destFieldsType) throws Exception {
		Map<String, Integer> srcFieldIndexMap = new HashMap<String, Integer>();
		for (int i = 0; i < srcFields.length; i++) {
			srcFieldIndexMap.put(srcFields[i], new Integer(i));
		}
		String fieldType = "";
		for (int i = 0; i < srcFiles.length; i++) {
			delFile(destFile[i]);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(srcFiles[i]), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String temp = "";
			List<String> list = new ArrayList<String>();
			while ((temp = br.readLine()) != null) {
				if (!temp.equals("")) {
						String[] tempArray = temp.split(",");
						if (map.containsKey(tempArray[0])) {
							List<String> tempList = map.get(tempArray[0]);
							for (int m = 0; m < tempList.size(); m++) {
								tempArray[0] = tempList.get(m);
								Map<String, String> tempConvertMap = convertMap.get(tempArray[srcIdField]);
								String tempStr = "";
								for (int j = 0; j < destFields.length; j++) {
									if(destFieldsType[j].equals("String")){
										fieldType = "\"";
									}else{
										fieldType = "";
									}
									if (j == 0) {
										if (srcFieldIndexMap.containsKey(destFields[j])) {
											tempStr = tempStr + fieldType + tempArray[srcFieldIndexMap.get(destFields[j])]+ fieldType;
										} else {
											tempStr = tempStr + fieldType + tempConvertMap.get(destFields[j]) + fieldType;
										}
									} else {
										if (srcFieldIndexMap.containsKey(destFields[j])) {
											tempStr = tempStr + "," + fieldType + tempArray[srcFieldIndexMap.get(destFields[j])] + fieldType;
										} else {
											tempStr = tempStr + "," + fieldType + tempConvertMap.get(destFields[j]) + fieldType;
										}
									}
								}
								list.add(tempStr);
								if (list.size() == 10000) {
									saveFileListAppendNoCheck(list, destFile[i]);
									list.clear();
								}
							}
						}
					}

			}
			if(list.size()!=0) {
				saveFileListAppendNoCheck(list, destFile[i]);
			}
		}
	}

	/**
	 * 保存Set<String>到文件末尾
	 * @param set 字符串Set
	 * @param filepath 文件路径
	 * @throws Exception
	 */
	public void saveFileSetAppend(Set<String> set,String filepath) throws Exception {
		String content = "";
		int index = 0;
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			content = content + it.next() + "\n";
			index ++;
			if(index==100)
			{
				this.appendMethod(filepath,content);
				index = 0;
				content = "";
			}
		}
		this.appendMethod(filepath,content);
	}
	/**
	 * 保存文件
	 * @param value String[]数据格式
	 * @param filepath 保存文件路径
	 * @throws Exception
	 */
	public void saveFileFromArray(String[] value,String filepath) throws Exception {
		String content = "";
		for (int i = 0; i < value.length; i++) {
			content = content + value[i] + "\n";
		}
		saveFile(content,filepath,"GBK");
	}

	public List<File> getFileList(String path) throws Exception {
		File f = new File(path);
		List<File> list = new ArrayList<File>();
		String[] temp = f.list();
		for (int i = 0; i < temp.length; i++) {
			File file = new File(f.getAbsolutePath() + "/" + temp[i]);
			if (file.isFile()) {
				list.add(file);
			}
		}
		return list;
	}
	public List<Map<String,String>> getFileInfoList(String path) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		File f = new File(path);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		String[] temp = f.list();
		if (temp == null) {
			return null;
		}
		for (int i = 0; i < temp.length; i++) {
			File file = new File(f.getAbsolutePath() + "/" + temp[i]);
			if (file.isFile()) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("name", file.getName());
				map.put("absolutePath", file.getAbsolutePath());
				map.put("time", sf.format(new Date(file.lastModified())));
				list.add(map);
			}
		}
		return list;
	}
	public void reNameFolder(String path,String newname) throws Exception {
		File f=new File(path);
		f.renameTo(new File(f.getParentFile().getAbsolutePath()+"/"+newname));
	}
	public List getChildFolder(String path) throws Exception {
		List list=new ArrayList();
		File f=new File(path);
		String[] temp=f.list();

		for (int i = 0; i < temp.length; i++) {
			File file=new File(f.getAbsolutePath()+"/"+temp[i]);

			if(file.isDirectory())
			{

				list.add(f.getAbsolutePath()+"/"+temp[i]);
			}


		}

		return list;


	}
	public List getChildFolderName(String path) throws Exception {
		List list=new ArrayList();
		File f=new File(path);
		String[] temp=f.list();
		for (int i = 0; i < temp.length; i++) {
			File file=new File(f.getAbsolutePath()+"/"+temp[i]);

			if(file.isDirectory())
			{

				list.add(temp[i]);
			}


		}
		return list;


	}

	public List getChildFile(String path) throws Exception {
		List list=new ArrayList();
		File f=new File(path);
		String[] temp=f.list();
		for (int i = 0; i < temp.length; i++) {
			File file=new File(f.getAbsolutePath()+"/"+temp[i]);

			if(file.isFile())
			{

				list.add(f.getAbsolutePath()+"/"+temp[i]);
			}


		}
		return list;


	}
	public List getChildFileName(String path) throws Exception {
		List list=new ArrayList();
		File f=new File(path);
		String[] temp=f.list();
		for (int i = 0; i < temp.length; i++) {
			File file=new File(f.getAbsolutePath()+"/"+temp[i]);

			if(file.isFile())
			{

				list.add(temp[i]);
			}


		}
		return list;


	}
	public void saveFile(String content, String filepath, String encoding) throws IOException {
		File f = new File(filepath);
		String path = f.getParent();
		f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		f = new File(filepath);
		FileOutputStream fileoutstream;
		OutputStreamWriter outputstreamwriter;
		BufferedWriter bufferedwriter;
		File file = new File(filepath);
		fileoutstream = new FileOutputStream(file);
		outputstreamwriter = new OutputStreamWriter(fileoutstream, encoding);
		bufferedwriter = new BufferedWriter(outputstreamwriter);
		bufferedwriter.write(content);
		bufferedwriter.close();
		outputstreamwriter.close();
		fileoutstream.close();
	}
	
	public void saveAppendFile(String content, String filepath, String encoding) throws IOException {
		File f = new File(filepath);
		String path = f.getParent();
		f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		f = new File(filepath);
		FileOutputStream fileoutstream;
		OutputStreamWriter outputstreamwriter;
		BufferedWriter bufferedwriter;
		File file = new File(filepath);
		fileoutstream = new FileOutputStream(file, true);
		outputstreamwriter = new OutputStreamWriter(fileoutstream, encoding);
		bufferedwriter = new BufferedWriter(outputstreamwriter);
		bufferedwriter.write(content);
		bufferedwriter.close();
		outputstreamwriter.close();
		fileoutstream.close();
	}
	
	public void saveFile(String content, String filepath) throws Exception {
		try {
			File f = new File(filepath);
			String path = f.getParent();
			f = new File(path);
			if (!f.exists()) {
				f.mkdirs();
			}
			f = new File(filepath);

			f.createNewFile();
			FileWriter fr = new FileWriter(f);
			fr.write(content);
			fr.flush();
			fr.close();
		} catch (Exception e) {
			System.out.println(e.getMessage() + " " + filepath);
		}

	}


	public void moveFile(String sourcepath, String targetpath) {
		File f = new File(targetpath);
		if (f.exists())
			f.delete();
		String path = f.getParent();
		f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		f = new File(sourcepath);
		f.renameTo(new File(targetpath));
		f.delete();
	}


	public String readFile(String path) throws Exception {
			if(!new File(path).exists())
		{
			return "";
		}
		FileReader input = new FileReader(path);
		BufferedReader br = new BufferedReader(input);
		String temp = "";
		String content = "";
		while ((temp = br.readLine()) != null) {
			content = content + temp + "\n";
		}
		br.close();
		input.close();
		return content;
	}
	public String readFileNotN(String path) throws Exception {
		if(!new File(path).exists())
	{
		return "";
	}
	InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
	BufferedReader br = new BufferedReader(isr);
	String temp = "";
	String content = "";
	while ((temp = br.readLine()) != null) {
		content = content + temp.trim() ;
	}
	return content;
}
	public String readFileNotNAnsi(String path) throws Exception {
	/*	if(!new File(path).exists())
	{
		return "";
	}*/
		FileInputStream fs=	new FileInputStream(path);
	InputStreamReader isr = new InputStreamReader(fs);
	BufferedReader br = new BufferedReader(isr);
	String temp = "";
	String content = "";
	while ((temp = br.readLine()) != null) {
		content = content + temp.trim() ;
	}
	br.close();
	isr.close();
	fs.close();
	return content;
}

	public List<String> readFileContentList(String path) throws Exception {
		List<String> list = new ArrayList<String>();
		FileReader input = new FileReader(path);
		BufferedReader br = new BufferedReader(input);
		String temp = "";
		while ((temp = br.readLine()) != null) {
			if (!temp.equals("")) {
				list.add(temp);
			}
		}
		br.close();
		input.close();
		return list;
	}
	public List<String> readFileContentList(String path, String charset) throws Exception {
		List<String> list = new ArrayList<String>();
		InputStreamReader isr = new InputStreamReader(new FileInputStream(path), charset);
		BufferedReader br = new BufferedReader(isr);
		String temp = "";
		while ((temp = br.readLine()) != null) {
			if (!temp.equals("")) {
				list.add(temp);
			}
		}
		br.close();
		isr.close();
		return list;
	}
	public List<String> readFileContentListWithEndSplit(String path, String endSplit) throws Exception {
		List<String> list = new ArrayList<String>();
		FileReader input = new FileReader(path);
		BufferedReader br = new BufferedReader(input);
		String temp = "";
		String tempStr = "";
		while ((temp = br.readLine()) != null) {
			if (!temp.equals("")) {
				if(temp.indexOf(endSplit)!=-1){
					tempStr = tempStr + temp.replace(";", "");
					list.add(tempStr);
					tempStr = "";
				}else{
					tempStr = tempStr + temp;
				}
			}
		}
		return list;
	}
	/**
	 * 一组文件复制到另外一个的目录下
	 * @param oldPath
	 * @param newPath
	 * @throws Exception
	 */
	public void copyFile(String[] oldPath, String newPath) throws Exception {
		for (int i = 0; i < oldPath.length; i++) {
			File f1 = new File(oldPath[i]);
			File f2 = new File(newPath + "/" + f1.getName());
			int length = 2097152;
			FileInputStream in = new FileInputStream(f1);
			FileOutputStream out = new FileOutputStream(f2);
			byte[] buffer = new byte[length];
			while (true) {
				int ins = in.read(buffer);
				if (ins == -1) {
					in.close();
					out.flush();
					out.close();
					break;
				} else
					out.write(buffer, 0, ins);
			}
		}
	}
	public void copyFile(String oldPath, String newPath) throws Exception {
		File f1 = new File(oldPath);
		File f2 = new File(newPath);

		int length = 2097152;
		FileInputStream in = new FileInputStream(f1);
		FileOutputStream out = new FileOutputStream(f2);
		byte[] buffer = new byte[length];

		while (true) {
			int ins = in.read(buffer);
			if (ins == -1) {
				in.close();
				out.flush();
				out.close();
				break;
			} else
				out.write(buffer, 0, ins);
		}

	}

	public void appendMethod(String fileName, String content) {
		try {
			File f = new File(new File(fileName).getParent());
			if (!f.exists()) {
				f.mkdirs();
			}
			f = new File(fileName);
			if (!f.exists()) {
				f.createNewFile();
			}

			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");


			long fileLength = randomFile.length();

			randomFile.seek(fileLength);
			randomFile.write(content.getBytes());
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void appendMethodNoCheck(String fileName, String content) {
		try {
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			randomFile.write(content.getBytes());
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean isExist(String path) throws Exception {
		File f = new File(path);
		return f.exists();
	}



	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath);
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void delFile(String path) {
		System.out.println("path="+path);
		if(path !=null) {
			File file = new File(path);
			file.delete();
		}
	}


	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);
				delFolder(path + "/" + tempList[i]);
				flag = true;
			}
		}
		return flag;
	}


	public static boolean delAllFile(String path, String suffix) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				if (temp.getName().split("\\.")[0].endsWith(suffix)) {
					temp.delete();
					if (temp.getParentFile().list().length == 0) {
						temp.getParentFile().delete();
					}
				}
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i],suffix);
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 解析异常显示内容.
	 * @param params 参数.
	 * @param contentmap txt中的规则.
	 * @param type 类型.
	 * @return 返回规则.
	 */
	public String tocontent(String [] params, Map<String, String> contentmap, String type) {
		String result = "";
		MessageFormat form = new MessageFormat(contentmap.get(type));
		result = form.format(params);
		return result;
	}
	/**
	 * 解析txt文件.
	 * @return Map规则.
	 */
	public Map<String, String> getcontent() {
		String HADataPath = this.getClass().getResource("/").getPath() + "HAData.txt";
		InputStreamReader isr2;
		Map contentmap = new HashMap();
		try {
			isr2 = new InputStreamReader(new FileInputStream(HADataPath), "GBK");
			BufferedReader bufferedreader = new BufferedReader(isr2);
			String line = "";
			while ((line = bufferedreader.readLine()) != null) {
				contentmap.put(line.split(";")[0], line.split(";")[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentmap;
	}
	/**
	 * 属性类型.
	 * @param size
	 * @return
	 */
	private static String[] getAtrributeTypes(int size) {
		String [] type = new String[size];
		for (int i = 0; i < type.length; i++) {
			type[i]="string";
		}
		return type;
	}

	/**
	 * 获取目录下文件列表
	 * 
	 * @param path 路径
	 * @return 文件路径列表
	 */
	public List<String> getFilePathList(String path) {
		
		List<String> resultList = new ArrayList<String>();
		File file = new File(path);
		
		// 路径不存在，返回
		if (!file.exists()) {
			return resultList;

		// 路径是文件，返回
		} else if (file.isFile()) {
			resultList.add(path);
			return resultList;
		}
		
		// 子路径列表
		String[] childFileList = file.list();
		
		for (int i = 0; i < childFileList.length; i++) {
			
			File childFile = new File(path, childFileList[i]);
			
			// 子路径是目录，再取子路径
			if (childFile.isDirectory()) {
				resultList.addAll(this.getFilePathList(childFile.getAbsolutePath()));

			// 子路径是文件，保存路径
			} else {
				resultList.add(childFile.getAbsolutePath());
			}
		}
		
		return resultList;
	}
	
	public static void main(String s[]) throws Exception {

		List<List<Map<String,String>>> pageList = new ArrayList<List<Map<String,String>>>();


		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "世界杯专区夺宝世界杯");
		map.put("filter", "/worldcup/raiderWorldCup/|/worldcup/raiderWorldCup/,/worldcup/raiderWorldCup/|/worldcup/raiderWorldCup/");
		list.add(map);
		map = new HashMap<String,String>();
		map.put("name", "世界杯专区今日世界杯");
		map.put("filter", "/worldcup/todayWorldCup/");
		list.add(map);
		map = new HashMap<String,String>();
		map.put("name", "世界杯专区竞猜世界杯");
		map.put("filter", "/worldcup/guessWorldCup/");
		list.add(map);
		map = new HashMap<String,String>();
		map.put("name", "世界杯专区决赛竞猜");
		map.put("filter", "worldcup/guessWorldCupTow/");
		list.add(map);
		map = new HashMap<String,String>();
		map.put("name", "世界杯专区全民世界杯");
		map.put("filter", "/worldcup/allWorldCup/");
		list.add(map);


		pageList.add(list);

		list = new ArrayList<Map<String,String>>();
		map = new HashMap<String,String>();
		map.put("name", "世界杯专区夺宝世界杯");
		map.put("filter", "/worldcup/raiderWorldCup/");
		list.add(map);
		map = new HashMap<String,String>();
		map.put("name", "世界杯专区今日世界杯");
		map.put("filter", "/worldcup/todayWorldCup/");
		list.add(map);
		pageList.add(list);

		list = new ArrayList<Map<String,String>>();
		map = new HashMap<String,String>();
		map.put("name", "世界杯专区夺宝世界杯");
		map.put("filter", "/worldcup/raiderWorldCup/");
		list.add(map);
		pageList.add(list);


		List<List<Map<String,String>>> pathList = new ArrayList<List<Map<String,String>>>();
		list = new ArrayList<Map<String,String>>();
		map = new HashMap<String,String>();
		map.put("name", "世界杯专区夺宝世界杯");
		map.put("filter", "/worldcup/raiderWorldCup/");
		list.add(map);
		map = new HashMap<String,String>();
		map.put("name", "世界杯专区今日世界杯");
		map.put("filter", "/worldcup/todayWorldCup/");
		list.add(map);
		pathList.add(list);


		list = new ArrayList<Map<String,String>>();
		map = new HashMap<String,String>();
		map.put("name", "世界杯专区夺宝世界杯");
		map.put("filter", "/worldcup/raiderWorldCup/");
		list.add(map);
		map = new HashMap<String,String>();
		map.put("name", "世界杯专区决赛竞猜");
		map.put("filter", "worldcup/guessWorldCupTow/");
		list.add(map);
		pathList.add(list);

		Map<String, List<List<Map<String,String>>>> data = new HashMap<String, List<List<Map<String,String>>>>();
		data.put("page", pageList);
		data.put("path", pathList);
		com.google.gson.Gson gson = new com.google.gson.Gson();

		Map<String, List<List<Map<String,String>>>> pageData = new HashMap<String, List<List<Map<String,String>>>>();
		data.put("page", pageList);
		data.put("path", pathList);


	}

}
