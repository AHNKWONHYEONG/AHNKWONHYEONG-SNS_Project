package com.hk.sns.doas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.sns.dtos.LDto;
@Repository
public class LoginDao implements ILoginDao {

	String ns="com.sns.login.";
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public LDto getLogin(String id, String password) {
		Map<String, String> map=new HashMap<>();
		map.put("id", id);
		map.put("password", password);

		return sqlsession.selectOne(ns+"getLogin", map);
	}

	@Override
	public boolean insertUser(LDto dto) {
		int count=0;

		count=sqlsession.insert(ns+"insertUser", dto);

		return count>0?true:false;
	}

	@Override
	public String idChk(String id) {
		return sqlsession.selectOne(ns+"idChk", id);
	}

	@Override
	public List<LDto> getAllUserStatus() {
		return sqlsession.selectList(ns+"getAllUserStatus");
	}

	@Override
	public List<LDto> getAllUser() {
		return sqlsession.selectList(ns+"getAllUser");
	}

	@Override
	public LDto getUser(String id) {
		return sqlsession.selectOne(ns+"getUser", id);
	}

	@Override
	public boolean updateRoleUser(String id, String grade) {
		int count=0;
		Map<String, String> map=new HashMap<>();
		map.put("id", id);
		map.put("grade", grade);

		count=sqlsession.update(ns+"updateRoleUser", map);

		return count>0?true:false;
	}

	@Override
	public boolean deleteUser(String id) {
		int count=0;

		count=sqlsession.update(ns+"deleteUser", id);

		return count>0?true:false;
	}

	@Override
	public boolean updateUser(LDto dto) {
		int count=0;

		count=sqlsession.update(ns+"updateUser", dto);

		return count>0?true:false;
	}

}