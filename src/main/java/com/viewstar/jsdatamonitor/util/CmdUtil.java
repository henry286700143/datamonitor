package com.viewstar.jsdatamonitor.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.viewstar.jsdatamonitor.blogic.InstallBLogic;

/**
 * 执行命令
 *
 * @author liwei
 *
 */
public class CmdUtil {
	/**
	 * 命令缓存文件
	 */
	private String filepath;
	private FileUtil fu;
	public static List<String> cmdInfoList = new ArrayList<String>();
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public CmdUtil(String filepath) {
		this.filepath = filepath;
		this.fu = new FileUtil();
	}

	/**
	 * 执行SQL脚本文件
	 *
	 * @param filePath
	 *            SQL脚本文件
	 * @throws Exception
	 */
	public void cmdInSqlScriptFile(String filePath) throws Exception {
		cmdInfoList.clear();
		InstallBLogic installBLogic = (InstallBLogic) CustomBeanFactory.getBean("installBLogic");
		List<String> sqlList = fu.readFileContentListWithEndSplit(filePath, ";");
		for (int i = 0; i < sqlList.size(); i++) {
			try {
				cmdInfoList.add(0, sf.format(new Date()) + " 正在执行" + " : " + sqlList.get(i));
				installBLogic.installScript(sqlList.get(i));
				cmdInfoList.add(0, sf.format(new Date()) + " 执行结束" + " : " + sqlList.get(i));
			} catch (Exception e) {
				cmdInfoList.add(0, sf.format(new Date()) + " 执行出错" + " : " + sqlList.get(i));
			}
		}
	}

	/**
	 * 执行SQL命令查询
	 * @param filePath
	 * @throws Exception
	 */
	public void cmdInSql(String sql) throws Exception {
		//cmdInfoList.clear();
		InstallBLogic installBLogic = (InstallBLogic) CustomBeanFactory.getBean("installBLogic");


		try {
			cmdInfoList.add(0, sf.format(new Date()) + " 正在执行" + " : " + sql);
			List<Map<String, Object>> list = installBLogic.installexecuteSQL(sql);
			StringBuffer buffer = new StringBuffer("");
			List<String> keyList = new ArrayList<String>();

			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					Map<String, Object> map = list.get(0);
					Iterator<Entry<String, Object>> it = map.entrySet().iterator();
					while (it.hasNext()) {
						Entry<String, Object> next = it.next();
						String key = next.getKey();
						keyList.add(key);
						buffer.append(key).append(",").append(" ");
					}

					buffer.delete(buffer.length() - 2, buffer.length());
					buffer.append("\n");
				}

				Map<String, Object> mapv = list.get(i);

				for (int j = 0; j < keyList.size(); j++) {
					String value = mapv.get(keyList.get(j)).toString();
					buffer.append(value).append(",").append(" ");
				}

				buffer.delete(buffer.length() - 2, buffer.length());
				buffer.append("\n");

			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

			fu.saveFile(buffer.toString(), filepath + sdf.format(new Date()) + ".txt");

			cmdInfoList.add(0, sf.format(new Date()) + " 执行结束" + " : " + sql);
		} catch (Exception e) {
			e.printStackTrace();
			cmdInfoList.add(0, sf.format(new Date()) + " 执行出错" + " : " + sql);
		}

	}

	/**
	 * 执行Shell脚本文件
	 *
	 * @param filePath
	 *            Shell脚本文件
	 * @throws Exception
	 */
	public void cmdInFilePath(String filePath) throws Exception {
		cmdInfoList.clear();
		List<String> cmdList = fu.readFileContentList(filePath);
		for (int i = 0; i < cmdList.size(); i++) {
			try {
				cmdInfoList.add(0, sf.format(new Date()) + " 正在执行" + " : " + cmdList.get(i));
				cmdInFile(cmdList.get(i));
				cmdInfoList.add(0, sf.format(new Date()) + " 执行结束" + " : " + cmdList.get(i));
			} catch (Exception e) {
				cmdInfoList.add(0, sf.format(new Date()) + " 执行出错" + " : " + cmdList.get(i));
			}
		}
	}

	/**
	 * 执行Shell命令
	 * @param cmd
	 * @throws Exception
	 */
	public void cmdInFile(String cmd) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String filename = sf.format(new Date());
		String shell = "#!/bin/sh\n";
		shell = shell + cmd;
		File f = new File(filepath + "/" + filename);
		String path = f.getParent();
		f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		f = new File(filepath + "/" + filename);
		f.createNewFile();
		FileWriter fr = new FileWriter(f);
		fr.write(shell);
		fr.flush();
		fr.close();
		cmd("chmod 777 " + filepath + "/" + filename);
		cmd(filepath + "/" + filename);
		f.delete();
	}

	/**
	 * 执行Shell命令并返回执行的内容List
	 *
	 * @param cmd
	 *            Shell命令
	 * @throws Exception
	 */
	public List<String> cmdInFile(String cmd, String outputfile) throws Exception {
		cmd = cmd + ">" + outputfile;
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String filename = sf.format(new Date());
		String shell = "#!/bin/sh\n";
		shell = shell + cmd;
		File f = new File(filepath + "/" + filename);
		String path = f.getParent();
		f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		f = new File(filepath + "/" + filename);
		f.createNewFile();
		FileWriter fr = new FileWriter(f);
		fr.write(shell);
		fr.flush();
		fr.close();
		try {
			cmd("chmod 777 " + filepath + "/" + filename);
			cmd(filepath + "/" + filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		f.delete();
		FileReader input = new FileReader(outputfile);
		BufferedReader br = new BufferedReader(input);
		String temp = "";
		List<String> list = new ArrayList<String>();
		while ((temp = br.readLine()) != null) {
			list.add(temp);
		}
		return list;

	}

	public void cmd(String cmd) throws Exception {

		Process process = null;

		try {

			process = Runtime.getRuntime().exec(cmd);

			process.waitFor();

		} catch (Exception e) {

			process.getOutputStream().close();

			process.getInputStream().close();

			process.getErrorStream().close();

		}
	}

	public void cmdfromfile(String cmd, String parafile, String beginpath, String suffix) throws Exception {
		saveFileListToFile(beginpath, parafile, suffix);
		cmd(cmd + " " + parafile);
	}

	public void saveFileListToFile(String beginpath, String savefile, String suffix) {
		File file = new File(savefile);
		file.delete();
		saveFileList(beginpath, savefile, suffix);

	}

	public void saveFileList(String beginpath, String savefile, String suffix) {
		File dir = new File(beginpath);
		File[] files = dir.listFiles();
		if (files == null)
			return;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				saveFileList(files[i].getAbsolutePath(), savefile, suffix);
			} else {
				String strFileName = files[i].getAbsolutePath().toLowerCase();
				if (files[i].getName().indexOf(suffix) != -1) {
					appendMethod(savefile, strFileName + "\n");
				}
			}
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

}
