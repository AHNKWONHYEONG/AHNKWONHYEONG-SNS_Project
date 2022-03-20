package com.hk.sns.dtos;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainBoardDto {

	private int seq;
	private String id;
	private String category;
	private String img_1;
	private String title;
	private String content;
	private int good;
	private String delflag;
	private Date regdate;
	private String report;

	public MainBoardDto(int seq, String id, String category, String img_1, String title, String content, int good,
			String delflag, Date regdate, String report) {
		super();
		this.seq = seq;
		this.id = id;
		this.category = category;
		this.img_1 = img_1;
		this.title = title;
		this.content = content;
		this.good = good;
		this.delflag = delflag;
		this.regdate = regdate;
		this.report = report;
	}

	public MainBoardDto(int seq, String id, String category, String img_1, String title, String content, int good,
			Date regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.category = category;
		this.img_1 = img_1;
		this.title = title;
		this.content = content;
		this.good = good;
		this.regdate = regdate;
	}

	public MainBoardDto(String id, String category) {
		super();
		this.id = id;
		this.category = category;
	}

	public MainBoardDto(int seq, String category, String img_1, String title, String content) {
		super();
		this.seq = seq;
		this.category = category;
		this.img_1 = img_1;
		this.title = title;
		this.content = content;
	}

	




}