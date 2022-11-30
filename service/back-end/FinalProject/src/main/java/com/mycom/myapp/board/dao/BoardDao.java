package com.mycom.myapp.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardFileDto;
import com.mycom.myapp.board.dto.BoardParamDto;

// dao layer 는 Controller, Service 를 바라보고 코드를 작성 X, DB 등 Persistance 를 보고 작성
@Mapper
public interface BoardDao {
	
	// 목록
	// limit, offset
	public List<BoardDto> boardList(BoardParamDto boardParamDto);
	int boardListTotalCount();
	
	// searchWord
	public List<BoardDto> boardListSearchWord(BoardParamDto boardParamDto);
	int boardListSearchWordTotalCount(BoardParamDto boardParamDto);
	
	// 상세
	BoardDto boardDetail(BoardParamDto boardParamDto); // 게시글 상세
	List<BoardFileDto> boardDetailFileList(int boardId); // 특정 게시글에 첨부된 첨부파일 정보들
	
	// 수정
	int boardUpdate(BoardDto dto);
	
	// 삭제
	int boardDelete(int boardId);
	
	// 파일 삭제
	int boardFileDelete(int boardId);
	
	// 등록
	int boardInsert(BoardDto dto);
	int boardFileInsert(BoardFileDto dto); //첨부파일 등록
	
	// 파일 이름 url 가져오기
	List<String> boardFileUrlDeleteList(int boardId);
	
	// 조회수 관련
	// 1 user 1 view 조건으로 readCount 처리
	int boardUserReadCount(BoardParamDto boardParamDto); // 2개의 파라미터 mybatis map 대응 <- Dto
	int boardUserReadInsert(@Param("boardId") int boardId, @Param("userSeq") int userSeq); // 2개의 파라미터 mybatis map 대응 <= @Param
	
	int boardReadCountUpdate(int boardId); // 게시글 조회수 1 증가
	int boardReadCountDelete(int boardId);
	
}
