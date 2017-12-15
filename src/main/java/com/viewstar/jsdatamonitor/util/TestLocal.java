package com.viewstar.jsdatamonitor.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TestLocal {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		File file = new File("C:/Users/henry/Desktop/pic");

		File[] filearray = file.listFiles();

		for (int i = 0; i < filearray.length; i++) {
			File tempfile = filearray[i];
			String filename = tempfile.getName();
//			if(filename.endsWith(".PNG")){
//				System.out.println();
//				tempfile.renameTo(new File(tempfile.getParent()+"/"+filename.replaceAll(".PNG", ".png")));
//			}
			System.out.println(filename);

			String gbk = new String(filename.getBytes( "GBK"));
			System.out.println(gbk);
			String unicode = new String(gbk.getBytes(),"UTF-8");
			System.out.println(unicode);
			String utf8 = new String(unicode.getBytes("UTF-8"));

			tempfile.renameTo(new File(tempfile.getParent()+"/"+utf8));
		}
	}
}
