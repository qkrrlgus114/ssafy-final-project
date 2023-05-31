package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.VideoBoard;

public interface VideoBoardDao {
	// 전체 비디오 조회
	List<VideoBoard> selectAll();

	// 비디오 등록
	int insert(VideoBoard video);

	// 비디오 삭제
	void delete(int video_board_id);

	// 비디오 수정
	int update(VideoBoard video);

	// 해당 유튜브의 정보와 그 유튜브의 리뷰들도 불러옴.
	VideoBoard selectOne(String youtube_url);

	// 해당 유튜브 영상 불러옴
	VideoBoard selectOneVideo(int video_board_id);

	// 해당 유튜브 영상 조회수 업데이트 하지 않고 불러옴
	VideoBoard selectOneNoUpdateViewCount(int video_board_id);

	// 검색기능
	public List<VideoBoard> search(SearchCondition condition);
	public List<VideoBoard> searchVideo(String word);

	// 조회수 올리기(뷰카운트)
	public void updateViewCnt(int video_board_id);

	// 비디오 중복 등록 확인
	public VideoBoard duplicationCheck(String youtube_url);

}
