package com.ssafy.ssafit.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ssafit.model.dto.User;

public interface UserDao {
	// 해당 유저 조회(로그인 위해서)
	User select(String user_id, String user_pass);

	// 해당 유저 가져오기
	User selectOne(String user_id);

	// 등록
	int regist(User user);

	// 전체 유저 조회
	List<User> selectAll();

	// 유저 탈퇴
	void delete(Map<String, String> params);

	int addImgage(User user);

	// 유저 수정
	int modify(User user);

	// 아이디 찾기
	User searchId(String user_name, String user_phone_number);

	// 이메일 전송(비밀번호 찾기)
	User getUserInfo(User user);

	// 아이디 중복 확인
	User checkId(String user_id);

	// 이메일 중복 확인
	User checkEmail(String user_email);

	// 전화번호 중복 확인
	User checkPNum(String user_phone_number);
}
