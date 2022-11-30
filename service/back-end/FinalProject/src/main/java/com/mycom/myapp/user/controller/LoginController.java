package com.mycom.myapp.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.service.LoginService;

@RestController
@CrossOrigin(
	    // localhost:5500 과 127.0.0.1 구분
	    origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
	    allowCredentials = "true", 
	    allowedHeaders = "*", 
	    methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class LoginController {
	
	@Autowired
	LoginService service;
	
	// "result" : success / "fail"
	@PostMapping(value="/login")
	public ResponseEntity<UserDto> login(@RequestBody UserDto dto, HttpSession session){
		// dto 에는 client 가 보낸 userEmail, userPassword가 자동으로 파라미터 처리되어진다.
		UserDto userDto = service.login(dto);
		
		System.out.println("로그인 컨트롤러!!");
		
		if(userDto != null) { // login 성공
			session.setAttribute("userDto", userDto);
			return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		}
		return new ResponseEntity<UserDto>(userDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/logout")
	public ResponseEntity<Map<String, String>> login(HttpSession session){
		
		System.out.println("로그아웃 컨트롤러!!");
		session.invalidate();
		Map<String, String> map = new HashMap<>();
		map.put("result","success");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		
	}
}
