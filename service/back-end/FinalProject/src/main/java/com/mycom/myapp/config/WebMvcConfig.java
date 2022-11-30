package com.mycom.myapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mycom.myapp.common.LoginInterceptor;

@Configuration 
public class WebMvcConfig implements WebMvcConfigurer{

	@Autowired
	private LoginInterceptor loginIntercpetor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("nonono!");
		registry.addInterceptor(loginIntercpetor)
		.addPathPatterns("/**")
		.excludePathPatterns("/", "/index.html", "favicon.ico")
		.excludePathPatterns("/login/**", "/register/**","/boards/**" , "/notice/**", "/css/**", "/js/**", "/img/**","/kakaoLogin/**","/isRegisteredUser/**");
	}
}
