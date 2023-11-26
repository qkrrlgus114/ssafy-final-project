package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.LikeVideo;
import com.ssafy.ssafit.model.dto.VideoBoard;
import com.ssafy.ssafit.model.service.LikeVideoService;
import com.ssafy.ssafit.model.service.VideoBoardService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/like")
@CrossOrigin(origins = "http://localhost:8080") // 이거 추가해야 되더라...	
public class LikeVideoController {

	private LikeVideoService lkService;
	private ResourceLoader reLoader;
	private VideoBoardService vService;

	@Autowired
	public LikeVideoController(LikeVideoService lkService, ResourceLoader reLoader, VideoBoardService vService) {
		this.lkService = lkService;
		this.reLoader = reLoader;
		this.vService = vService;
	}

	// 모든 좋아요 가져오기
	@GetMapping
	public ResponseEntity<?> selectAll() {
		List<LikeVideo> like = lkService.selectAll();
		return new ResponseEntity<>(like, HttpStatus.OK);
	}

	// 해당 비디오 좋아요 카운트 +1
	@PutMapping("plus/{video_board_id}")
	public ResponseEntity<?> plusLikeCount(@PathVariable int video_board_id) {
		// 좋아요 +1
		int result = lkService.plusLikeCnt(video_board_id);
		if (result == 1) {
			return new ResponseEntity<>("OK", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	// 좋아요 등록하기
	@PostMapping
	public ResponseEntity<?> likeVideo(@RequestBody LikeVideo likeVideo) {
		int result = lkService.insert(likeVideo);
		if (result == 1) {
			return new ResponseEntity<>("OK", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}
	}

	// 해당 비디오 좋아요 카운트 -1
	@PutMapping("minus/{video_board_id}")
	public ResponseEntity<?> minusLikeCount(@PathVariable int video_board_id) {
		// 좋아요 -1
		int result = lkService.minusLikeCnt(video_board_id);
		if (result == 1) {
			return new ResponseEntity<>("OK", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
	}

	// 좋아요 삭제하기
	@DeleteMapping
	public ResponseEntity<?> deleteVideo(LikeVideo likeVideo) {
		int result = lkService.delete(likeVideo);
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

}
