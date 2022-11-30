package com.mycom.myapp.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.dto.NoticeDto;
import com.mycom.myapp.dto.NoticeParamDto;
import com.mycom.myapp.dto.NoticeResultDto;

public interface NoticeService{
	public NoticeResultDto noticeDetail(NoticeParamDto boardParamDto);
	
	public NoticeResultDto noticeDelete(int boardId);
	
	public NoticeResultDto noticeUpdate(NoticeDto dto);

	public NoticeResultDto noticeInsert(NoticeDto dto, HttpServletRequest request);

	public NoticeResultDto noticeList(NoticeParamDto boardParamDto);
}
