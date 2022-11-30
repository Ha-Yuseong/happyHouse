package com.mycom.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.dto.NoticeDto;
import com.mycom.myapp.dto.NoticeParamDto;

@Mapper
public interface NoticeDao {

	 NoticeDto noticeDetail(NoticeParamDto boardParamDto);
	 int noticeDelete(int boardId);	
	 int noticeInsert (NoticeDto dto);
	 List<NoticeDto> noticeList(NoticeParamDto boardParamDto);
	 int noticeUpdate(NoticeDto dto);
}
