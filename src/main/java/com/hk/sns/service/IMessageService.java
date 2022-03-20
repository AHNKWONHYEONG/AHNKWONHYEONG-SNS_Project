package com.hk.sns.service;

import java.util.List;

import com.hk.sns.dtos.MessageDto;

public interface IMessageService {

	public List<MessageDto> sgetBoardList(String id);
	public List<MessageDto> rgetBoardList(String id);
	public MessageDto messagedetail(int seq);
	public boolean insertBoard(MessageDto dto);
	public boolean mulDel(String[] seqs);
	public boolean report(int seq);
	public List<MessageDto> messagereport();
	public boolean yellowmessage(String s_id, String id, int seq);
	
	public boolean mainyellowmessage(String s_id, String id, int seq);
}