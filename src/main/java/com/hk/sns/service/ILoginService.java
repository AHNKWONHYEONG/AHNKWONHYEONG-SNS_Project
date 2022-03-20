package com.hk.sns.service;

import java.util.List;

import com.hk.sns.dtos.LDto;



public interface ILoginService {

public LDto getLogin(String id, String password);

	public boolean insertUser(LDto dto);

	public String idChk(String id);

	public List<LDto> getAllUserStatus();

	public List<LDto> getAllUser();

	public LDto getUser(String id);

	public boolean updateRoleUser(String id, String role);

	public boolean deleteUser(String id);

	public boolean updateUser(LDto dto);
}
