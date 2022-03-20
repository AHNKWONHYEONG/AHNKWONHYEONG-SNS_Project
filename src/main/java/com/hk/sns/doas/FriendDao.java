package com.hk.sns.doas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.sns.dtos.FriendDto;

@Repository
public class FriendDao implements IFriendDao {

	private String namespace="com.sns.friend.";
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	@Override
	public List<FriendDto> getFriendList(String my_id) {
		return sqlsession.selectList(namespace+"getfriendList",my_id);
	}

	@Override
	public boolean insertFriend(String id, String my_id) {
		// insertfriend
		int count=0;
		Map<String, String> map=new HashMap<>();
		map.put("id", id);
		map.put("my_id", my_id);

		count=sqlsession.insert(namespace+"insertfriend", map);

		return count>0?true:false;
	}

	@Override
	public FriendDto searchFriend(int seq) {
		// searchfriend
		return sqlsession.selectOne(namespace+"searchfriend", seq);
	}

	@Override
	public boolean delFriend(String[] seqs) {
		int count=0;

		Map<String, String[]> map=new HashMap<>();
		map.put("seqs",seqs);
		count=sqlsession.delete(namespace+"delfriend", map);

		return count>0?true:false;
	}

}
