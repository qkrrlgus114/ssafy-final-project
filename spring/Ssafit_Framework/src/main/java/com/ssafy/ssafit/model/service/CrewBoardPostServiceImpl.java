package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.CrewBoardDao;
import com.ssafy.ssafit.model.dao.CrewBoardPostDao;
import com.ssafy.ssafit.model.dto.CrewBoardPost;

@Service
public class CrewBoardPostServiceImpl implements CrewBoardPostService {

	private CrewBoardPostDao cbpDao;

	@Autowired
	public CrewBoardPostServiceImpl(CrewBoardPostDao cbpDao) {
		this.cbpDao = cbpDao;
	}

	// 크루 게시글 등록
	@Override
	public int regist(CrewBoardPost crewBoardPost) {
		return cbpDao.regist(crewBoardPost);
	}

	// 크루 게시글 가져오기
	@Override
	public List<CrewBoardPost> getCrewBoardPost(int crew_id) {
		return cbpDao.getCrewBoardPost(crew_id);
	}

	// 특정 게시글 가져오기
	@Override
	public CrewBoardPost getCrewBoardPost1(int crew_board_post_id) {
		return cbpDao.getCrewBoardPost1(crew_board_post_id);
	}

	@Override
	public void plusViewCnt(int crew_board_post_id) {
		cbpDao.plusViewCnt(crew_board_post_id);
		return;
	}

}
