package com.ssafy.ssafit.model.dto;

public class Crew {
	private int crew_id;
	private String crew_name;
	private String crew_leader;
	private String user_id;
	private String crew_thema;
	private String crew_create_date;
	private String crew_content;
	private int crew_max_members;
	private int crew_current_members;
	private String crew_img;
	private String crew_join_content;
	private int crew_recruit_status;
	public Crew() {
		// TODO Auto-generated constructor stub
	}
	public Crew(int crew_id, String crew_name, String crew_leader, String user_id, String crew_thema,
			String crew_create_date, String crew_content, int crew_max_members, int crew_current_members,
			String crew_img, String crew_join_content, int crew_recruit_status) {
		super();
		this.crew_id = crew_id;
		this.crew_name = crew_name;
		this.crew_leader = crew_leader;
		this.user_id = user_id;
		this.crew_thema = crew_thema;
		this.crew_create_date = crew_create_date;
		this.crew_content = crew_content;
		this.crew_max_members = crew_max_members;
		this.crew_current_members = crew_current_members;
		this.crew_img = crew_img;
		this.crew_join_content = crew_join_content;
		this.crew_recruit_status = crew_recruit_status;
	}
	public int getCrew_id() {
		return crew_id;
	}
	public void setCrew_id(int crew_id) {
		this.crew_id = crew_id;
	}
	public String getCrew_name() {
		return crew_name;
	}
	public void setCrew_name(String crew_name) {
		this.crew_name = crew_name;
	}
	public String getCrew_leader() {
		return crew_leader;
	}
	public void setCrew_leader(String crew_leader) {
		this.crew_leader = crew_leader;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCrew_thema() {
		return crew_thema;
	}
	public void setCrew_thema(String crew_thema) {
		this.crew_thema = crew_thema;
	}
	public String getCrew_create_date() {
		return crew_create_date;
	}
	public void setCrew_create_date(String crew_create_date) {
		this.crew_create_date = crew_create_date;
	}
	public String getCrew_content() {
		return crew_content;
	}
	public void setCrew_content(String crew_content) {
		this.crew_content = crew_content;
	}
	public int getCrew_max_members() {
		return crew_max_members;
	}
	public void setCrew_max_members(int crew_max_members) {
		this.crew_max_members = crew_max_members;
	}
	public int getCrew_current_members() {
		return crew_current_members;
	}
	public void setCrew_current_members(int crew_current_members) {
		this.crew_current_members = crew_current_members;
	}
	public String getCrew_img() {
		return crew_img;
	}
	public void setCrew_img(String crew_img) {
		this.crew_img = crew_img;
	}
	public String getCrew_join_content() {
		return crew_join_content;
	}
	public void setCrew_join_content(String crew_join_content) {
		this.crew_join_content = crew_join_content;
	}
	public int getCrew_recruit_status() {
		return crew_recruit_status;
	}
	public void setCrew_recruit_status(int crew_recruit_status) {
		this.crew_recruit_status = crew_recruit_status;
	}
	@Override
	public String toString() {
		return "Crew [crew_id=" + crew_id + ", crew_name=" + crew_name + ", crew_leader=" + crew_leader + ", user_id="
				+ user_id + ", crew_thema=" + crew_thema + ", crew_create_date=" + crew_create_date + ", crew_content="
				+ crew_content + ", crew_max_members=" + crew_max_members + ", crew_current_members="
				+ crew_current_members + ", crew_img=" + crew_img + ", crew_join_content=" + crew_join_content
				+ ", crew_recruit_status=" + crew_recruit_status + "]";
	}
	
}
