package com.hk.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.sns.doas.ILoginDao;
import com.hk.sns.dtos.LDto;

@Service
public class LoginService implements ILoginService {

	@Autowired
	private ILoginDao ldao;

	@Override
	public LDto getLogin(String id, String password) {
		return ldao.getLogin(id, password);
	}

	@Override
	public boolean insertUser(LDto dto) {
		return ldao.insertUser(dto);
	}

	@Override
	public String idChk(String id) {
		return ldao.idChk(id);
	}

	@Override
	public List<LDto> getAllUserStatus() {
		return ldao.getAllUserStatus();
	}

	@Override
	public List<LDto> getAllUser() {
		return ldao.getAllUser();
	}

	@Override
	public LDto getUser(String id) {
		return ldao.getUser(id);
	}

	@Override
	public boolean updateRoleUser(String id, String role) {
		return ldao.updateRoleUser(id, role);
	}

	@Override
	public boolean deleteUser(String id) {
		return ldao.deleteUser(id);
	}

	@Override
	public boolean updateUser(LDto dto) {
		return ldao.updateUser(dto);
	}

}