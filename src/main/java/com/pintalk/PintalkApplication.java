package com.pintalk;

import com.pintalk.session.SessionListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.servlet.http.HttpSessionListener;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaAuditing
public class PintalkApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(PintalkApplication.class);
		SpringApplication springApplication = springApplicationBuilder.build();
		springApplication.run(args);
	}

	@Bean
	public HttpSessionListener httpSessionListener(){
		return new SessionListener();
	}
}
