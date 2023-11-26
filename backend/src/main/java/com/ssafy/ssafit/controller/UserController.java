package com.ssafy.ssafit.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.UserService;
import com.ssafy.ssafit.util.JwtUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8080") // 이거 추가해야 되더라...
public class UserController {

	private JwtUtil jwtUtil;
	private UserService uService;
	private ResourceLoader reLoaders;

	@Autowired
	public UserController(UserService uService, ResourceLoader reLoaders, JwtUtil jwtUtil) {
		this.uService = uService;
		this.reLoaders = reLoaders;
		this.jwtUtil = jwtUtil;
	}

	// 모든 유저 가져오기
	@GetMapping
	@ApiOperation(value = "모든 유저 조회")
	public ResponseEntity<?> userAll() {
		try {
			List<User> users = uService.selectAll();
			if (users != null && users.size() > 0) {
				return new ResponseEntity<List<User>>(users, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 해당 유저 가져오기
	@GetMapping("{user_id}")
	@ApiOperation(value = "유저 상세 정보")
	public ResponseEntity<?> getUserInfo(@PathVariable String user_id) {
		User loginUser = uService.selectOne(user_id); // id로 유저 정보 하나 꺼내옴
		if (loginUser != null) {
			return new ResponseEntity<User>(loginUser, HttpStatus.OK); // 그거 반환시켜
		} else {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}
	}

	// 로그인하기
	@PostMapping("/login")
	@ApiOperation(value = "로그인")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
		String id = user.getUser_id();
		String password = user.getUser_pass();
		User loginUser = uService.select(id, password); // 로그인 유저의 아이디, 비밀번호 비교해서 저장
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		try {
			if (loginUser != null) { // 해당 유저 있으면
				result.put("access-token",
						jwtUtil.createToken("id", (loginUser.getUser_id()), "isAdmin", loginUser.getUser_is_admin()));
				result.put("message", "success");
				result.put("loginUser", loginUser);
				status = HttpStatus.ACCEPTED;
			} else {
				result.put("message", "fail");
				status = HttpStatus.BAD_REQUEST;// 유저가 널이면 실패
				return new ResponseEntity<Map<String, Object>>(result, status);
			}
		} catch (UnsupportedEncodingException e) {
			result.put("message", "error");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

	// 로그아웃
	@PostMapping("/logout")
	@ApiOperation(value = "로그아웃")
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate(); // 세션 만료
		return new ResponseEntity<>("logout OK", HttpStatus.OK);
	}

	// 회원가입 진행
	@PostMapping("/signup")
	@ApiOperation(value = "회원가입")
	public ResponseEntity<?> userRegist(@RequestBody User user) {
		int result = uService.regist(user); // 해당 유저의 정보를 저장(트랜잭션 처리 해서 int로 반환)
		if (result == 0) { // 0이면 실패
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		} else { // 아니면 성공
			return new ResponseEntity<>("OK", HttpStatus.OK);
		}
	}

	// 회원탈퇴
	@DeleteMapping()
	@ApiOperation(value = "회원탈퇴")
	public ResponseEntity<?> deleteAccount(User user, HttpServletRequest request) {
		String token = request.getHeader("access-token");
		if (token == null) { // 토큰이 없으면
			return new ResponseEntity<>("No token", HttpStatus.UNAUTHORIZED);
		}
		try {
			jwtUtil.valid(token); // 토큰 검증(실패하면 catch로 간다)
			String user_id = jwtUtil.getUserIdFromToken(token);
			uService.delete(user_id, user.getUser_pass());
			return new ResponseEntity<>("OK", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
		}
	}

	// 유저 비밀번호 2차확인
	@PostMapping("/check")
	public ResponseEntity<?> userPassCheck(User user, HttpServletRequest request) {
		String token = request.getHeader("access-token");
		if (token == null) { // 토큰이 없으면
			return new ResponseEntity<>("No token", HttpStatus.UNAUTHORIZED);
		}
		try {
			jwtUtil.valid(token); // 토큰 검증(실패하면 catch로 간다)
			String user_id = jwtUtil.getUserIdFromToken(token);
			String user_pass = user.getUser_pass();
			User tempUser = uService.select(user_id, user_pass);
			if (tempUser == null) {
				return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(tempUser, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
		}
	}

	// 유저 정보 수정
	@PutMapping
	public ResponseEntity<?> modifyUserInfo(User user, HttpServletRequest request) {
		String token = request.getHeader("access-token");
		if (token == null) { // 토큰이 없으면
			return new ResponseEntity<>("No token", HttpStatus.UNAUTHORIZED);
		}
		try {
			jwtUtil.valid(token); // 토큰 검증(실패하면 catch로 간다)
			String user_id = jwtUtil.getUserIdFromToken(token);
			User tempUser = uService.selectOne(user_id); // 유저 정보 가져옴
			tempUser = user; // 수정하는 유저 정보로 갈아끼움
			int result = uService.modify(tempUser); // 수정함
			if (result == 0) {
				return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
			} else {
				User modifyUser = uService.selectOne(user_id); // 성공했으면 그 유저 정보 새로 가져옴(수정된 유저 정보)
				return new ResponseEntity<>(modifyUser, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
		}
	}

	// 아이디 찾기
	@PostMapping("/search/id")
	// user = 아이디 찾고싶어서 입력한 user
	// tmpUser = 정보 찾고자 하는 user의 id로 db에서 찾은 유저
	public ResponseEntity<?> searchId(User user) {
		String name = user.getUser_name();
		String user_phone_number = user.getUser_phone_number();
		User tmpUser = uService.searchId(name, user_phone_number);
		if (tmpUser == null) {
			return new ResponseEntity<>("NO USER", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(tmpUser, HttpStatus.OK);
		}
	}

	// 임시 비밀번호 이메일 보내기
	@GetMapping("/sendEmail")
	public ResponseEntity<?> sendEmail(User user) {
		User tmpUser = uService.getUserInfo(user);
		if (tmpUser == null) {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		} else {
			// 1. 수신자 설정
			String recipient = user.getUser_email();
			// 임시비밀번호
			String tmpPass = String.valueOf((int) ((Math.random() + 1) * 100000));

			// 2. 발신자 설정
			final String sender = "ssyeonn@naver.com";
			final String password = "rlgusthdus123!";

			// 3. property에 SMTP 서버 정보 설정
			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.naver.com");
			prop.put("mail.smtp.port", 587);
			prop.put("mail.smtp.auth", "true");

			// 집에서 추가
			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
			prop.put("mail.smtp.ssl.ciphers", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");

			// 4. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
			// 이 Session은 우리가 사용하던 HttpSession과는 다른 javax.mail 에서 생성하는 session
			Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(sender, password);
				}
			});

			// 5. Message 클래스의 객체를 사용하여 수신자와 내용, 제목의 메시지를 작성한다.
			MimeMessage msg = new MimeMessage(session);
			try {
				msg.setFrom(new InternetAddress(sender));
				// 수신자 메일 주소
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
				// 메일 제목
				msg.setSubject("[fitopia] 임시비밀번호입니다.");
				// 본문
				msg.setText("임시비밀번호: " + tmpPass);

				// 6. Transport 클래스를 사용하여 작성한 메시지를 전달한다.
//	            try {
				Transport.send(msg);
//	            } catch (MessagingException e) {
//	                e.printStackTrace();
//	                return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
//	            }
				tmpUser.setUser_pass(tmpPass);
				System.out.println(tmpUser.toString());
				uService.modify(tmpUser);
//				System.out.println(user.toString());

				return new ResponseEntity<>(tmpUser, HttpStatus.OK);

			} catch (AddressException e) {
//				e.printStackTrace();
				return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
			} catch (MessagingException e) {
//				e.printStackTrace();
				return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
			}
//			return null;
		}
//		return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
	}

	// 어드민 여부 확인
	@GetMapping("/isAdmin")
	public ResponseEntity<?> isAdmin(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		String token = header.replace("Bearer ", "");
		try {
			Integer isAdmin = jwtUtil.getUserAdminFromToken(token);
			boolean isAdminStatus = isAdmin == 1;
			return ResponseEntity.status(HttpStatus.OK).body(isAdminStatus);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
	}

	// 사진 업로드
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile multipartFile) throws IllegalStateException, IOException {
	    if (!multipartFile.isEmpty()) {
	        // 파일을 저장할 위치 지정
	        Resource res = reLoaders.getResource("classpath:/static/upload");
	        
	        if (res != null && res.getFile() != null) {
	            // UUID 생성
	            String uniqueFileName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
	            
	            // 파일 저장
	            try {
	                multipartFile.transferTo(new File(res.getFile(), uniqueFileName));
	                return new ResponseEntity<>(uniqueFileName, HttpStatus.OK);
	            } catch (IOException e) {
	                // 처리 실패 시 예외 처리
	                e.printStackTrace();
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	            }
	        } else {
	            // 처리 실패 시 예외 처리
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
		return null;
	}
	
	// 아이디 중복 확인
			@GetMapping("/checkId/{user_id}")
			@ApiOperation(value = "아이디 중복 확인")
			public ResponseEntity<?> checkId(@PathVariable String user_id) {
				User loginUser = uService.checkId(user_id); 
				// 아이디와 일치하는 유저가 있으면
				if (loginUser != null) {
					return new ResponseEntity<>("DUPLICATION", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("OK", HttpStatus.NO_CONTENT);
				}
			}
			
			// 이메일 중복 확인
			@GetMapping("/checkEmail/{user_email}")
			@ApiOperation(value = "이메일 중복 확인")
			public ResponseEntity<?> checkEmail(@PathVariable String user_email) {
				User loginUser = uService.checkEmail(user_email); // id로 유저 정보 하나 꺼내옴
				if (loginUser != null) {
					return new ResponseEntity<>("DUPLICATION", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("OK", HttpStatus.NO_CONTENT);
				}
			}
			
			// 휴대폰번호 중복 확인
			@GetMapping("/checkPNum/{user_phone_number}")
			@ApiOperation(value = "휴대폰번호 중복 확인")
			public ResponseEntity<?> checkPNum(@PathVariable String user_phone_number) {
				System.out.println(user_phone_number);
				User loginUser = uService.checkPNum(user_phone_number); // id로 유저 정보 하나 꺼내옴
				if (loginUser != null) {
					return new ResponseEntity<>("DUPLICATION", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("OK", HttpStatus.NO_CONTENT);
				}
			}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
