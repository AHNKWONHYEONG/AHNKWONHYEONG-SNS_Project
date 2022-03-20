package com.hk.sns.doas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.sns.dtos.AnsDto;


@Repository
public class AnsDao implements IAnsDao {

	private String namespace="com.sns.ansboard.";
	//application-context.xml에 등록된 sqlsessiontemplate객체와 같은 타입의
	//객체를 @autowired로 선언된 필드들을 탐색해서 주입시켜준다.
	//어노테이션으로 주입하는 방식
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public List<AnsDto> getAllList() {
		return sqlsession.selectList(namespace+"boardlist");
	}

	@Override
	public boolean insertBoard(AnsDto dto) {
		int count=0;

		count=sqlsession.insert(namespace+"insertboard", dto);

		return count>0?true:false;
	}

	@Override
	public AnsDto detailBoard(int seq) {
		Map<String, Integer> map=new HashMap<>();
		map.put("seq", seq);
		return sqlsession.selectOne(namespace+"boardlist", map);
	}

	@Override
	public boolean updateBoard(AnsDto dto) {
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
	public int replyBoardUp(AnsDto dto) {
		return sqlsession.update(namespace+"replyupdate", dto);
	}
	@Override
	public int replyBoardIn(AnsDto dto) {
		return sqlsession.update(namespace+"replyinsert", dto);
	}

	




}
