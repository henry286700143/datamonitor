package com.viewstar.jsdatamonitor.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public final class CustomBeanFactory {

	private static ApplicationContext ctx;


	public static ApplicationContext getContext() {

		ApplicationContext context =null;

		if (null == context && null == ctx) {
			ctx = new ClassPathXmlApplicationContext(new String[] {
					"classpath*:applicationContext*.xml"});
			return ctx;
		}

		else if (null == context && ctx != null) {
			return ctx;
		}

		else {
			return context;
		}
	}

	public static Object getBean(String beanName) {
		return getContext().getBean(beanName);
	}

	public static Object getBean(String[] file, String beanName) {
		ApplicationContext context = new ClassPathXmlApplicationContext(file);
		return context.getBean(beanName);
	}
	public static void publishEvent(ApplicationEvent evt) {
		getContext().publishEvent(evt);
	}
}
