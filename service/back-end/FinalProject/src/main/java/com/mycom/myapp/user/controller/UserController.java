package com.mycom.myapp.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.service.UserService;

@RestController
@CrossOrigin(
	    // localhost:5500 과 127.0.0.1 구분
	    origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
	    allowCredentials = "true", 
	    allowedHeaders = "*", 
	    methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class UserController {
	
	@Autowired
	UserService service;
	
	
	@PostMapping("/mypage/detail")
	public ResponseEntity<UserDto> detail(HttpSession session){
	    // 현재 사용자의 userSeq 를 session 에서 획득, 전달
	    UserDto userDto = (UserDto) session.getAttribute("userDto");
	    System.out.println(userDto.getUserEmail());
	    return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	@PutMapping("/users")
	public ResponseEntity<Map<String, String>> update(@RequestBody UserDto userDto, HttpSession session){

		int val = service.userUpdate(userDto);
		Map<String, String> map = new HashMap<>();
		if(val >= 0 ) {
			session.setAttribute("userDto", userDto);
			
			map.put("result","success");
			map.put("userCode", userDto.getCode());
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> userRegister(@RequestBody UserDto userDto){
		int val = service.userRegister(userDto);
		
		Map<String, String> map = new HashMap<>();
		if(val >= 0 ) {
			map.put("result","success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/users")
	public ResponseEntity<Map<String, String>> userDelete(HttpSession session){
		
		Map<String, String> map = new HashMap<>();
		map.put("result","success");
		
		service.userDelete(123); // !!!!!!!!!!!! 
		
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}
	

}
