package com.viewstar.jsdatamonitor.model;

public class AjaxResult {
	private String result;
	private Msg msg;
	
	public AjaxResult(String result, String state, String content) {
		this.result = result;
		this.msg = new Msg(state,content);
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Msg getMsg() {
		return msg;
	}

	public void setMsg(Msg msg) {
		this.msg = msg;
	}

}
