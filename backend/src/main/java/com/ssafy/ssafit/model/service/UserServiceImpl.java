package com.ssafy.ssafit.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.User;

/**
 * 빈으로 등록될 수 있도록 @Service를 선언한다.
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private UserDao udao;

	// 생성자 주입을 해준다.
	@Autowired
	public UserServiceImpl(UserDao udao) {
		this.udao = udao;
	}

	@Override
	public User select(String user_id, String user_pass) {
		return udao.select(user_id, user_pass);
	}

	@Override
	public User selectOne(String user_id) {
		return udao.selectOne(user_id);
	}

	@Override
	@Transactional
	public int regist(User user) {
		return udao.regist(user);
	}

	@Override
	public List<User> selectAll() {
		return udao.selectAll();
	}

	// 회원탈퇴
	@Override
	public void delete(String user_id, String user_pass) {
		Map<String, String> params = new HashMap<>();
	    params.put("user_id", user_id);
	    params.put("user_pass", user_pass);
	    System.out.println("123");
	    udao.delete(params);
	    return;
	}

	@Override
	public int addImage(User user) {
		return udao.addImgage(user);
	}

	@Override
	@Transactional
	public int modify(User user) {
		return udao.modify(user);
	}

	// 아이디 찾기
	@Override
	public User searchId(String user_name, String user_phone_number) {
		return udao.searchId(user_name, user_phone_number);
	}

	@Override
	public User getUserInfo(User user) {
		return udao.getUserInfo(user);
	}
	
	@Override
	public User checkId(String user_id) {
		return udao.checkId(user_id);
	}

	@Override
	public User checkEmail(String user_email) {
		return udao.checkEmail(user_email);
	}

	@Override
	public User checkPNum(String user_phone_number) {
		return udao.checkPNum(user_phone_number);
	}

}
