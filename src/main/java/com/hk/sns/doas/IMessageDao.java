package com.hk.sns.doas;

import java.util.List;

import com.hk.sns.dtos.AnsDto;
import com.hk.sns.dtos.BoardDto;
import com.hk.sns.dtos.MessageDto;


public interface IMessageDao {

	public List<MessageDto> sgetBoardList(String id);
	public List<MessageDto> rgetBoardList(String id);
	public MessageDto messagedetail(int seq);
	public boolean insertBoard(MessageDto dto);
	public boolean mulDel(String[] seqs);
	public boolean report(int seq);
	public List<MessageDto> messagereport();
	public int yellowmessage(String s_id, String id);

	public int yellowmessage2(int seq);
	
	public int mainyellowmessage(String s_id, String id);

	public int mainyellowmessage2(int seq);
}
