package com.mycom.myapp.map.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.map.dto.DongCodeDto;
import com.mycom.myapp.map.dto.FavoriteParamDto;
import com.mycom.myapp.map.dto.GugunCodeDto;
import com.mycom.myapp.map.dto.HouseFileDto;
import com.mycom.myapp.map.dto.HouseParamDto;
import com.mycom.myapp.map.dto.HouseResultDto;
import com.mycom.myapp.map.dto.HouseTotalDto;
import com.mycom.myapp.map.dto.HouseinfoDto;
import com.mycom.myapp.map.dto.SidoCodeDto;


@Mapper
public interface MapDao {
	/* 아파트별 조회 */
	int houseListTotalCnt(HouseParamDto houseParamDto);
	int houseListSearchWordTotalCnt(HouseParamDto houseParamDto);
	
	int favoriteListTotalCnt(HouseParamDto houseParamDto);
	
	List<HouseTotalDto> houseList(HouseParamDto houseParamDto);
	List<HouseTotalDto> houseListSearchWord(HouseParamDto houseParamDto);
	
	List<HouseTotalDto> favoriteHouseList(HouseParamDto houseParamDto);
	
	int getFavorite(FavoriteParamDto favoriteParamDto);
	int addFavorite(FavoriteParamDto favoriteParamDto);
	int deleteFavorite(FavoriteParamDto favoriteParamDto);
	
	// 상세
	HouseResultDto houseDetail(HouseParamDto houseParamDto);
	
	/* 동별 조회 */
	List<SidoCodeDto> sidoList();
	List<GugunCodeDto> gugunList(String sidoCode);
	List<DongCodeDto> dongList(String gugunCode);
	List<HouseinfoDto> resultDongSearchList(Map map);
	int dongListTotalCnt(String code, String name);
	
	
	/* 판매자 */
	
	List<HouseTotalDto> sellingHouseList(HouseParamDto houseParamDto);
	int sellingHouseListTotalCnt(HouseParamDto houseParamDto);
	
	List<HouseTotalDto> sellingHouseListMap(HouseParamDto houseParamDto);
	int sellingHouseListMapCount(HouseParamDto houseParamDto);
	List<HouseTotalDto> sellingHouseListMapSearchWord(HouseParamDto houseParamDto);
	int sellingHouseListMapSearchWordCount(HouseParamDto houseParamDto);
	int houseUpdate(HouseParamDto houseParamDto);
	
	
	
	// 파일 삭제
	int houseFileDelete(int boardId);
	int houseFileInsert(HouseFileDto dto); //첨부파일 등록
	
	// 파일 이름 url 가져오기
	List<String> houseFileUrlDeleteList(int boardId);
}
