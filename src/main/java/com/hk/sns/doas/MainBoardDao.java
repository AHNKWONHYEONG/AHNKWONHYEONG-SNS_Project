package com.hk.sns.doas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.sns.dtos.MainBoardDto;

@Repository
public class MainBoardDao implements IMainBoardDao{

	private String namespace="com.sns.mainboard.";
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public boolean insertfileinfo(MainBoardDto dto) {
		int count=sqlsession.insert(namespace+"insertfileinfo",dto);
		return count>0?true:false;
	}

	@Override
	public List<MainBoardDto> getfilelist(String id) {
		return sqlsession.selectList(namespace+"maingetlist",id);
	}

	@Override
	public List<MainBoardDto> otherlist(String id, String category) {
		Map<String, String> map=new HashMap<>();
		map.put("id", id);
		map.put("category", category);

		return sqlsession.selectList(namespace+"othermaingetlist", map);
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
	public boolean mainreport(int seq) {
		int count=0;

		count=sqlsession.update(namespace+"mainreport", seq);

		return count>0?true:false;
	}

	@Override
	public MainBoardDto mypostdetail(int seq) {
		return sqlsession.selectOne(namespace+"mypostdetail", seq);
	}

	@Override
	public boolean mainupboard(MainBoardDto dto) {
		int count=0;

		count=sqlsession.update(namespace+"mainupboard", dto);

		return count>0?true:false;
	}

	@Override
	public boolean good(int seq) {
		int count=0;

		count=sqlsession.update(namespace+"good", seq);

		return count>0?true:false;
	}

	@Override
	public List<MainBoardDto> mainereportlist() {
		return sqlsession.selectList(namespace+"amainreportlist");
	}

}