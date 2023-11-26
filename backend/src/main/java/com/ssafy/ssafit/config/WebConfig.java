package com.ssafy.ssafit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.ssafit.interceptor.SessionInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

//	private final SessionInterceptor sessionInterceptor;

//	@Autowired
//	public WebConfig(SessionInterceptor sessionInterceptor) {
//		this.sessionInterceptor = sessionInterceptor;
//	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// /** 모든 URL경로를 지원하며, 현재 어플리케이션의 classpath 루트에 있는 static 폴더를 나타낸다.
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

		registry.addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
	}

//	// 요기에다가 인터셉터 등록하면 된다~(필요하면)
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(sessionInterceptor).addPathPatterns("/**") // 모든 경로에 대해 인터셉터를 적용
//				.excludePathPatterns("/index"); // index 페이지는 예외로 처리
//	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowCredentials(true).allowedHeaders("*")
				.allowedOriginPatterns("http://localhost:8080");
	}

}
