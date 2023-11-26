package com.ssafy.ssafit.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.dto.Crew;
import com.ssafy.ssafit.model.dto.CrewBoard;
import com.ssafy.ssafit.model.dto.CrewMember;

public interface CrewService {

	// 크루 생성
	public int regist(Crew crew);

	// 크루들 가져오기
	public List<Crew> selectAll();

	// 크루 가져오기
	public Crew selectOne(int crew_id);

	// 크루 검색
	public List<Crew> searchCrew(String word);

	// 크루 아이디 불러오기
	public int getCrewId(Crew crew);

	// 크루 게시판 생성
	public int registCrewBoard(CrewBoard board);

	// 크루 멤버 추가
	public int registCrewMember(CrewMember member);

	// 크루 중복 가입 체크
	public CrewMember registCheck(Map<String, Object> map);

	// 크루 가입하면 인원 수 증가.
	public int plusCrewMember(int crew_id);

	// 크루 꽉차면 0으로 바꿈
	public int updateCrewRecruitStatus(int crew_id);

	// 내가 가입한 크루 아이디들 가져오기
	public List<Integer> getMyCrewId(String user_id);

	// 크루 아이디 리스트로 크루 가져오기
	public List<Crew> getMyCrews(int crew_id);
}
