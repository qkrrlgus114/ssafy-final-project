package com.ssafy.ssafit.model.dto;

public class CrewMember {
	private int crew_id;
	private String member_id;
	private String crew_join_content;
	
	public CrewMember() {
	}
	

	public CrewMember(int crew_id, String member_id, String crew_join_content) {
		super();
		this.crew_id = crew_id;
		this.member_id = member_id;
		this.crew_join_content = crew_join_content;
	}

	public int getCrew_id() {
		return crew_id;
	}

	public void setCrew_id(int crew_id) {
		this.crew_id = crew_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getCrew_join_content() {
		return crew_join_content;
	}

	public void setCrew_join_content(String crew_join_content) {
		this.crew_join_content = crew_join_content;
	}

	@Override
	public String toString() {
		return "CrewMember [crew_id=" + crew_id + ", member_id=" + member_id + ", crew_join_content="
				+ crew_join_content + "]";
	}
	

	
}
