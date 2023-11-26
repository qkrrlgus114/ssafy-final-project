package com.ssafy.ssafit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.VideoBoard;
import com.ssafy.ssafit.model.dto.VideoReview;
import com.ssafy.ssafit.model.service.VideoReviewService;
import com.ssafy.ssafit.model.service.VideoBoardService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/videos/reviews")
@CrossOrigin(origins = "http://localhost:8080") // 이거 추가해야 되더라...	
public class ReviewController {
	private VideoReviewService vrService;
	private ResourceLoader reLoaders;
	private VideoBoardService vService;

	@Autowired
	public ReviewController(VideoBoardService vService,VideoReviewService vrService, ResourceLoader reLoaders) {
		this.vrService = vrService;
		this.reLoaders = reLoaders;
		this.vService = vService;
	}
	
	// 해당 게시글의 리뷰 끌고오기
	@GetMapping("{video_board_id}")
	public ResponseEntity<?> selectAllReviews(@PathVariable int video_board_id){
		List<VideoReview> reviews = vrService.selectAll(video_board_id);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	// 리뷰 등록하기
	@PostMapping()
	@ApiOperation(value = "리뷰 등록하기")
	public ResponseEntity<?> registerReview(@RequestBody VideoReview review){
		int result = vrService.insert(review); // 리뷰 등록하고
		if(result==0) {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}else {
			VideoReview oneReivew = vrService.selectAfterInsert(review); // 해당 리뷰 내용 전부 가져오기
			return new ResponseEntity<>(oneReivew, HttpStatus.OK);			
		}
	}

	// 리뷰 상세 불러오기
	@GetMapping("/videos/{video_no}/reviews/{review_no}")
	@ApiOperation(value = "해당 영상의 리뷰 조회하기")
	public ResponseEntity<?> getReviewDetail(@PathVariable int review_no, @PathVariable int video_no) {
		VideoReview vr = vrService.selectOne(video_no ,review_no); // 해당 리뷰 번호로 조회
		return new ResponseEntity<>(vr, HttpStatus.OK);
	}

	// 리뷰 삭제하기
	@DeleteMapping("{review_id}")
	public ResponseEntity<?> deleteReview(@PathVariable int review_id){
		vrService.delete(review_id);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	
//	// 리뷰 수정하기
//	@PatchMapping("/review/{reviewNo}")
//	@ApiOperation(value = "리뷰 수정하기")
//	public ResponseEntity<?> updateReview(@PathVariable int reviewNo, String reviewTitle, String reviewContent, HttpSession session) {
//		if(session.getAttribute("loginUser")==null) { // 로그인 유저가 없으면 유저 없음 반화
//			return new ResponseEntity<>("no user", HttpStatus.BAD_REQUEST);
//		}
//		String userId = (String) session.getAttribute("loginUserId"); // 유저의 이름을 저장
//		VideoReview videoReview = vrService.selectOneNoUpdateViewCount(reviewNo); // 해당 리뷰를 불러옴(리뷰 조회수 업데이트 XXX)
//		if(videoReview.getUserId().equals(userId)) { // 리뷰의 작성자와 로그인 유저의 이름이 같으면
//			if (reviewContent != null) { // 바꿀 내용이 있으면 바꿈
//		        videoReview.setReviewContent(reviewContent);
//		    } // 바꿀 제목이 있으면 바꿈
//		    if (reviewTitle != null) {
//		        videoReview.setReviewTitle(reviewTitle);
//		    }
//		}else { // 이름이 다르면 유저 다름
//			return new ResponseEntity<>("User does not match", HttpStatus.BAD_REQUEST);
//		}
//		vrService.update(videoReview); // 업데이트 실행
//		return new ResponseEntity<>(videoReview, HttpStatus.OK);
//	}
}
