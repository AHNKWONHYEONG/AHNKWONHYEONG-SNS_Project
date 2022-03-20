package com.hk.sns.doas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.sns.dtos.AnsDto;
import com.hk.sns.dtos.MainAnsDto;


@Repository
public class MainAnsDao implements IMainAnsDao {

	private String namespace="com.sns.mainansboard.";

	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public List<MainAnsDto> getAllList() {
		return sqlsession.selectList(namespace+"boardlist");
	}

	@Override
	public boolean insertBoard(MainAnsDto dto) {
		int count=0;

		count=sqlsession.insert(namespace+"insertboard", dto);

		return count>0?true:false;
	}

	@Override
	public MainAnsDto detailBoard(int seq) {
		Map<String, Integer> map=new HashMap<>();
		map.put("seq", seq);
		return sqlsession.selectOne(namespace+"boardlist", map);
	}

	@Override
	public boolean updateBoard(MainAnsDto dto) {
		int count=0;

		count=sqlsession.update(namespace+"updateboard", dto);

		return count>0?true:false;
	}

	@Override
	public boolean delBoard(String[] seqs) {
		int count=0;

		Map<String, String[]> map=new HashMap<>();
		map.put("seqs", seqs);
		count=sqlsession.update(namespace+"delboard", map);

		return count>0?true:false;
	}

	@Override
	public boolean readCount(int seq) {
		int count=0;

		count=sqlsession.update(namespace+"readcount",seq);

		return count>0?true:false;
	}

	@Override
	public int replyBoardUp(MainAnsDto dto) {
		return sqlsession.update(namespace+"replyupdate", dto);
	}

	@Override
	public int replyBoardIn(MainAnsDto dto) {
		return sqlsession.update(namespace+"replyinsert", dto);
	}




}
