package com.ssafy.ssafit.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.dto.Follow;

public interface FollowService {
	public int followUser(Map<String, Object> map);

	public void unfollowUser(Map<String, Object> map);

	public List<Follow> getFollowers(String id);

	public List<Follow> getFollowings(String id);
	
	public List<Follow> followCheck(Map<String, Object> map);
}
