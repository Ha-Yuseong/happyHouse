package com.mycom.myapp.map.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.map.dto.DongCodeDto;
import com.mycom.myapp.map.dto.FavoriteParamDto;
import com.mycom.myapp.map.dto.GugunCodeDto;
import com.mycom.myapp.map.dto.HouseParamDto;
import com.mycom.myapp.map.dto.HouseResultDto;
import com.mycom.myapp.map.dto.HouseinfoDto;
import com.mycom.myapp.map.dto.SidoCodeDto;

public interface MapService {
	/* 아파트별 조회 */
	HouseResultDto houseList(HouseParamDto houseParamDto);
	HouseResultDto houseListSearchWord(HouseParamDto houseParamDto);
	HouseResultDto favoriteHouseList(HouseParamDto houseParamDto);
	
	// 상세
	HouseResultDto houseDetail(HouseParamDto houseParamDto);
	
	/* 동별 조회 */
	List<SidoCodeDto> sidoList();
	List<GugunCodeDto> gugunList(String sidoCode);
	List<DongCodeDto> dongList(String gugunCode);
	List<HouseinfoDto> resultDongSearchList(Map map);
	int dongListTotalCnt(String code, String name);
	
	HouseResultDto getFavorite(FavoriteParamDto favoriteParamDto);
	HouseResultDto addFavorite(FavoriteParamDto favoriteParamDto);
	HouseResultDto deleteFavorite(FavoriteParamDto favoriteParamDto);
	
	HouseResultDto sellingHouseList(HouseParamDto houseParamDto);
	HouseResultDto sellingHouseListMap(HouseParamDto houseParamDto);
	HouseResultDto sellingHouseListMapSearchWord(HouseParamDto houseParamDto);
	HouseResultDto houseUpdate(HouseParamDto houseParamDto, MultipartHttpServletRequest request);
}
