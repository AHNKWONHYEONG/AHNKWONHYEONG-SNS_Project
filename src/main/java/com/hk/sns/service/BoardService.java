package com.hk.sns.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.sns.doas.IBoardDao;
import com.hk.sns.dtos.BoardDto;

@Service
public class BoardService implements IBoardService {

	@Autowired
	private IBoardDao boardDao;

	@Override
	public List<BoardDto> getBoardList() {
		return boardDao.getBoardList();
	}

	@Override
	public boolean insertBoard(BoardDto dto) {
		return boardDao.insertBoard(dto);
	}

	@Override
	public BoardDto searchBoard(int seq) {
		return boardDao.searchBoard(seq);
	}

	@Override
	public boolean upBoard(BoardDto dto) {
		return boardDao.upBoard(dto);
	}

	@Override
	public boolean delBoard(int seq) {
		return boardDao.delBoard(seq);
	}

	@Override
	public boolean mulDel(String[] seqs) {
		return boardDao.mulDel(seqs);
	}

}
