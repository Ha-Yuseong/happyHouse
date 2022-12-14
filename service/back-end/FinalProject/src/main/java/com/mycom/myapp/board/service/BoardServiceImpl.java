package com.mycom.myapp.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.board.dao.BoardDao;
import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardFileDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.board.dto.BoardResultDto;

// Controller 의 요청에 의해 필요한 파라미터는 받고, 원하는 결과 데이터를 만들어서 리턴해준다.
// Business Logic 의 핵심은 바로 Service layer에서!!!

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDao dao;
	
	private final int SUCCESS = 1;
	private final int FAIL = -1;
	
	// @Value <-- application.properties 설정값으로 대체
	@Value("${app.fileupload.uploadPath}")
	String uploadPath; 
	
	@Value("${app.fileupload.uploadDir}")
	String uploadFolder;
	
	@Override
	public BoardResultDto boardList(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<BoardDto> list = dao.boardList(boardParamDto);
			int count = dao.boardListTotalCount();
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}
	
	
	@Override
	public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<BoardDto> list = dao.boardListSearchWord(boardParamDto);
			int count = dao.boardListSearchWordTotalCount(boardParamDto);
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}


	@Override
	public BoardResultDto boardDetail(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			// 현재 게시글을 현재 사용자가 읽었는지 (visit) 확인
			int count = -1;
			count = dao.boardUserReadCount(boardParamDto);
			
			// 안읽었으면 신규로 게시글 읽었다는 insert 하고, 게시글 조회수 +1 처리
			if(count<=0) {
				dao.boardUserReadInsert(boardParamDto.getBoardId(), boardParamDto.getUserSeq());
				dao.boardReadCountUpdate(boardParamDto.getBoardId());
			}
			
			// 읽었으면 무시
			
			// DB에서 게시글 정보를 가져온다.
			BoardDto boardDto = dao.boardDetail(boardParamDto);
			// 게시글 작성자와 현재 상세 조회하는 사용자의 동일인 여부 확인
			if(boardDto.getUserSeq()==boardParamDto.getUserSeq()) {
				boardDto.setSameUser(true);
			}else {
				boardDto.setSameUser(false);
			}
			
			// 해당 게시글에 대한 첨부파일 정보를 추가
			List<BoardFileDto> fileList = dao.boardDetailFileList(boardDto.getBoardId());
			boardDto.setFileList(fileList);
			
			
			// boardResultDto 의 일부인 boardDto 를 설정
			boardResultDto.setDto(boardDto);
			
			
			
			boardResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}


	@Override
	@Transactional
	public BoardResultDto boardUpdate(BoardDto boardDto, MultipartHttpServletRequest request) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			
			dao.boardUpdate(boardDto);
			
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
			if(! uploadDir.exists()) uploadDir.mkdir();
			
			List<String> fileUrlList = dao.boardFileUrlDeleteList(boardDto.getBoardId());
	        for(String fileUrl : fileUrlList) {
	            File file = new File(uploadPath + File.separator, fileUrl);                
	            if(file.exists()) {
	                file.delete();
	            }
	        }
	        
	        // 테이블에서 게시판 파일 삭제
	        
	        
	        dao.boardFileDelete(boardDto.getBoardId());
	        
	        // 수정 과정에 새로 추가된 첨부 파일 등록
	        
	        List<MultipartFile> fileList  = request.getFiles("file");
			
			for(MultipartFile partFile : fileList) {
				
				int boardId = boardDto.getBoardId(); // 직전 등록된 게시글의 key
				String fileName = partFile.getOriginalFilename(); // 첨부된 원래 파일 명, 이 이름으로는 바로 저장하지 않고 UUID를 이용해서 중복불가한 파일 이름을 만든다.
				
				// Random UUID File id
				UUID uuid = UUID.randomUUID(); // 대체될 파일 이름
				
				// 파일 확장자
				String extension = FilenameUtils.getExtension(fileName); // 원래 파일의 확장자만 추출
				
				// 실제 저장할 파일 전체 이름은
				String savingFileName = uuid + "." + extension;
				
				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				
				// 파일 객체를 통해서 파일을 저장
				partFile.transferTo(destFile);
				
				// 테이블에 첨부파일 정보 저장
				BoardFileDto boardFileDto = new BoardFileDto();
				boardFileDto.setBoardId(boardId);
				boardFileDto.setFileName(fileName);
				boardFileDto.setFileSize(partFile.getSize());
				boardFileDto.setFileContentType(partFile.getContentType());
				boardFileDto.setFileUrl(uploadFolder + "/" + savingFileName);
				
				dao.boardFileInsert(boardFileDto);
				
			}
			
			boardResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}


	@Override
	@Transactional
	public BoardResultDto boardDelete(int boardId) {
		BoardResultDto boardResultDto = new BoardResultDto();
	    
	    try {
	    	
	        List<String> fileUrlList = dao.boardFileUrlDeleteList(boardId);
	        for(String fileUrl : fileUrlList) {
	            File file = new File(uploadPath + File.separator, fileUrl);                
	            if(file.exists()) {
	                file.delete();
	            }
	        }
	        
	        // 삭제 순서
	        dao.boardReadCountDelete(boardId);
	        dao.boardFileDelete(boardId);
	        dao.boardDelete(boardId);        
	        
	        boardResultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	        boardResultDto.setResult(FAIL);
	        // 파일업로드 등이 실패할 경우 IOException 이 발생하는데, IOException 을 throw 할 경우 Rollback 되지 않는다.
	        // UncheckedException 발생
	        throw new RuntimeException();            
	    }
	    
	    return boardResultDto;
	}


	@Override
	@Transactional // DB Transaction 정책 // 파일 저장 복구 원복 X
	public BoardResultDto boardInsert(BoardDto boardDto, MultipartHttpServletRequest request) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		// 게시글 테이블에 등록
		// 물리적인 파일 저장
		// 여러 개와 파일이 기본
		// Multipart 외 각 part 별로 파일을 구분해서 물리적으로 저장 => 파일 업로드 정보를 테이브레 저장
		
		try {
			
			dao.boardInsert(boardDto); // insert 되는 건수
			System.out.println("generated key : " + boardDto.getBoardId());
			
			File uploadDir = new File(uploadPath + File.separator + uploadFolder); // 업로드 된 파일이 저장될 폴더 (디렉토리)
			if( !uploadDir.exists() ) uploadDir.mkdir(); // 없으면 새로 생성
			
			List<MultipartFile> fileList = request.getFiles("file");
			
			// NullPointer 예외 발생
//			String str = null;
//			str.length();
			
			for(MultipartFile partFile : fileList) {
				
				int boardId = boardDto.getBoardId(); // 직전 등록된 게시글의 key
				String fileName = partFile.getOriginalFilename(); // 첨부된 원래 파일 명, 이 이름으로는 바로 저장하지 않고 UUID를 이용해서 중복불가한 파일 이름을 만든다.
				
				// Random UUID File id
				UUID uuid = UUID.randomUUID(); // 대체될 파일 이름
				
				// 파일 확장자
				String extension = FilenameUtils.getExtension(fileName); // 원래 파일의 확장자만 추출
				
				// 실제 저장할 파일 전체 이름은
				String savingFileName = uuid + "." + extension;
				
				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				
				// 파일 객체를 통해서 파일을 저장
				partFile.transferTo(destFile);

				
				
				// 테이블에 첨부파일 정보 저장
				BoardFileDto boardFileDto = new BoardFileDto();
				boardFileDto.setBoardId(boardId);
				boardFileDto.setFileName(fileName);
				boardFileDto.setFileSize(partFile.getSize());
				boardFileDto.setFileContentType(partFile.getContentType());
				boardFileDto.setFileUrl(uploadFolder + "/" + savingFileName);
				
				dao.boardFileInsert(boardFileDto);
				
			}
			
			boardResultDto.setResult(SUCCESS);
			
		}catch(IOException e) {
			// 만약, 파일 저장 작업 중 오류가 발생하면 이곳 IOException catch block 의 코드가 실행됨.
			// 2가지 작업이 필요
			// #1. 예외 발생 이전에 저장된 파일을 모두 삭제 => 직접 물리적인 파일 삭제 작업 수행
			// #2. 이전에 테이블에 등록된 작업 취소 => Spring의 @Transaction 을 이용하기 위해 RuntimeException 계열의 예외를 발생 throw new RuntimeException();
			
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
			
			throw new RuntimeException(); 
		}
		return boardResultDto;
	}
	
	


	

}
