package com.hk.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.sns.doas.IMessageDao;
import com.hk.sns.dtos.MessageDto;

@Service
public class MessageService implements IMessageService{

	@Autowired
	private IMessageDao messageDao;

	@Override
	public List<MessageDto> sgetBoardList(String id) {
		return messageDao.sgetBoardList(id);
	}

	@Override
	public List<MessageDto> rgetBoardList(String id) {
		return messageDao.rgetBoardList(id);
	}

	@Override
	public boolean insertBoard(MessageDto dto) {
		return messageDao.insertBoard(dto);
	}

	@Override
	public boolean mulDel(String[] seqs) {
		return messageDao.mulDel(seqs);
	}

	@Override
	public MessageDto messagedetail(int seq) {
		return messageDao.messagedetail(seq);
	}

	@Override
	public boolean report(int seq) {
		return messageDao.report(seq);
	}

	@Override
	public List<MessageDto> messagereport() {
		return messageDao.messagereport();
	}

	@Override
	public boolean yellowmessage(String s_id, String id, int seq) {
		int count=0;
		messageDao.yellowmessage(s_id, id);
		count=messageDao.yellowmessage2(seq);
		return count>0?true:false;
	}

	@Override
	public boolean mainyellowmessage(String s_id, String id, int seq) {
		int count=0;
		messageDao.mainyellowmessage(s_id, id);
		count=messageDao.mainyellowmessage2(seq);
		return count>0?true:false;
	}

}