package com.ssafy.ssafit.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.model.dao.LikeVideoDao;
import com.ssafy.ssafit.model.dto.LikeVideo;
import com.ssafy.ssafit.model.dto.VideoBoard;

@Service
public class LikeVideoServiceImpl implements LikeVideoService {
	
	private LikeVideoDao vdao;
	
	@Autowired
	public LikeVideoServiceImpl(LikeVideoDao vdao) {
		this.vdao = vdao;
	}

	//모든 좋아요 가져오기
	@Override
	public List<LikeVideo> selectAll() {
		return vdao.selectAll();
	}

	
	// 해당 비디오 좋아요 +1
	public int plusLikeCnt(int video_board_id) {
		return vdao.plusLikeCnt(video_board_id);
	}

	// 좋아요 등록하기
	@Override
	public int insert(LikeVideo likevideo) {
		return vdao.insert(likevideo);
	}
	
	// 해당 비디오 좋아요 -1
	@Override
	public int minusLikeCnt(int video_board_id) {
		return vdao.minusLikeCnt(video_board_id);
	}
	
	//좋아요 취소하기
	@Override
	public int delete(LikeVideo likevideo) {
		return vdao.delete(likevideo);
	}




	
}
