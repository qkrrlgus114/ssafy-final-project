package com.ssafy.ssafit.model.dto;

public class VideoReview {
	private int review_id;
	private int video_board_id;
	private String user_id;
	private String review_content;
	private String review_reg_date;
	
	public VideoReview() {
		
	}

	public VideoReview(int review_id, int video_board_id, String user_id, String review_content,
			String review_reg_date) {
		super();
		this.review_id = review_id;
		this.video_board_id = video_board_id;
		this.user_id = user_id;
		this.review_content = review_content;
		this.review_reg_date = review_reg_date;
	}

	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	public int getVideo_board_id() {
		return video_board_id;
	}

	public void setVideo_board_id(int video_board_id) {
		this.video_board_id = video_board_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public String getReview_reg_date() {
		return review_reg_date;
	}

	public void setReview_reg_date(String review_reg_date) {
		this.review_reg_date = review_reg_date;
	}

	@Override
	public String toString() {
		return "VideoReview [review_id=" + review_id + ", video_board_id=" + video_board_id + ", user_id=" + user_id
				+ ", review_content=" + review_content + ", review_reg_date=" + review_reg_date + "]";
	}
	
}
