package com.hk.sns.doas;

import java.util.List;

import com.hk.sns.dtos.BoardDto;

public interface IBoardDao {

	public List<BoardDto> getBoardList();
	public boolean insertBoard(BoardDto dto);
	public BoardDto searchBoard(int seq);
	public boolean upBoard(BoardDto dto);
	public boolean delBoard(int seq);
	public boolean mulDel(String[] seqs);

}