package com.hk.sns.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class MainAnsDto {

	private int seq;
	private String id;
	private String title;
	private String content;
	private Date regdate;
	private String regdateStr;
	private int refer;
	private int step;
	private int dep;
	private int readcount;
	private String delflag;
	private String report;
}
