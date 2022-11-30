package com.mycom.myapp.user.dto;

import java.sql.Date;

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
public class UserDto {
	private int userSeq; 
	private String userName; 
	private String userPassword; 
	private String userEmail; 
	private String userProfileImageUrl; 
	private Date userRegisterDate;
	private String code;
	
}
