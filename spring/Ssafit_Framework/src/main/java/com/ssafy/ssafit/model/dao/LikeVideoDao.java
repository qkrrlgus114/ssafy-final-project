package com.ssafy.ssafit.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.dto.LikeVideo;
import com.ssafy.ssafit.model.dto.VideoBoard;

public interface LikeVideoDao {

	// 모든 좋아요 가져오기
	public List<LikeVideo> selectAll();

	// 해당 비디오 좋아요 카운트+1
	int plusLikeCnt(int video_board_id);
	
	// 좋아요 누르기
	public int insert(LikeVideo likevideo);
	
	// 해당 비디오 좋아요 카운트-1
	int minusLikeCnt(int video_board_id);
	
	// 좋아요 취소하기
	public int delete(LikeVideo likevideo);

}
