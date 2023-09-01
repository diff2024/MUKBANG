package com.diff.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DiffMukbangApplication extends SpringBootServletInitializer {
	
	//WAR 배포 설정
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DiffMukbangApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DiffMukbangApplication.class, args);
	}

}
