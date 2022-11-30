package com.mycom.myapp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.myapp.dao.NoticeDao;
import com.mycom.myapp.dto.NoticeDto;
import com.mycom.myapp.dto.NoticeParamDto;
import com.mycom.myapp.dto.NoticeResultDto;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	NoticeDao dao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	

	@Override
	@Transactional
	public NoticeResultDto noticeDetail(NoticeParamDto noticeParamDto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		
		try {
			NoticeDto dto = dao.noticeDetail(noticeParamDto);
			noticeResultDto.setDto(dto);
			
			noticeResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		
		return noticeResultDto;
	}

	@Override
	public NoticeResultDto noticeDelete(int noticeId) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
				
		try {
			dao.noticeDelete(noticeId);		
			noticeResultDto.setResult(SUCCESS);	
		}catch(Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		return noticeResultDto;
	}

	@Override
	public NoticeResultDto noticeUpdate(NoticeDto dto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		try {
			dao.noticeUpdate(dto);
			noticeResultDto.setResult(SUCCESS);	
			System.out.println("dd");
		}catch(Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		return noticeResultDto;
	}

	@Override
	public NoticeResultDto noticeInsert(NoticeDto dto , HttpServletRequest request) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		try {
			dao.noticeInsert(dto);
			noticeResultDto.setResult(SUCCESS);	
		}catch(Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		return noticeResultDto;
	}

	@Override
	public NoticeResultDto noticeList(NoticeParamDto boardParamDto) {	
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		try {
			List<NoticeDto> list = dao.noticeList(boardParamDto);	
			noticeResultDto.setList(list);
			noticeResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		
		return noticeResultDto;
	}

}
