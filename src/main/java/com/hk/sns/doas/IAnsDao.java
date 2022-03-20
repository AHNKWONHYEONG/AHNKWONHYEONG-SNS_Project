package com.hk.sns.doas;

import java.util.List;

import com.hk.sns.dtos.AnsDto;

public interface IAnsDao {

	public List<AnsDto> getAllList();
	public boolean insertBoard(AnsDto dto);
	public AnsDto detailBoard(int seq);
	public boolean updateBoard(AnsDto dto);
	public boolean delBoard(String[] seqs);
	public boolean readCount(int seq);

	public int replyBoardUp(AnsDto dto);

	public int replyBoardIn(AnsDto dto);
	
	

}
