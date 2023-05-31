package com.ssafy.ssafit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.ssafit.interceptor.SessionInterceptor;

@SpringBootApplication
//mybatis의 매퍼 스캔: <mybatis:scan base-package="com.ssafy.ws.model.dao"/>를 대체한다.
// dao경로에 있는 모든 파일을 매핑하겠다.
@MapperScan(basePackages = "com.ssafy.ssafit.model.dao")
public class SpringProjectApplication implements WebMvcConfigurer {


	@Autowired
	SessionInterceptor si;
	
	/**
	 * interceptor를 등록한다. <inteceptors> 태그의 내용을 대체한다.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(si).addPathPatterns("/regist");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringProjectApplication.class, args);
	}

}
