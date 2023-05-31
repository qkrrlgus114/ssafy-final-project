package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.CrewBoardPost;
import com.ssafy.ssafit.model.service.CrewBoardPostService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/crews/board/post")
@CrossOrigin(origins = "http://localhost:8080") // 이거 추가해야 되더라...
public class CrewBoardPostController {
	
	private CrewBoardPostService cbpService;
	
	@Autowired
	public CrewBoardPostController(CrewBoardPostService cbpService) {
		this.cbpService = cbpService;
	}

	// 게시글 등록
	@PostMapping
	public ResponseEntity<?> registPost(@RequestBody CrewBoardPost post){
		int result = cbpService.regist(post);
		if(result==0) {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>("OK", HttpStatus.OK);
		}
	}
	

	// 크루id로 게시글들 찾기
	@GetMapping("{crew_id}")
	public ResponseEntity<?> getCrewBoardPost(@PathVariable int crew_id){
		try {
			List<CrewBoardPost> list = cbpService.getCrewBoardPost(crew_id); 
			if(list.size()==0) {
				return new ResponseEntity<>("NO", HttpStatus.OK);
			}else {
				return new ResponseEntity<>(list, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	// 크루보드포스트id로 게시글 찾기(조회수+1)
	@GetMapping("get/{crew_board_post_id}")
	public ResponseEntity<?> getCrewBoardPostOne(@PathVariable int crew_board_post_id){
		try {
			CrewBoardPost post = cbpService.getCrewBoardPost1(crew_board_post_id); 
			if(post == null) {
				return new ResponseEntity<>("NO", HttpStatus.BAD_REQUEST);
			}else {
				cbpService.plusViewCnt(post.getCrew_board_post_id());
				return new ResponseEntity<>(post, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("ERR", HttpStatus.BAD_REQUEST);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
