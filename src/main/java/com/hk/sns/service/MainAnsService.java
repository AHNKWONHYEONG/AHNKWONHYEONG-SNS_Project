package com.hk.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.sns.doas.IAnsDao;
import com.hk.sns.dtos.AnsDto;
import com.hk.sns.dtos.MainAnsDto;

@Service
public class MainAnsService implements IMainAnsService {

	@Autowired
	private IMainAnsService mainansDao;

	@Override
	public List<MainAnsDto> getAllList() {
		return mainansDao.getAllList();
	}

	@Override
	public boolean insertBoard(MainAnsDto dto) {
		return mainansDao.insertBoard(dto);
	}

	@Override
	public MainAnsDto detailBoard(int seq) {
		return mainansDao.detailBoard(seq);
	}

	@Override
	public boolean updateBoard(MainAnsDto dto) {
		return mainansDao.updateBoard(dto);
	}

	@Override
	public boolean delBoard(String[] seqs) {
		return mainansDao.delBoard(seqs);
	}

	@Override
	public boolean readCount(int seq) {
		return mainansDao.readCount(seq);
	}

	@Override
	public boolean replyBoard(MainAnsDto dto) {
		return mainansDao.replyBoard(dto);
	}

	



}