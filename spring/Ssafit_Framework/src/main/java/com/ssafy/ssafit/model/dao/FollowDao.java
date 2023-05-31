package com.ssafy.ssafit.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.dto.Follow;

public interface FollowDao {
	// 내가 팔로우 하겠다.
	public int insert(Map<String, Object> map);
	// 팔로우 끊겠다.
	public void delete(Map<String, Object> map);
	// 내가 팔로우 하는 사람들 목록 부르기
	public List<Follow> selectFollowings(String id);
	// 나를 팔로우 하는 사람들 목록 부르기
	public List<Follow> selectFollowers(String id);
	// 중복 팔로우 확인하기 위한 메서드
	public List<Follow> check(Map<String, Object> map);
}
