package com.viewstar.jsdatamonitor.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	private String encode;

	private String ignorePage;

	private String gbkPage;

	private String actionPage;

	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		String url = request.getRequestURI();
		int pos = url.lastIndexOf("/");

		url = url.substring(pos + 1).toLowerCase();

		if(ignorePage.indexOf(url)!=-1)
		{

		}
			else if(request.getMethod().equalsIgnoreCase("get"))
		{

				if(url.lastIndexOf("jpg")!=-1){
					request.setCharacterEncoding("UTF-8");
					response.setCharacterEncoding("UTF-8");
				}
				if(url.equalsIgnoreCase("getRecommendByUser")){
					request.setCharacterEncoding("UTF-8");
					response.setCharacterEncoding("UTF-8");
				}
		}
			else if(gbkPage.toLowerCase().indexOf(url)!=-1)
		{

			request.setCharacterEncoding("gbk");
			response.setCharacterEncoding("gbk");
		}
			else
		{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		}

		arg2.doFilter(request, response);

	}

	public void init(FilterConfig arg0) throws ServletException {

		ignorePage = "#~~#/logindataimport.htm#~~#/logindataimport_processbar.jsp/";
		ignorePage = ignorePage+"#~~#/channelplaytimesdataimport.htm#~~#/channelplaytimesdataimport_processbar.jsp/";
		ignorePage = ignorePage+"#~~#/dataimport.htm#~~#/dataimport_processbar.jsp/#~~#";
		ignorePage = ignorePage+"#~~#/dataimport.htm#~~#/dataimport_processbar.jsp/#~~#";
		ignorePage = ignorePage+"#~~#/getFileList#~~#/getFileList/#~~#";
		gbkPage =gbkPage+"#~~#/gettest#~~#/";
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getIgnorePage() {
		return ignorePage;
	}

	public void setIgnorePage(String ignorePage) {
		this.ignorePage = ignorePage;
	}

}
