package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.CrewBoardPost;

public interface CrewBoardPostService {

	// 크루 게시글 등록
	int regist(CrewBoardPost crewBoardPost);

	// 크루 게시글 가져오기
	public List<CrewBoardPost> getCrewBoardPost(int crew_id);

	// 특정 게시글 가져오기
	public CrewBoardPost getCrewBoardPost1(int crew_board_post_id);

	// 조회수 +1
	public void plusViewCnt(int crew_board_post_id);
}
