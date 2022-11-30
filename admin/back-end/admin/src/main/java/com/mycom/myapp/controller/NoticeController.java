package com.mycom.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.dto.NoticeDto;
import com.mycom.myapp.dto.NoticeParamDto;
import com.mycom.myapp.dto.NoticeResultDto;
import com.mycom.myapp.dto.UserDto;
import com.mycom.myapp.service.NoticeService;
@RestController
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class NoticeController {

	@Autowired
	NoticeService service;
	
	private static final int SUCCESS = 1;
	
	@GetMapping(value="/notice")
	public ResponseEntity<NoticeResultDto> noticeList(NoticeParamDto boardParamDto){
		
		NoticeResultDto noticeResultDto;
		 noticeResultDto = service.noticeList(boardParamDto);
		
		if( noticeResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/notice/{noticeId}")
	public ResponseEntity<NoticeResultDto> noticeDetail(@PathVariable int boardId){
		NoticeParamDto noticeParamDto = new NoticeParamDto();
		
		noticeParamDto.setBoardId(boardId);

		NoticeResultDto noticeResultDto = service.noticeDetail(noticeParamDto);
		if( noticeResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/notice/{noticeId}") 
	public ResponseEntity<NoticeResultDto> noticeUpdate(
		@RequestBody NoticeDto noticeDto, HttpServletRequest request){
		
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		noticeDto.setUserSeq( (((UserDto) request.getSession().getAttribute("userDto")).getUserSeq()));
		noticeResultDto.setDto(noticeDto);
		
		noticeResultDto = service.noticeUpdate(noticeDto);
		
		if( noticeResultDto.getResult() == SUCCESS ) {
			System.out.println("ddd");
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PostMapping(value="/notice")
	public ResponseEntity<NoticeResultDto> boardInsert(
			@RequestBody NoticeDto noticeDto,
			HttpServletRequest request
			) {

		System.out.println(noticeDto);
			
		noticeDto.setUserSeq( ((UserDto) request.getSession().getAttribute("userDto")).getUserSeq());
		
		NoticeResultDto noticeResultDto = service.noticeInsert(noticeDto, request);
	
		if( noticeResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		}else {
			System.out.println("fai");
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	@DeleteMapping(value="/notice/{noticeId}") 
	public ResponseEntity<NoticeResultDto> noticeDelete(@PathVariable(value="noticeId") int boardId){
		NoticeResultDto noticeResultDto = service.noticeDelete(boardId);
		
		if( noticeResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
}
