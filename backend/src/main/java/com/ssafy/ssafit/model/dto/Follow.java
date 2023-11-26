package com.ssafy.ssafit.model.dto;

public class Follow {
	private String to_user;
	private String from_user;
	
	public Follow() {
		// TODO Auto-generated constructor stub
	}
	
	public Follow(String to_user, String from_user) {
		super();
		this.to_user = to_user;
		this.from_user = from_user;
	}

	public String getTo_user() {
		return to_user;
	}

	public void setTo_user(String to_user) {
		this.to_user = to_user;
	}

	public String getFrom_user() {
		return from_user;
	}

	public void setFrom_user(String from_user) {
		this.from_user = from_user;
	}
	
	
	
}
