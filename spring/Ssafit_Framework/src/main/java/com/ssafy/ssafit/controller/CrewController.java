package com.ssafy.ssafit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Crew;
import com.ssafy.ssafit.model.dto.CrewBoard;
import com.ssafy.ssafit.model.dto.CrewMember;
import com.ssafy.ssafit.model.service.CrewServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/crews")
public class CrewController {

	CrewServiceImpl cService;

	@Autowired
	public CrewController(CrewServiceImpl cService) {
		this.cService = cService;
	}

	// 크루 등록하기
	@PostMapping
	public ResponseEntity<?> registCrew(@RequestBody Crew crew) {
		// 이미지 넣어줘잉
		try {
			int result = cService.regist(crew);
			// 크루 등록
			if (result == 1) {
				int crew_id = getCrewId(crew);
				String crew_leader_id = crew.getUser_id();
				String crew_name = crew.getCrew_name();
				String crew_join_content = crew.getCrew_join_content();
				// 크루가 정상적으로 등록됐으면 크루 게시판만들고 리더 크루 멤버로 추가
				boolean bl1 = registCrewBoard(crew_id, crew_name);
				boolean bl2 = registCrewMember(crew_id, crew_leader_id, crew_join_content);
				// 게시판 등록 정상적으로 되고 멤버 추가 되면 crew 반환 => 해당 crewId의 게시판으로 보내자
				if (bl1 && bl2) {
//						Crew createCrew = crew;
					crew.setCrew_id(crew_id);
					return new ResponseEntity<>(crew, HttpStatus.OK);
				} else
					return new ResponseEntity<>("FAIL", HttpStatus.BAD_GATEWAY);
			} else {
				return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 크루들 가져오기
	@GetMapping
	@ApiOperation(value = "크루 목록")
	public ResponseEntity<?> selectAll() {
		try {
			List<Crew> crews = cService.selectAll();
			if (crews != null && crews.size() > 0) {
				return new ResponseEntity<>(crews, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 크루 가져오기
	@GetMapping("detail/{crew_id}")
	public ResponseEntity<?> selectOne(@PathVariable int crew_id) {
		try {
			Crew crew = cService.selectOne(crew_id);
			if (crew != null) {
				return new ResponseEntity<>(crew, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 크루 아이디 가져오기
	public int getCrewId(Crew crew) {
		int crew_id = cService.getCrewId(crew);
		return crew_id;
	}

	// 크루 게시판 생성
	public boolean registCrewBoard(int crew_id, String crew_name) {
		CrewBoard crew_board = new CrewBoard();
		crew_board.setCrew_id(crew_id);
		crew_board.setCrew_name(crew_name);
		int result = cService.registCrewBoard(crew_board);
		// 게시판 정상적으로 생성되면 true 반환
		if (result == 1)
			return true;
		else
			return false;
	}

	// 크루 멤버 추가
	public boolean registCrewMember(int crew_id, String user_id, String crew_join_content) {
		CrewMember member = new CrewMember(crew_id, user_id, crew_join_content);
		int result = cService.registCrewMember(member);
		// 크루 멤버 정상적으로 추가되면 true 반환
		if (result == 1)
			return true;
		else
			return false;
	}

	// 크루 키워드로 검색
	@GetMapping("/search/{word}")
	public ResponseEntity<?> searchCrew(@PathVariable String word) {
		try {
			List<Crew> crews = cService.searchCrew(word);
			if (crews != null && crews.size() > 0) {
				return new ResponseEntity<>(crews, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 크루 가입하기
	@PostMapping("/join/{crew_id}")
	@ApiOperation(value = "크루 가입")
	public ResponseEntity<?> joinCrew(@PathVariable int crew_id, String user_id, String crew_join_content) {
		CrewMember member = new CrewMember(crew_id, user_id, crew_join_content);
		int result = cService.registCrewMember(member);
		if (result == 0) {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		} else {
			int update = cService.plusCrewMember(crew_id);
			Crew crew = cService.selectOne(crew_id);
			if (crew.getCrew_max_members() == crew.getCrew_current_members()) {
				int result1 = cService.updateCrewRecruitStatus(crew_id);
				if (result1 == 1) {
					return new ResponseEntity<>("OK", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<>("OK", HttpStatus.OK);
		}
	}

	// 크루 중복 가입 확인
	@GetMapping("check/{crewId}")
	public ResponseEntity<?> crewCheck(@PathVariable int crewId, String userId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("crew_id", crewId);
		paramMap.put("user_id", userId);
		CrewMember crew = cService.registCheck(paramMap);
		if(crew != null) {
			return new ResponseEntity<>("FAIL", HttpStatus.OK);
		}else {
			Crew curCrew = cService.selectOne(crewId);
			if(curCrew.getCrew_current_members() == curCrew.getCrew_max_members()) {
				return new ResponseEntity<>("MAX", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("OK", HttpStatus.OK);
			}
		}
	}
	
	// 내 크루 가져오기
	@GetMapping("{user_id}")
	public ResponseEntity<?> getMyCrews(@PathVariable String user_id){
		List<Integer> crews_id = cService.getMyCrewId(user_id);
		if(crews_id.isEmpty()) {
			return new ResponseEntity<>("NO CREW", HttpStatus.OK);
		}else {
			List<Crew> crews = new ArrayList<Crew>();
			for(int i=0; i<crews_id.size(); i++) {
				crews.add((Crew) cService.getMyCrews(crews_id.get(i)).get(0));
			}
			return new ResponseEntity<>(crews, HttpStatus.OK);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
