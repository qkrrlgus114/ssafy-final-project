package com.ssafy.ssafit.model.dto;

public class CrewBoardPost {
	private int crew_board_post_id;
	private int crew_id;
	private String crew_board_post_title;
	private String crew_board_post_writer;
	private String crew_board_post_content;
	private String crew_board_post_img;
	private String crew_board_post_reg_date;
	private int crew_board_post_view_cnt;
	private int crew_board_post_like_cnt;
	
	
	public CrewBoardPost() {
	}


	public CrewBoardPost(int crew_board_post_id, int crew_id, String crew_board_post_title,
			String crew_board_post_writer, String crew_board_post_content, String crew_board_post_img,
			String crew_board_post_reg_date, int crew_board_post_view_cnt, int crew_board_post_like_cnt) {
		super();
		this.crew_board_post_id = crew_board_post_id;
		this.crew_id = crew_id;
		this.crew_board_post_title = crew_board_post_title;
		this.crew_board_post_writer = crew_board_post_writer;
		this.crew_board_post_content = crew_board_post_content;
		this.crew_board_post_img = crew_board_post_img;
		this.crew_board_post_reg_date = crew_board_post_reg_date;
		this.crew_board_post_view_cnt = crew_board_post_view_cnt;
		this.crew_board_post_like_cnt = crew_board_post_like_cnt;
	}


	public int getCrew_board_post_id() {
		return crew_board_post_id;
	}


	public void setCrew_board_post_id(int crew_board_post_id) {
		this.crew_board_post_id = crew_board_post_id;
	}


	public int getCrew_id() {
		return crew_id;
	}


	public void setCrew_id(int crew_id) {
		this.crew_id = crew_id;
	}


	public String getCrew_board_post_title() {
		return crew_board_post_title;
	}


	public void setCrew_board_post_title(String crew_board_post_title) {
		this.crew_board_post_title = crew_board_post_title;
	}


	public String getCrew_board_post_writer() {
		return crew_board_post_writer;
	}


	public void setCrew_board_post_writer(String crew_board_post_writer) {
		this.crew_board_post_writer = crew_board_post_writer;
	}


	public String getCrew_board_post_content() {
		return crew_board_post_content;
	}


	public void setCrew_board_post_content(String crew_board_post_content) {
		this.crew_board_post_content = crew_board_post_content;
	}


	public String getCrew_board_post_img() {
		return crew_board_post_img;
	}


	public void setCrew_board_post_img(String crew_board_post_img) {
		this.crew_board_post_img = crew_board_post_img;
	}


	public String getCrew_board_post_reg_date() {
		return crew_board_post_reg_date;
	}


	public void setCrew_board_post_reg_date(String crew_board_post_reg_date) {
		this.crew_board_post_reg_date = crew_board_post_reg_date;
	}


	public int getCrew_board_post_view_cnt() {
		return crew_board_post_view_cnt;
	}


	public void setCrew_board_post_view_cnt(int crew_board_post_view_cnt) {
		this.crew_board_post_view_cnt = crew_board_post_view_cnt;
	}


	public int getCrew_board_post_like_cnt() {
		return crew_board_post_like_cnt;
	}


	public void setCrew_board_post_like_cnt(int crew_board_post_like_cnt) {
		this.crew_board_post_like_cnt = crew_board_post_like_cnt;
	}


	@Override
	public String toString() {
		return "CrewBoardPost [crew_board_post_id=" + crew_board_post_id + ", crew_id=" + crew_id
				+ ", crew_board_post_title=" + crew_board_post_title + ", crew_board_post_writer="
				+ crew_board_post_writer + ", crew_board_post_content=" + crew_board_post_content
				+ ", crew_board_post_img=" + crew_board_post_img + ", crew_board_post_reg_date="
				+ crew_board_post_reg_date + ", crew_board_post_view_cnt=" + crew_board_post_view_cnt
				+ ", crew_board_post_like_cnt=" + crew_board_post_like_cnt + "]";
	}
	
		
}
