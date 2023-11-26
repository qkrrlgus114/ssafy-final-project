package com.ssafy.ssafit.model.dto;

public class VideoBoard {
	private int video_board_id;
	private String video_board_content;
    private String video_board_title;
    private String youtube_url;
    private int video_board_view_cnt;
	private int video_board_like_cnt;
	private String video_board_reg_date;

	public VideoBoard() {
	}

	public VideoBoard(int video_board_id, String video_board_content, String video_board_title, String youtube_url,
			int video_board_view_cnt, int video_board_like_cnt, String video_board_reg_date) {
		super();
		this.video_board_id = video_board_id;
		this.video_board_content = video_board_content;
		this.video_board_title = video_board_title;
		this.youtube_url = youtube_url;
		this.video_board_view_cnt = video_board_view_cnt;
		this.video_board_like_cnt = video_board_like_cnt;
		this.video_board_reg_date = video_board_reg_date;
	}

	public int getVideo_board_id() {
		return video_board_id;
	}

	public void setVideo_board_id(int video_board_id) {
		this.video_board_id = video_board_id;
	}

	public String getVideo_board_content() {
		return video_board_content;
	}

	public void setVideo_board_content(String video_board_content) {
		this.video_board_content = video_board_content;
	}

	public String getVideo_board_title() {
		return video_board_title;
	}

	public void setVideo_board_title(String video_board_title) {
		this.video_board_title = video_board_title;
	}

	public String getYoutube_url() {
		return youtube_url;
	}

	public void setYoutube_url(String youtube_url) {
		this.youtube_url = youtube_url;
	}

	public int getVideo_board_view_cnt() {
		return video_board_view_cnt;
	}

	public void setVideo_board_view_cnt(int video_board_view_cnt) {
		this.video_board_view_cnt = video_board_view_cnt;
	}

	public int getVideo_board_like_cnt() {
		return video_board_like_cnt;
	}

	public void setVideo_board_like_cnt(int video_board_like_cnt) {
		this.video_board_like_cnt = video_board_like_cnt;
	}

	public String getVideo_board_reg_date() {
		return video_board_reg_date;
	}

	public void setVideo_board_reg_date(String video_board_reg_date) {
		this.video_board_reg_date = video_board_reg_date;
	}

	@Override
	public String toString() {
		return "VideoBoard [video_board_id=" + video_board_id + ", video_board_content=" + video_board_content
				+ ", video_board_title=" + video_board_title + ", youtube_url=" + youtube_url
				+ ", video_board_view_cnt=" + video_board_view_cnt + ", video_board_like_cnt=" + video_board_like_cnt
				+ ", video_board_reg_date=" + video_board_reg_date + "]";
	}

}