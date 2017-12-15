package com.viewstar.jsdatamonitor.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * È¨ÏÞ¹ýÂËÆ÷
 *
 * @author liwei
 *
 */
public class SessionFilter implements Filter {

	private String ignorePage;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		boolean isValid = true;
		isValid = req.getSession(false) == null;
		String userid = (String) req.getSession().getAttribute("userid");
		String password = (String) req.getSession().getAttribute("password00");
		ServletContext application = req.getSession().getServletContext();
		String url = req.getRequestURI();
		int pos = url.lastIndexOf("/");
		url = url.substring(pos + 1).toLowerCase();
		if (url.equals("") || ignorePage.toLowerCase().indexOf(url.toLowerCase()) != -1) {
			chain.doFilter(request, response);
		} else {
			if (isValid) {
				if (!req.getRequestURL().toString().contains("/services/") && !req.getRequestURL().toString().toLowerCase().contains("install")) {
					req.getSession().getServletContext().getRequestDispatcher("/WEB-INF/views/analysis/personalhome/errorPage.jsp").forward(req, res);
				} else {
					chain.doFilter(request, response);
				}
			} else {

				if (userid != null && password != null) {
					String s = (String) application.getAttribute(userid);
					if (!s.equals(password)) {
						if (!req.getRequestURL().toString().toLowerCase().contains("index")) {
							//req.getSession().invalidate();
							req.getSession().getServletContext().getRequestDispatcher("/WEB-INF/views/analysis/personalhome/errorPage.jsp").forward(req, res);
						}

					}
				}
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ignorePage = "#~~#/checkLoginAuthUser#~~#/successLoginAuthUser/";
	}

	@Override
	public void destroy() {
	}
}
