package com.mycom.myapp.board.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.board.dto.BoardResultDto;

public interface BoardService {
	
	// 목록
	// 총건수를 구하는 부분이 boardList에 포함되어 있다.
	BoardResultDto boardList(BoardParamDto boardParamDto);
	BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);
	
	// 상세
	BoardResultDto boardDetail(BoardParamDto boardParamDto);
	
	// 수정
	BoardResultDto boardUpdate(BoardDto boardDto, MultipartHttpServletRequest request);
	
	// 삭제
	BoardResultDto boardDelete(int boardId);
	
	// 등록
	BoardResultDto boardInsert(BoardDto boardDto, MultipartHttpServletRequest request);
}
