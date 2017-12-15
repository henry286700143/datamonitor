package com.viewstar.jsdatamonitor.model;

public class Msg {
	private String state;
	
	private String content;

	public Msg(String state, String content) {
		super();
		this.state = state;
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
