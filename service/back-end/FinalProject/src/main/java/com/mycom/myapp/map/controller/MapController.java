package com.mycom.myapp.map.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.map.dto.DongCodeDto;
import com.mycom.myapp.map.dto.FavoriteParamDto;
import com.mycom.myapp.map.dto.GugunCodeDto;
import com.mycom.myapp.map.dto.HouseParamDto;
import com.mycom.myapp.map.dto.HouseResultDto;
import com.mycom.myapp.map.dto.HouseinfoDto;
import com.mycom.myapp.map.dto.SidoCodeDto;
import com.mycom.myapp.map.service.MapService;

@RestController
@CrossOrigin(
	    // localhost:5500 과 127.0.0.1 구분
	    origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
	    allowCredentials = "true", 
	    allowedHeaders = "*", 
	    methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class MapController {
	
	@Autowired
	MapService service;

	private final int SUCCESS = 1;
	
	@GetMapping(value="/maps/aptList")
	public ResponseEntity<HouseResultDto> houseList(HouseParamDto houseParamDto){
		HouseResultDto houseResultDto;
		System.out.println(houseParamDto);
		if(houseParamDto.getSearchWord()==null ||houseParamDto.getSearchWord().isEmpty()) {
			houseResultDto = service.houseList(houseParamDto);
		}else {
			houseResultDto = service.houseListSearchWord(houseParamDto);
		}
		
		if(houseResultDto.getResult()==SUCCESS) {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/maps/sidoList")
	public List<SidoCodeDto> sidoList(){
		return service.sidoList();
	}
	
	@GetMapping("/maps/gugunList/{sidoCode}")
	public List<GugunCodeDto> gugunList(@PathVariable String sidoCode){
		return service.gugunList(sidoCode);
	}
	
	@GetMapping("/maps/dongList/{gugunCode}")
	public List<DongCodeDto> dongList(@PathVariable String gugunCode){
		return service.dongList(gugunCode);
	}
	
	@GetMapping("/maps/resultDongSearchList/{code}/{name}/{limit}/{offset}")
	List<HouseinfoDto> resultDongSearchList(@PathVariable int limit, @PathVariable int offset, @PathVariable String code, @PathVariable String name){
		System.out.println(limit +" " + offset + " " + code + " " + name);
		Map<String, Object> map = new HashMap<>();
		map.put("limit", limit);
		map.put("offset", offset);
		map.put("code", code);
		map.put("name", name);
		
		return service.resultDongSearchList(map);
	}
	
	@GetMapping("/map/dongListTotalCnt/{code}/{name}")
	int dongListTotalCnt(@PathVariable String code, @PathVariable String name) {
		return service.dongListTotalCnt(code, name);
	}
	
	@GetMapping(value="/maps/favoriteList")
	public ResponseEntity<HouseResultDto> favoriteList(HouseParamDto houseParamDto){
		HouseResultDto houseResultDto;
		System.out.println("favorite 리스트 !!");
		
		houseResultDto = service.favoriteHouseList(houseParamDto);
		
		if(houseResultDto.getResult()==SUCCESS) {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/maps/getFavorite")
	public ResponseEntity<HouseResultDto> favoriteList(FavoriteParamDto favoriteParamDto){
	
		HouseResultDto houseResultDto = service.getFavorite(favoriteParamDto);
	    
	    if( houseResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
	}
	
	@GetMapping("/maps/getFavoriteOne")
	public ResponseEntity<HouseResultDto> getFavorite(FavoriteParamDto favoriteParamDto){
	
		HouseResultDto houseResultDto = service.getFavorite(favoriteParamDto);
	    
	    if( houseResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
	}
	
	@PostMapping("/maps/favorite/{no}/{userSeq}")
	private ResponseEntity<HouseResultDto> addFavorite(@PathVariable int no, @PathVariable int userSeq){
		System.out.println(no);
		FavoriteParamDto favoriteParamDto = new FavoriteParamDto();
		favoriteParamDto.setNo(no);
		favoriteParamDto.setUserSeq(userSeq);
		System.out.println(favoriteParamDto);
		HouseResultDto houseResultDto = service.addFavorite(favoriteParamDto);
		
		if(houseResultDto.getResult()==SUCCESS) {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/maps/favorite")
	private ResponseEntity<HouseResultDto> deleteFavorite(FavoriteParamDto favoriteParamDto){

		System.out.println(favoriteParamDto);
		HouseResultDto houseResultDto = service.deleteFavorite(favoriteParamDto);
		
		if(houseResultDto.getResult()==SUCCESS) {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/maps/sellingList")
	public ResponseEntity<HouseResultDto> sellingHouseList(HouseParamDto houseParamDto){
		HouseResultDto houseResultDto;
		System.out.println(houseParamDto);
		if(houseParamDto.getSearchWord()==null ||houseParamDto.getSearchWord().isEmpty()) {
			houseResultDto = service.sellingHouseList(houseParamDto);
		}else {
			houseResultDto = service.houseListSearchWord(houseParamDto);
		}
		
		if(houseResultDto.getResult()==SUCCESS) {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value="/maps/sellingListMap")
	public ResponseEntity<HouseResultDto> sellingHouseListMap(HouseParamDto houseParamDto){
		HouseResultDto houseResultDto;
		System.out.println(houseParamDto);
		if(houseParamDto.getSearchWord()==null ||houseParamDto.getSearchWord().isEmpty()) {
			houseResultDto = service.sellingHouseListMap(houseParamDto);
		}else {
			houseResultDto = service.sellingHouseListMapSearchWord(houseParamDto);
		}
		
		if(houseResultDto.getResult()==SUCCESS) {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping(value="/maps/{boardId}")
	private ResponseEntity<HouseResultDto> houseUpdate(HouseParamDto houseParamDto, MultipartHttpServletRequest request){

		HouseResultDto houseResultDto = service.houseUpdate(houseParamDto, request);
		
		
		if(houseResultDto.getResult()==SUCCESS) {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
