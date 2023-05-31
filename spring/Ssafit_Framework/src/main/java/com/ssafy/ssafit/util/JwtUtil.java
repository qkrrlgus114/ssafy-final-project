package com.ssafy.ssafit.util;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private static final String SALT = "FinalProject_park_song"; // 비밀키 지정(변하지 않게)

	// 토큰 생성
	// 인자로 키와 밸류가 넘어왔다라고만 처리 (한가지의 정보만 넣는다고 가정)
	public String createToken(String claimId, String data, String claimAdmin, int isAdmin) throws UnsupportedEncodingException {
		long expiration;
	    if (isAdmin == 1) {
	        // 어드민인 경우 유효 기간을 무한으로 설정
	        expiration = Long.MAX_VALUE;
	    } else {
	        // 어드민이 아닌 경우 유효 기간을 20분으로 설정
	        expiration = System.currentTimeMillis() +20*60*1000;
	    }
		return Jwts.builder()
			.setHeaderParam("alg", "HS256")
			.setHeaderParam("typ", "JWT") // 헤더 완료
			.setExpiration(new Date(expiration)) // 유효기간 25분
			.claim(claimId, data) // id claim
			.claim(claimAdmin, isAdmin) // admin claim
			.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8")) // 어떤 암호를 쓸지, 바이트배열 키, 서명완료
			.compact(); // 스트링타입으로 반환을 해주겠다.
	}
	
	// 유효성 검사
	public void valid(String token) throws Exception {
		Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
	}
	
	public String getUserIdFromToken(String token) throws UnsupportedEncodingException {
	    return Jwts.parser()
	        .setSigningKey(SALT.getBytes("UTF-8"))
	        .parseClaimsJws(token)
	        .getBody()
	        .get("id", String.class);
	}
	
	public Integer getUserAdminFromToken(String token) throws UnsupportedEncodingException {
	    return Jwts.parser()
	        .setSigningKey(SALT.getBytes("UTF-8"))
	        .parseClaimsJws(token)
	        .getBody()
	        .get("isAdmin", Integer.class);
	}
}
