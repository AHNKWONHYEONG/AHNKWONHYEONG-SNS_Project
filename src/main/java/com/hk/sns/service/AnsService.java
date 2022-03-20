package com.hk.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.sns.doas.IAnsDao;
import com.hk.sns.dtos.AnsDto;

@Service
public class AnsService implements IAnsService {

	@Autowired
	private IAnsDao ansDao;

	@Override
	public List<AnsDto> getAllList() {
		return ansDao.getAllList();
	}

	@Override
	public boolean insertBoard(AnsDto dto) {
		return ansDao.insertBoard(dto);
	}

	@Override
	public AnsDto detailBoard(int seq) {
		return ansDao.detailBoard(seq);
	}

	@Override
	public boolean updateBoard(AnsDto dto) {
		return ansDao.updateBoard(dto);
	}

	@Override
	public boolean delBoard(String[] seqs) {
		return ansDao.delBoard(seqs);
	}

	@Override
	public boolean readCount(int seq) {
		return ansDao.readCount(seq);
	}

	@Override
	public boolean replyBoard(AnsDto dto) {
		int count=0;
		ansDao.replyBoardUp(dto);//update실행
		count=ansDao.replyBoardIn(dto);//insert실행
		return count>0?true:false;
	}



}