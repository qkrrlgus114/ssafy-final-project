package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.VideoBoard;
import com.ssafy.ssafit.model.dto.VideoReview;

public interface VideoReviewDao {
	// 해당 비디오의 리뷰들을 조회
	List<VideoReview> selectAll(int video_board_id);

	// 리뷰 등록
	int insert(VideoReview videoReview);
	
	// 리뷰 등록했으면 해당 리뷰 내용 가져오기
	VideoReview selectAfterInsert(VideoReview review);
	
	// 해당 리뷰 아이디로 리뷰 가져오기
	VideoReview selectOneReview(int review_id);

	// 리뷰 선택
	VideoReview selectOne(int video_no ,int review_no);
	
	// 리뷰 삭제기 위한 메서드(삭제시 뷰 카운트 증가시키지 않음)
	VideoReview selectOneNoUpdateViewCount(int review_id);

	// 리뷰 조회수 올리기
	void updateViewCount(int review_no);

	// 리뷰 삭제
	void delete(int review_id);

	// 수정
	void update(VideoReview videoReview);
}
