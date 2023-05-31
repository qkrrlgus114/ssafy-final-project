package com.ssafy.ssafit.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

// 이 경로에 있는 거 죄다 스캔 떠서 니가 구현체로 만들어
@Configuration
@MapperScan(basePackages = "com.ssafy.ssafit.model.dao")
public class DBConfig {

}
