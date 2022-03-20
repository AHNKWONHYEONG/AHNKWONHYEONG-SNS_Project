package com.hk.sns.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class LDto {

	private String id;
	private String name;
	private String password;
	private String address;
	private String phone;
	private String email;
	private String enabled;
	private String grade;
	private Date regdate;
}