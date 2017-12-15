package com.viewstar.jsdatamonitor.controller;

import java.io.BufferedReader;
import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.viewstar.jsdatamonitor.blogic.InstallBLogic;
import com.viewstar.jsdatamonitor.constants.Constants;
import com.viewstar.jsdatamonitor.util.CmdUtil;
import com.viewstar.jsdatamonitor.util.DateUtil;
import com.viewstar.jsdatamonitor.util.FileUpLoad;
import com.viewstar.jsdatamonitor.util.FileUtil;

/**
 * 系统安装
 *
 * @author liwei
 *
 */
@Controller
public class InstallController {
	SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMddHHmmss");

	@Resource
	private InstallBLogic installBLogic;

	/**
	 * 进入安装页面
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "install")
	public ModelAndView install(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = "resources/upload/install";
		FileUtil fu = new FileUtil();
		List<Map<String, String>> list = fu.getFileInfoList(request.getRealPath("/" + path));
		java.util.Collections.sort(list, new Comparator<Map<String, String>>() {
			@Override
			public int compare(Map<String, String> o1, Map<String, String> o2) {
				try {
					long time1 = Long.parseLong(sf2.format(sf1.parse(o1.get("time"))));
					long time2 = Long.parseLong(sf2.format(sf1.parse(o2.get("time"))));
					return time1 <= time2 ? 1 : -1;
				} catch (ParseException e) {
					return 0;
				}
			}
		});
		request.setAttribute("list", list);
		return new ModelAndView("install/install");
	}

	/**
	 * 上传安装文件
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "installFileUpload")
	public ModelAndView installFileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("gbk");
		String path = "resources/upload/install";
		String realPath = request.getServletContext().getRealPath("/" + path);
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(1024 * 1024 * 1024);
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		for (FileItem item : items) {
			if (!item.isFormField()) {
				File fullFile = new File(item.getName());
				String fullfilename = "";
				if (item.getName().lastIndexOf("\\") != -1) {
					fullfilename = item.getName().substring(item.getName().lastIndexOf("\\") + 1, item.getName().length());
				} else {
					fullfilename = fullFile.getName();
				}
				File uploadFile = new File(realPath, fullfilename);
				try {
					item.write(uploadFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		FileUtil fu = new FileUtil();
		List<Map<String, String>> list = fu.getFileInfoList(request.getRealPath("/" + path));
		java.util.Collections.sort(list, new Comparator<Map<String, String>>() {
			@Override
			public int compare(Map<String, String> o1, Map<String, String> o2) {
				try {
					long time1 = Long.parseLong(sf2.format(sf1.parse(o1.get("time"))));
					long time2 = Long.parseLong(sf2.format(sf1.parse(o2.get("time"))));
					return time1 <= time2 ? 1 : -1;
				} catch (ParseException e) {
					return 0;
				}
			}
		});
		request.setAttribute("list", list);
		return new ModelAndView("install/install");
	}

	/**
	 * 获取正在执行的安装信息
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getInstallInfo")
	public ModelAndView getInstallInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String content = request.getParameter("content");
		List<String> list = CmdUtil.cmdInfoList;
		String temp = "";
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).split(" : ")[0].equals(content.split(" : ")[0])) {
				break;
			}
			temp = list.get(i) + "1234567890" + temp;
		}
		if (!temp.equals("")) {
			temp = temp.substring(0, temp.length() - 10);
		}
		request.setAttribute("result", temp);
		return new ModelAndView("ajaxresult");
	}

	/**
	 * cmd 主页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cmd_index")
	public ModelAndView cmd_index (HttpServletRequest request, HttpServletResponse response) throws Exception {

		return new ModelAndView("install/cmd");
	}

	String shelltemplocation = "/home/testus/";

	/**
	 * 执行cmd命令
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cmd_execute")
	public ModelAndView cmd_execute (HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cmdtxt = request.getParameter("cmdtxt");
		String type = request.getParameter("type");


		File file = new File(shelltemplocation);

		if (!file.exists()) {
			file.mkdirs();
		}

		CmdUtil cmdutil = new CmdUtil(shelltemplocation);
		if (type.equals("1")) { // 常规模式
			cmdutil.cmd(cmdtxt);
		}

		if (type.equals("2")) { // 重定向模式
			cmdtxt = cmdtxt + " > " + shelltemplocation + sf2.format(new Date()) + ".txt";
			System.out.println(cmdtxt);
			cmdutil.cmdInFile(cmdtxt);
		}

		if (type.equals("3")) { // 重定向模式
			Pattern p =  Pattern.compile("(?i)where");
			Matcher matcher = p.matcher(cmdtxt);
			if (matcher.find()) {
				cmdtxt = cmdtxt + " and rownum <= 1000";
			} else {
				cmdtxt = cmdtxt + " where rownum <= 1000";
			}
			cmdutil.cmdInSql(cmdtxt);
		}

		request.setAttribute("result", "执行完毕");
		return new ModelAndView("ajaxresult");
	}

	/**
	 * 查看执行结果
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cmd_fileList")
	public ModelAndView cmd_fileList (HttpServletRequest request, HttpServletResponse response) throws Exception {

		File file = new File(shelltemplocation);

		if (!file.exists()) {
			file.mkdirs();
		}
		FileUtil fileUtil = new FileUtil();
		List<Map<String, String>> list = fileUtil.getFileInfoList(shelltemplocation);

		Map<String, String> map = new HashMap<String, String>();


		for (int i = 0; i < list.size(); i++) {

			for (int j = 1; j < list.size() - i; j++) {
				try {
					Map<String, String> map1 = list.get(j - 1);
					String name1 = map1.get("name");
					String realName1 = name1.substring(0, name1.length() - 4);
					Date time1 = sf2.parse(realName1);

					Map<String, String> map2 = list.get(j);
					String name2 = map2.get("name");
					String realName2 = name2.substring(0, name2.length() - 4);
					Date time2 = sf2.parse(realName2);

					if (time1.before(time2)) {
						map = map1;
						map1 = map2;
						map2 = map;
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
					// TODO: handle exception
				}

			}
		}

		Collections.reverse(list);

		request.setAttribute("list", list);
		return new ModelAndView("install/cmd_fileList");
	}

	/**
	 * 下载执行结果
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cmd_download")
	public ModelAndView cmd_download (HttpServletRequest request, HttpServletResponse response) throws Exception {

		String fileName = request.getParameter("fileName");
		FileUpLoad fileupload = new FileUpLoad();
		fileName = shelltemplocation + fileName;
		File file = new File(fileName);
		fileupload.download(response, request, fileName, file.getName());

		return new ModelAndView("ajaxresult");
	}

	/**
	 * 删除执行结果
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cmd_fileDel")
	public ModelAndView cmd_fileDel (HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = request.getParameter("fileName");

		fileName = shelltemplocation + fileName;
		File file = new File(fileName);

		if (file.exists()) {
			file.delete();
		}

		request.setAttribute("result", "删除成功！");
		return new ModelAndView("ajaxresult");
	}
}
