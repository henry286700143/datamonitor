package com.viewstar.jsdatamonitor.util.exception;

public class ExceptionFiliter {
	
	public String msg;
	
	public ExceptionFiliter() {
		super();
		this.msg = "";
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * 校验日期格式（2016-12-12）
	 * @param param
	 * @param value
	 * @return
	 */
	public ExceptionFiliter isTime(String param, String value) {
		String regex = "^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$";
		if(!value.matches(regex)) {
			msg = msg + param + "(格式不正确) ";
		}
		return this;
	}
	
	/**
	 * 校验路径格式
	 * @param param
	 * @param value
	 * @return
	 */
	public ExceptionFiliter isPath(String param, String value) {
		String regex = "(^//.|^/|^[a-zA-Z])?:?/.+(/$)?/";
		if(!value.matches(regex)) {
			msg = msg + param + "(格式不正确) ";
		}
		return this;
	}

	/**
	 * 是否是数字格式
	 * @param param
	 * @param value
	 * @return
	 */
	public ExceptionFiliter isNumber(String param, String value) {
		String regex = "^[0-9]\\d*|0$";
		if(!value.matches(regex)) {
			msg = msg + param + "(格式不正确) ";
		}
		return this;
	}	
	
	public static void main(String[] args) {
		ExceptionFiliter ef = new ExceptionFiliter();
		System.out.println(ef.isNumber("userid", "02201742699").getMsg());
	}
}
