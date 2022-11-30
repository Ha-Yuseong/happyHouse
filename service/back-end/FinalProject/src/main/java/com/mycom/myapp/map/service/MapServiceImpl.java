package com.mycom.myapp.map.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.map.dao.MapDao;
import com.mycom.myapp.map.dto.DongCodeDto;
import com.mycom.myapp.map.dto.FavoriteParamDto;
import com.mycom.myapp.map.dto.GugunCodeDto;
import com.mycom.myapp.map.dto.HouseFileDto;
import com.mycom.myapp.map.dto.HouseParamDto;
import com.mycom.myapp.map.dto.HouseResultDto;
import com.mycom.myapp.map.dto.HouseTotalDto;
import com.mycom.myapp.map.dto.HouseinfoDto;
import com.mycom.myapp.map.dto.SidoCodeDto;

@Service
public class MapServiceImpl implements MapService {

	@Autowired
	MapDao dao;
	
	private final int SUCCESS = 1;
	private final int FAIL = -1;
	
	// @Value <-- application.properties 설정값으로 대체
	@Value("${app.fileupload.uploadPath}")
	String uploadPath; 
	
	@Value("${app.fileupload.uploadDir}")
	String uploadFolder;
	
	
	@Override
	public List<SidoCodeDto> sidoList() {
		// TODO Auto-generated method stub
		return dao.sidoList();
	}

	@Override
	public List<GugunCodeDto> gugunList(String sidoCode) {
		// TODO Auto-generated method stub
		return dao.gugunList(sidoCode);
	}

	@Override
	public List<DongCodeDto> dongList(String gugunCode) {
		// TODO Auto-generated method stub
		return dao.dongList(gugunCode);
	}

	@Override
	public List<HouseinfoDto> resultDongSearchList(Map map) {
		// TODO Auto-generated method stub
		return dao.resultDongSearchList(map);
	}

	@Override
	public int dongListTotalCnt(String code, String name) {
		// TODO Auto-generated method stub
		return dao.dongListTotalCnt(code, name);
	}

	@Override
	public HouseResultDto houseList(HouseParamDto houseParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<HouseTotalDto> list = dao.houseList(houseParamDto);
			int count = dao.houseListTotalCnt(houseParamDto);
			houseResultDto.setCount(count);
			houseResultDto.setList(list);
			houseResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		return houseResultDto;
	}

	@Override
	public HouseResultDto houseListSearchWord(HouseParamDto houseParamDto) {
		
		HouseResultDto houseResultDto = new HouseResultDto();
		try {
			// 목록, 총건수를 가져온다.
			List<HouseTotalDto> list = dao.houseListSearchWord(houseParamDto);
			int count = dao.houseListSearchWordTotalCnt(houseParamDto);
			houseResultDto.setCount(count);
			houseResultDto.setList(list);
			houseResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		return houseResultDto;
	}

	@Override
	public HouseResultDto houseDetail(HouseParamDto houseParamDto) {
		// TODO Auto-generated method stub
		return dao.houseDetail(houseParamDto);
	}

	@Override
	public HouseResultDto favoriteHouseList(HouseParamDto houseParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		try {
			// 목록, 총건수를 가져온다.
			List<HouseTotalDto> list = dao.favoriteHouseList(houseParamDto);
			int count = dao.favoriteListTotalCnt(houseParamDto);
			houseResultDto.setList(list);
			houseResultDto.setCount(count);
			houseResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		return houseResultDto;
	}

	@Override
	public HouseResultDto getFavorite(FavoriteParamDto favoriteParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			int count = dao.getFavorite(favoriteParamDto);
			houseResultDto.setCount(count);
			houseResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		return houseResultDto;
	}

	@Override
	public HouseResultDto addFavorite(FavoriteParamDto favoriteParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			dao.addFavorite(favoriteParamDto);
			houseResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		return houseResultDto;
	}

	@Override
	public HouseResultDto deleteFavorite(FavoriteParamDto favoriteParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			dao.deleteFavorite(favoriteParamDto);
			houseResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		return houseResultDto;
	}

	@Override
	public HouseResultDto sellingHouseList(HouseParamDto houseParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<HouseTotalDto> list = dao.sellingHouseList(houseParamDto);
			int count = dao.sellingHouseListTotalCnt(houseParamDto);
			houseResultDto.setCount(count);
			houseResultDto.setList(list);
			houseResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		return houseResultDto;
	}

	@Override
	public HouseResultDto sellingHouseListMap(HouseParamDto houseParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		try {
			// 목록, 총건수를 가져온다.
			List<HouseTotalDto> list = dao.sellingHouseListMap(houseParamDto);
			int count = dao.sellingHouseListMapCount(houseParamDto);
			houseResultDto.setCount(count);
			houseResultDto.setList(list);
			houseResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		return houseResultDto;
	}

	@Override
	public HouseResultDto sellingHouseListMapSearchWord(HouseParamDto houseParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		try {
			// 목록, 총건수를 가져온다.
			List<HouseTotalDto> list = dao.sellingHouseListMapSearchWord(houseParamDto);
			int count = dao.sellingHouseListMapSearchWordCount(houseParamDto);
			houseResultDto.setCount(count);
			houseResultDto.setList(list);
			houseResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		return houseResultDto;
	}

	@Override
	public HouseResultDto houseUpdate(HouseParamDto houseParamDto, MultipartHttpServletRequest request) {
		HouseResultDto houseResultDto = new HouseResultDto();
		System.out.println("service impl 잘 들어옴");
		try {
			
			System.out.println("-------집 값이 변했기를 바랍니다.");
			
			dao.houseUpdate(houseParamDto);
			
			System.out.println("-------파일 부분 실행");
			
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
			if(! uploadDir.exists()) uploadDir.mkdir();
			
			List<String> fileUrlList = dao.houseFileUrlDeleteList(houseParamDto.getHouseId());
	        for(String fileUrl : fileUrlList) {
	            File file = new File(uploadPath + File.separator, fileUrl);                
	            if(file.exists()) {
	                file.delete();
	            }
	        }
	        
	        // 테이블에서 게시판 파일 삭제
	        
	        
	        dao.houseFileDelete(houseParamDto.getHouseId());
	        
	        System.out.println("!!!!!!!!!파일 삭제 실행");
	        
	        // 수정 과정에 새로 추가된 첨부 파일 등록
	        
	        List<MultipartFile> fileList  = request.getFiles("file");
	        
	        System.out.println("파일리스트는 다음과 같습니다. " + fileList);
			
			for(MultipartFile partFile : fileList) {
				
				int houseId = houseParamDto.getHouseId(); // 직전 등록된 게시글의 key
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
				HouseFileDto houseFileDto = new HouseFileDto();
				houseFileDto.setHouseId(houseId);
				houseFileDto.setFileName(fileName);
				houseFileDto.setFileSize(partFile.getSize());
				houseFileDto.setFileContentType(partFile.getContentType());
				houseFileDto.setFileUrl(uploadFolder + "/" + savingFileName);
				
				dao.houseFileInsert(houseFileDto);
				
				System.out.println("##########파일 입력 실행");
				
			}
			
			houseResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		return houseResultDto;
	}



}
