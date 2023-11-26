package com.ssafy.ssafit.model.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ssafy.ssafit.model.dto.VideoBoard;
import com.ssafy.ssafit.model.dto.VideoReview;

public interface VideoReviewService {

	List<VideoReview> selectAll(int video_board_id);
	
	// 리뷰 등록
	int insert(VideoReview videoReview);
	
	// 리뷰 등록했으면 해당 리뷰 내용 가져오기
	VideoReview selectAfterInsert(VideoReview review);
	
	VideoReview selectOne(int video_no ,int review_no);
	
	void delete(int review_id);
	
	void update(VideoReview videoReview);
	
	VideoReview selectOneNoUpdateViewCount(int review_id);
	
}
