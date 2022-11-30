package com.mycom.myapp.map.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HouseTotalDto {
	private int no;
	private String dong;
	private String AptName;
	private String gu;
	private String dealAmount;
	private String buildYear;
	private String area;
	private String floor;
	private String jibun;
	private String type;
	private String lat;
	private String lng;
	private String houseContent;
	private String img = "./assets/img/property-";
	private String seller;
	
}
