package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.model.dao.VideoBoardDao;
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.VideoBoard;

@Service
public class VideoBoardServiceImpl implements VideoBoardService {

	private VideoBoardDao videoDao;

	@Autowired
	public VideoBoardServiceImpl(VideoBoardDao videoDao) {
		this.videoDao = videoDao;
	}

	@Override
	public List<VideoBoard> selectAll() {
		return videoDao.selectAll();
	}

	@Override
	@Transactional
	public int insert(VideoBoard video) {
		return videoDao.insert(video);
	}

	@Override
	@Transactional
	public int update(VideoBoard video) {
		return videoDao.update(video);
	}

	// 비디오 삭제
	@Override
	public void delete(int video_board_id) {
		videoDao.delete(video_board_id);
		return;
	}

	@Override
	public VideoBoard selectOne(String youtube_url) {
		return videoDao.selectOne(youtube_url);
	}

	@Override
	public List<VideoBoard> search(SearchCondition condition) {
		return videoDao.search(condition);
	}

	@Override
	public VideoBoard selectOneNoUpdateViewCount(int video_board_id) {
		return videoDao.selectOneNoUpdateViewCount(video_board_id);
	}

	@Override
	public VideoBoard duplicationCheck(String youtube_url) {
		return videoDao.duplicationCheck(youtube_url);

	}

	@Override
	public VideoBoard selectOneVideo(int video_board_id) {
		videoDao.updateViewCnt(video_board_id);
		return videoDao.selectOneVideo(video_board_id);
	}

	@Override
	public List<VideoBoard> searchVideo(String word) {
		return videoDao.searchVideo(word);
	}

}
