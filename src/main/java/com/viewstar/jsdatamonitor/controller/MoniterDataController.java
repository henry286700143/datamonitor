package com.viewstar.jsdatamonitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MoniterDataController {

	
	@RequestMapping(value = "getLogListInfo")
	public ModelAndView getLogListInfo(@RequestParam("begintime") String begintime,  
            @RequestParam("endtime") String endtime, @RequestParam("age") int age) throws Exception {
		
		
		return new ModelAndView("analysis/upload");
	}
}
