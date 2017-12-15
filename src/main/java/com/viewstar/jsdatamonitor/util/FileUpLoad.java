package com.viewstar.jsdatamonitor.util;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUpLoad {
	
	public void download(HttpServletResponse response,HttpServletRequest request, String path,
			String displayname) throws Exception {
		response.reset();
		response.setContentType("application/x-download");
		String filenamedownload =  path;
		String filenamedisplay = displayname;
		filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
		filenamedisplay = filenamedisplay.replace("+", "%20");
		response.addHeader("Content-Disposition", "attachment;filename=" + filenamedisplay);
		OutputStream output = null;
		FileInputStream fis = null;
		try {
			output = response.getOutputStream();
			fis = new FileInputStream(filenamedownload);
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				output.write(b, 0, i);
			}
			output.flush();
		} catch (Exception e) {
			System.out.println("Error!");
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
				fis = null;
			}
			if (output != null) {
				output.close();
				output = null;
			}
		}
	}
}
