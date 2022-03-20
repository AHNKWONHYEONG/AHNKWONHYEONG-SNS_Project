package com.hk.sns.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class FriendDto {

	private int seq;
	private String id;
	private String name;
	private String my_id;
	private Date fridate;
}
