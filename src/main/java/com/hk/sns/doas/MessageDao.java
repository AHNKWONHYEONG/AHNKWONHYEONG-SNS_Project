package com.hk.sns.doas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.sns.dtos.MessageDto;

@Repository
public class MessageDao implements IMessageDao {

	private String namespace="com.sns.message.";
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public List<MessageDto> sgetBoardList(String id) {
		return sqlsession.selectList(namespace+"sgetBoardList",id);
	}

	@Override
	public List<MessageDto> rgetBoardList(String id) {
		return sqlsession.selectList(namespace+"rgetBoardList",id);
	}

	@Override
	public boolean insertBoard(MessageDto dto) {
		int count=0;

		count=sqlsession.insert(namespace+"insertboard", dto);

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

	@Override
	public MessageDto messagedetail(int seq) {
		return sqlsession.selectOne(namespace+"messagedetail", seq);
	}

	@Override
	public boolean report(int seq) {
		int count=0;

		count=sqlsession.update(namespace+"report", seq);

		return count>0?true:false;
	}

	@Override
	public List<MessageDto> messagereport() {
		return sqlsession.selectList(namespace+"messagereport");
	}

	@Override
	public int yellowmessage(String s_id, String id) {
		Map<String, String> map=new HashMap<>();
		map.put("s_id", s_id);
		map.put("id", id);
		return sqlsession.update(namespace+"yellowmessage", map);
	}

	@Override
	public int yellowmessage2(int seq) {
		return sqlsession.update(namespace+"yellowmessage2", seq);
	}

	@Override
	public int mainyellowmessage(String s_id, String id) {
		Map<String, String> map=new HashMap<>();
		map.put("s_id", s_id);
		map.put("id", id);
		return sqlsession.update(namespace+"mainyellowmessage", map);
	}

	@Override
	public int mainyellowmessage2(int seq) {
		return sqlsession.update(namespace+"mainyellowmessage2", seq);
	}

}
