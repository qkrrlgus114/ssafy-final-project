package com.ssafy.ssafit.model.dto;

public class LikeVideo {
	private int like_id;
	private String user_id;
	private int video_board_id;

	public LikeVideo() {
		// TODO Auto-generated constructor stub
	}

	public LikeVideo(int like_id, String user_id, int video_board_id) {
		super();
		this.like_id = like_id;
		this.user_id = user_id;
		this.video_board_id = video_board_id;
	}

	public int getLike_id() {
		return like_id;
	}

	public void setLike_id(int like_id) {
		this.like_id = like_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getVideo_board_id() {
		return video_board_id;
	}

	public void setVideo_board_id(int video_board_id) {
		this.video_board_id = video_board_id;
	}

	@Override
	public String toString() {
		return "LikeVideo [like_id=" + like_id + ", user_id=" + user_id + ", video_board_id=" + video_board_id + "]";
	}

	
}
