package com.hk.sns.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDto {
	private int seq;
	private String id;
	private String title;
	private String content;
	private int readcount;
	private String delflag;
	private Date regdate;
	private String report;
}
