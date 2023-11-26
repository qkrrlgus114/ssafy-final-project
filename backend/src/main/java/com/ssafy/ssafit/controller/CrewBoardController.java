package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.CrewBoardPost;
import com.ssafy.ssafit.model.service.CrewBoardService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/crews/board")
@CrossOrigin(origins = "http://localhost:8080") // 이거 추가해야 되더라...
public class CrewBoardController {
	
	private CrewBoardService cbService;
	
	@Autowired
	public CrewBoardController(CrewBoardService cbService) {
		this.cbService = cbService;
	}

	@GetMapping("{crew_id}")
	@ApiOperation(value = "크루 게시판 번호 가져오기")
	public ResponseEntity<?> getCrewBoardId(@PathVariable int crew_id){
		int crew_board_id = cbService.getCrewBoardId(crew_id);
		if (crew_board_id != 0) {
			return new ResponseEntity<>(crew_board_id, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}
	}
	

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
