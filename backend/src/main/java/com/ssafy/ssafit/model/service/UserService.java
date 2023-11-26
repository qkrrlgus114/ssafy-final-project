package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ssafit.model.dto.User;

public interface UserService {
	User select(String user_id, String user_pass);

	// 해당 유저 가져오기
	User selectOne(String user_id);

	int regist(User user);

	List<User> selectAll();

	void delete(String user_id, String user_pass);

	int addImage(User user);

	// 유저 수정
	int modify(User user);

	// 아이디 찾기
	User searchId(String user_name, String user_phone_number);

	// 비밀번호 재설정 할 때 불러올 유저 정보
	User getUserInfo(User user);

	// 아이디 중복 확인
	User checkId(String user_id);

	// 이메일 중복 확인
	User checkEmail(String user_email);

	// 휴대폰번호 중복 확인
	User checkPNum(String user_phone_number);
}
