package com.hk.sns.service;

import java.util.List;

import com.hk.sns.dtos.MainAnsDto;



public interface IMainAnsService {

	public List<MainAnsDto> getAllList();
	public boolean insertBoard(MainAnsDto dto);
	public MainAnsDto detailBoard(int seq);
	public boolean updateBoard(MainAnsDto dto);
	public boolean delBoard(String[] seqs);
	public boolean readCount(int seq);
	public boolean replyBoard(MainAnsDto dto);

}
