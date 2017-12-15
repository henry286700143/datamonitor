package com.viewstar.jsdatamonitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * 页面跳转
 * @author liwei
 *
 */
@Controller
public class PageController {

	/**
	 * 进入首页
	 * @return 首页
	 */
	@RequestMapping(value = "nav")
	public ModelAndView getNavPage(){
		return new ModelAndView("nav");
	}
}
