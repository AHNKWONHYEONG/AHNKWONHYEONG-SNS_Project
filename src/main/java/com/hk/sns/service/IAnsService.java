package com.hk.sns.service;

import java.util.List;

import com.hk.sns.dtos.AnsDto;



public interface IAnsService {

	public List<AnsDto> getAllList();
	public boolean insertBoard(AnsDto dto);
	public AnsDto detailBoard(int seq);
	public boolean updateBoard(AnsDto dto);
	public boolean delBoard(String[] seqs);
	public boolean readCount(int seq);
	public boolean replyBoard(AnsDto dto);

}
