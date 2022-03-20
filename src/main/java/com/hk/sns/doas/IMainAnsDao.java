package com.hk.sns.doas;

import java.util.List;

import com.hk.sns.dtos.AnsDto;
import com.hk.sns.dtos.MainAnsDto;

public interface IMainAnsDao {

	public List<MainAnsDto> getAllList();
	public boolean insertBoard(MainAnsDto dto);
	public MainAnsDto detailBoard(int seq);
	public boolean updateBoard(MainAnsDto dto);
	public boolean delBoard(String[] seqs);
	public boolean readCount(int seq);

	public int replyBoardUp(MainAnsDto dto);

	public int replyBoardIn(MainAnsDto dto);
	
	

}
