package com.hk.sns.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hk.sns.dtos.MainBoardDto;

public interface IMainBoardService {

	public boolean insertfileinfo(HttpServletRequest request , String id, String category,
			String title, String content);

	public boolean multiInsertFileInfo(HttpServletRequest request, String id, String category,
			String title, String content);

	public List<MainBoardDto> getfilelist(String id);

	public List<MainBoardDto> otherlist(String id, String category);
	
	public boolean delBoard(String[] seqs);
	
	public boolean mainreport(int seq);
	
	public MainBoardDto mypostdetail(int seq);
	
	public boolean mainupboard(HttpServletRequest request, int seq, String category,
			String title, String content);
	
	public boolean good(int seq);
	
	public List<MainBoardDto> mainereportlist();
}