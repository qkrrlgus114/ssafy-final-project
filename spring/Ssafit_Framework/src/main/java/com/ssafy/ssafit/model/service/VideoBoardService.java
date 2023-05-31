package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.VideoBoard;

public interface VideoBoardService {

	// 모든 비디오 가져오기
	List<VideoBoard> selectAll();

	// 비디오 추가
	int insert(VideoBoard video);

	// 비디오 수정
	int update(VideoBoard video);

	// 비디오 삭제
	void delete(int video_board_id);

	// url로 비디오 가져오기
	VideoBoard selectOne(String youtube_url);

	// 해당 유튜브 영상 불러옴
	VideoBoard selectOneVideo(int video_board_id);

	// 비디오 하나 가져오기(뷰카운트 업데이트 X)
	VideoBoard selectOneNoUpdateViewCount(int video_board_id);

	// 검색기능
	public List<VideoBoard> search(SearchCondition condition);
	public List<VideoBoard> searchVideo(String word);

	// 비디오 중복 등록 확인
	public VideoBoard duplicationCheck(String youtube_url);

}
