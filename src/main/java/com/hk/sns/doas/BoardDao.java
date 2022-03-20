package com.hk.sns.doas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.sns.dtos.BoardDto;

@Repository
public class BoardDao implements IBoardDao {

	private String namespace="com.sns.board.";
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public List<BoardDto> getBoardList() {
		return sqlsession.selectList(namespace+"getBoardList");
	}

	@Override
	public boolean insertBoard(BoardDto dto) {
		int count=0;

		count=sqlsession.insert(namespace+"insertboard", dto);

		return count>0?true:false;
	}

	@Override
	public BoardDto searchBoard(int seq) {
		return sqlsession.selectOne(namespace+"searchboard", seq);
	}

	@Override
	public boolean upBoard(BoardDto dto) {
		int count=0;

		count=sqlsession.update(namespace+"updateboard", dto);

		return count>0?true:false;
	}

	@Override
	public boolean delBoard(int seq) {
		int count=0;

		count=sqlsession.delete(namespace+"delboard", seq);

		return count>0?true:false;
	}

	@Override
	public boolean mulDel(String[] seqs) {
		int count=0;

		Map<String, String[]> map=new HashMap<>();
		map.put("seqs",seqs);
		count=sqlsession.delete(namespace+"muldel", map);

		return count>0?true:false;
	}

}