package com.ssafy.ssafit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Follow;
import com.ssafy.ssafit.model.service.FollowService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class FollowController {
	
	private FollowService fService;
	private ResourceLoader reLoader;
	
	public FollowController(ResourceLoader reLoader, FollowService fService) {
		this.reLoader = reLoader;
		this.fService = fService;
	}
	
	// 해당 유저 팔로우
	@PostMapping("/user/follow/{id}")
	@ApiOperation(value = "해당 유저 팔로우")
	public ResponseEntity<?> followUserById(@PathVariable String id, HttpSession session){
		String currentUserId = (String) session.getAttribute("loginUserId");
		Map<String, Object> map = new HashMap<>();
		map.put("targetId", id);
		map.put("myId", currentUserId);
		List<Follow> check = fService.followCheck(map); // 이미 팔로우 했는지 중복 체크(근데 없어도 될듯)
		if(check.size()!=0) {
			return new ResponseEntity<>("exists following", HttpStatus.BAD_REQUEST);
		}else {
			int result = fService.followUser(map);
			if(result==0) {
				return new ResponseEntity<>("follow FAIL", HttpStatus.BAD_REQUEST);
			}else {
				return new ResponseEntity<>("OK", HttpStatus.OK);
			}
		}
	}
	
	// 해당 유저 언팔로우
	@DeleteMapping("/user/follow/{id}")
	@ApiOperation(value = "해당 유저 언팔로우")
	public ResponseEntity<?> unfollowUser(@PathVariable String id, HttpSession session){
		String currentUserId = (String) session.getAttribute("loginUserId");
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("myid", currentUserId);
		fService.unfollowUser(map);
		return new ResponseEntity<>("unfollow", HttpStatus.OK);
	}
	
	// 나를 팔로우 한 사람들 가져오기
	@GetMapping("/user/followers")
	@ApiOperation(value = "내 팔로워 가져오기")
	public ResponseEntity<?> getFollowers(HttpSession session){
		String currentUserId = (String) session.getAttribute("loginUserId");
		List<Follow> followersList = fService.getFollowers(currentUserId);
		if(followersList.size()==0) {
			return new ResponseEntity<>("You don't have any followers.", HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(followersList, HttpStatus.OK);
		}
	}
	
	// 내가 팔로우 한 사람들 가져오기
	@GetMapping("/user/followings")
	@ApiOperation(value = "내가 팔로우 하는 사람들 가져오기")
	public ResponseEntity<?> getFollowings(HttpSession session){
		String currentUserId = (String) session.getAttribute("loginUserId");
		List<Follow> followingsList = fService.getFollowings(currentUserId);
		if(followingsList.size()==0) {
			return new ResponseEntity<>("You don't have any followings.", HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(followingsList, HttpStatus.OK);
		}
	}
}
