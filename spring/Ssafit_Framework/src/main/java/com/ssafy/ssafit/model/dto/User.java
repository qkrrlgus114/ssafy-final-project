package com.ssafy.ssafit.model.dto;

public class User {
	private String user_id;
	private String user_pass;
	private String user_name;
	private String user_phone_number;
	private String user_email;
	private int user_athletic_career;
	private String user_profile_img;
	private int user_is_admin;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String user_id, String user_pass, String user_name, String user_phone_number, String user_email,
			int user_athletic_career, String user_profile_img, int user_is_admin) {
		super();
		this.user_id = user_id;
		this.user_pass = user_pass;
		this.user_name = user_name;
		this.user_phone_number = user_phone_number;
		this.user_email = user_email;
		this.user_athletic_career = user_athletic_career;
		this.user_profile_img = user_profile_img;
		this.user_is_admin = user_is_admin;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_phone_number() {
		return user_phone_number;
	}

	public void setUser_phone_number(String user_phone_number) {
		this.user_phone_number = user_phone_number;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getUser_athletic_career() {
		return user_athletic_career;
	}

	public void setUser_athletic_career(int user_athletic_career) {
		this.user_athletic_career = user_athletic_career;
	}

	public String getUser_profile_img() {
		return user_profile_img;
	}

	public void setUser_profile_img(String user_profile_img) {
		this.user_profile_img = user_profile_img;
	}

	public int getUser_is_admin() {
		return user_is_admin;
	}

	public void setUser_is_admin(int user_is_admin) {
		this.user_is_admin = user_is_admin;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_pass=" + user_pass + ", user_name=" + user_name
				+ ", user_phone_number=" + user_phone_number + ", user_email=" + user_email + ", user_athletic_career="
				+ user_athletic_career + ", user_profile_img=" + user_profile_img + ", user_is_admin=" + user_is_admin
				+ "]";
	}

	
}
