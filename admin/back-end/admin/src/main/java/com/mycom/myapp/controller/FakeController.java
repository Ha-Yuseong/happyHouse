package com.mycom.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.dto.FakeParamDto;
import com.mycom.myapp.dto.FakeResultDto;
import com.mycom.myapp.dto.NoticeDto;
import com.mycom.myapp.dto.NoticeResultDto;
import com.mycom.myapp.dto.UserDto;
import com.mycom.myapp.service.FakeService;

@RestController
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class FakeController {
	@Autowired
	
	FakeService service;
	private static final int SUCCESS = 1;
	
	@GetMapping(value="/fake")
	public ResponseEntity<FakeResultDto> fakeList(FakeParamDto fakeParamDto){
		
		FakeResultDto fakeResultDto;
		fakeResultDto = service.fakeList(fakeParamDto);
		System.out.println(fakeResultDto);
		
		if( fakeResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<FakeResultDto>(fakeResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<FakeResultDto>(fakeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/fake/{fakeId}")
	public ResponseEntity<FakeResultDto> fakeUpdate(@PathVariable int fakeId){
		FakeResultDto fakeResultDto = new FakeResultDto();

		fakeResultDto = service.fakeUpdate(fakeId);
		if( fakeResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<FakeResultDto>(fakeResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<FakeResultDto>(fakeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	


}
