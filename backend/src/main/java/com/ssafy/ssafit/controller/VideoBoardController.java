package com.ssafy.ssafit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.VideoBoard;
import com.ssafy.ssafit.model.dto.VideoReview;
import com.ssafy.ssafit.model.service.VideoReviewService;
import com.ssafy.ssafit.util.JwtUtil;
import com.ssafy.ssafit.model.service.VideoBoardService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/videos")
@CrossOrigin(origins = "http://localhost:8080") // 이거 추가해야 되더라...
public class VideoBoardController {

	private JwtUtil jwtUtil;
	private VideoBoardService vService;
	private ResourceLoader reLoaders;
	private VideoReviewService vrService;

	@Autowired
	public VideoBoardController(VideoBoardService vService, ResourceLoader reLoaders, VideoReviewService vrService, JwtUtil jwtUtil) {
		this.vService = vService;
		this.reLoaders = reLoaders;
		this.vrService = vrService;
		this.jwtUtil = jwtUtil;
	}

	// 전체 비디오 목록 불러오기
	@GetMapping
	@ApiOperation(value = "전체 영상 조회")
	public ResponseEntity<?> getVideoListAll() {
		try {
			List<VideoBoard> videos = vService.selectAll(); // 영상을 전부 조회해서 반환
			if (videos != null && videos.size() > 0) {
				return new ResponseEntity<>(videos, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No videos", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

	// 영상 조건으로 검색하기
	@GetMapping("/search")
	@ApiOperation(value = "영상 조건 검색")
	public ResponseEntity<?> searchVideo(SearchCondition condition) {
		try {
			List<VideoBoard> videos = vService.search(condition); // 영상의 조건들을 받아서 해당 영상을 리스트에 담아 반환
			if (videos == null || videos.size() == 0) { // 조건에 없으면 없음.
				return new ResponseEntity<>("no videos", HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<List<VideoBoard>>(videos, HttpStatus.OK); // 아니면 반환
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

	// 비디오 등록하기(트랜잭션)
	@PostMapping
	@ApiOperation(value = "영상 등록")
	public ResponseEntity<?> videoRegist(@RequestBody VideoBoard video) {
		try {
			// 중복 등록 확인
			VideoBoard duplicationVideo = vService.duplicationCheck(video.getYoutube_url());
			// 반환값이 널이 아니면 이미 같은 url이 등록이 됐다는 소리
			if (duplicationVideo != null) {
				return new ResponseEntity<>("duplicationVideo", HttpStatus.BAD_REQUEST);
			}
			// 반환값이 null이면 등록해도 되니깐
			// 비디오 url부터 우선 저장
			int resultVideoCreate = vService.insert(video); // 해당 영상을 등록(트랜잭션 처리 해서 int로 반환)
			if (resultVideoCreate == 0) { // 0이면 하나도 등록이 안됐으니 실패
				System.out.println("등록실패");
				return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
			} else { // 비디오 등록 성공했으면
				System.out.println("등록성공");
				return new ResponseEntity<>("OK", HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 비디오 삭제하기
	@DeleteMapping("{video_board_id}")
	public ResponseEntity<?> deleteVideo(@PathVariable int video_board_id) {
		vService.delete(video_board_id);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	// 해당 비디오 상세보기
	@GetMapping("{video_board_id}")
	@ApiOperation(value = "영상 주소로 검색")
	public ResponseEntity<?> videoDetail(@PathVariable int video_board_id) {

//		Map<VideoBoard, List<VideoReview>> map = new HashMap<>(); // 맵을 하나 만듦
		VideoBoard video = vService.selectOneVideo(video_board_id); // 해당 번호의 비디오 꺼내옴
//		List<VideoReview> reviews = vrService.selectAll(video_id); // 비디오의 url을 이용해서 url이 같은 리뷰들을 리스트로 저장
//		map.put(video, reviews); // 맵에 넣어서 반환
//		return new ResponseEntity<>(map, HttpStatus.OK);
		if (video != null) {
			return new ResponseEntity<>(video, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("NO video", HttpStatus.BAD_REQUEST);
		}
	}

	// 영상 제목으로 검색하기
	@GetMapping("/search/{word}")
	@ApiOperation(value = "영상 제목 검색")
	public ResponseEntity<?> searchVideo(@PathVariable String word) {
		try {
			List<VideoBoard> videos = vService.searchVideo(word); // 영상의 조건들을 받아서 해당 영상을 리스트에 담아 반환
			if (word == "")
				videos = vService.selectAll();
			if (videos == null || videos.size() == 0) { // 조건에 없으면 없음.
				return new ResponseEntity<>("no videos", HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<List<VideoBoard>>(videos, HttpStatus.OK); // 아니면 반환
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 비디오 수정하기
	@PutMapping("/update")
	public ResponseEntity<?> updateVideo(@RequestBody VideoBoard video) {
		int result = vService.update(video);
		if (result == 0) {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(video, HttpStatus.OK);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
