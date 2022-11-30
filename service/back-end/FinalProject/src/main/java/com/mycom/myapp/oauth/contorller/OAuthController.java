package com.mycom.myapp.oauth.contorller;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.oauth.service.OAuthService;


@RestController
@CrossOrigin(
    // localhost:5500 과 127.0.0.1 구분
    origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
    allowCredentials = "true", 
    allowedHeaders = "*", 
    methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
)
public class OAuthController {

	@Autowired
	OAuthService service;

	@GetMapping(value="/isRegisteredUser")
	public HashMap<String, String> isRegisteredUser(String email) {
	System.out.println("email : " + email);
	return service.isRegisteredUser(email);
	}
	
	@PostMapping(value="/kakaoLogin")
	public HashMap<String, String> kakaoLogin(String accessToken) {
		System.out.println("sssssss");
	    System.out.println("accessToken : " + accessToken);
	    return service.getKakaoUserInfo(accessToken);
	}
}