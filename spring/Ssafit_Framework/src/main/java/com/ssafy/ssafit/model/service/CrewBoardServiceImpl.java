package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.CrewBoardDao;
import com.ssafy.ssafit.model.dto.CrewBoardPost;

@Service
public class CrewBoardServiceImpl implements CrewBoardService{
	
	private CrewBoardDao cbDao;
	
	@Autowired
	public CrewBoardServiceImpl(CrewBoardDao cbDao) {
		this.cbDao = cbDao;
	}

	@Override
	public int getCrewBoardId(int crew_id) {
		return cbDao.getCrewBoardId(crew_id);
	}

	

}
