package com.ssafy.ssafit.model.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.model.dao.VideoReviewDao;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.dto.VideoBoard;
import com.ssafy.ssafit.model.dto.VideoReview;

@Service
public class VideoReviewServiceImpl implements VideoReviewService {

	private VideoReviewDao video;
	
	@Autowired
	public VideoReviewServiceImpl(VideoReviewDao video) {
		this.video = video;
	}
	
	// 해당 게시글의 비디오 리뷰들 가져오기
	@Override
	public List<VideoReview> selectAll(int video_board_id) {
		return video.selectAll(video_board_id);
	}

	// 리뷰 등록
	@Override
	@Transactional
	public int insert(VideoReview videoReview) {
		return video.insert(videoReview);
	}

	// 리뷰 등록했으면 해당 리뷰 내용 가져오기
	@Override
	public VideoReview selectAfterInsert(VideoReview review) {
		return video.selectAfterInsert(review);
	}

	
	// 리뷰 상세보기 위한 것(조회수 같이 업데이트)
	@Override
	public VideoReview selectOne(int video_no ,int review_no) {
		video.updateViewCount(review_no);
		return video.selectOne(video_no ,review_no);
	}
	
	@Override
	public void delete(int review_id) {
		video.delete(review_id);
		return;
	}
	
	@Override
	@Transactional
	public void update(VideoReview videoReview) {
		video.update(videoReview);
		return;
	}

	@Override
	public VideoReview selectOneNoUpdateViewCount(int review_id) {
		return video.selectOneNoUpdateViewCount(review_id);
	}



}
