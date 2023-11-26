package com.ssafy.ssafit.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.model.dao.CrewDao;
import com.ssafy.ssafit.model.dto.Crew;
import com.ssafy.ssafit.model.dto.CrewBoard;
import com.ssafy.ssafit.model.dto.CrewMember;

@Service
public class CrewServiceImpl implements CrewService {

	private CrewDao cdao;
	private ResourceLoader reLoader;

	@Autowired
	public CrewServiceImpl(CrewDao cdao, ResourceLoader reLoader) {
		this.cdao = cdao;
		this.reLoader = reLoader;
	}

	// 크루 생성
	@Override
	@Transactional
	public int regist(Crew crew) {
		return cdao.regist(crew);
	}

	// 크루들 가져오기
	@Override
	public List<Crew> selectAll() {
		return cdao.selectAll();
	}
	
	// 크루 가져오기
	@Override
	public Crew selectOne(int crew_id) {
		return cdao.selectOne(crew_id);
	}
	
	// 크루 검색
	@Override
	public List<Crew> searchCrew(String word) {
		return cdao.searchCrew(word);
	}
	
	// 크루 아이디 불러오기
	@Override
	public int getCrewId(Crew crew) {
		return cdao.getCrewId(crew);
	}
	
	// 크루 게시판 생성
	@Override
	@Transactional
	public int registCrewBoard(CrewBoard board) {
		return cdao.registCrewBoard(board);
	}
	
	// 크루 멤버 추가
	@Override
	@Transactional
	public int registCrewMember(CrewMember member) {
		return cdao.registCrewMember(member);
	}

	// 크루 중복 가입 방지
	@Override
	public CrewMember registCheck(Map<String, Object> map) {
		return cdao.registCheck(map);
	}

	// 크루 인원 수 증가
	@Override
	public int plusCrewMember(int crew_id) {
		return cdao.plusCrewMember(crew_id);
	}

	// 크루 인원 꽉차면 0으로 업데이트
	@Override
	public int updateCrewRecruitStatus(int crew_id) {
		return cdao.updateCrewRecruitStatus(crew_id);
	}

	// 내가 가입한 크루들 아이디 가져오기
	@Override
	public List<Integer> getMyCrewId(String user_id) {
		return cdao.getMyCrewId(user_id);
	}

	// 내가 가입한 크루들 가져오기
	@Override
	public List<Crew> getMyCrews(int crew_id) {
		return cdao.getMyCrews(crew_id);
	}


}
