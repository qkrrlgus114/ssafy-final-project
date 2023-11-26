package com.ssafy.ssafit.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.FollowDao;
import com.ssafy.ssafit.model.dto.Follow;

@Service
public class FollowServiceImpl implements FollowService{

	private FollowDao fdao;
	private ResourceLoader reLoader;
	
	@Autowired
	public FollowServiceImpl(FollowDao fdao, ResourceLoader reLoader) {
		this.fdao = fdao;
		this.reLoader = reLoader;
	}
	
	
	@Override
	public int followUser(Map<String, Object> map) {
		return fdao.insert(map);
	}

	@Override
	public void unfollowUser(Map<String, Object> map) {
		fdao.delete(map);
	}

	@Override
	public List<Follow> getFollowers(String myId) {
		return fdao.selectFollowers(myId);
	}

	@Override
	public List<Follow> getFollowings(String myId) {
		return fdao.selectFollowings(myId);
	}


	@Override
	public List<Follow> followCheck(Map<String, Object> map) {
		return fdao.check(map);
	}

}
