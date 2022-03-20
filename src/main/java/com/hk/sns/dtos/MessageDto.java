package com.hk.sns.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class MessageDto {

	private String s_id;
	private String id;
	private int seq;
	private String content;
	private String delflag;
	private String report;
	private int reportcount;
	private Date regdate;
}