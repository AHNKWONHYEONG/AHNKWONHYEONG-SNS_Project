package com.hk.sns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.sns.doas.IFriendDao;
import com.hk.sns.dtos.FriendDto;

@Service
public class FriendService implements IFriendService{

	@Autowired
	private IFriendDao friendDao;
	
	@Override
	public List<FriendDto> getFriendList(String my_id) {
		return friendDao.getFriendList(my_id);
	}

	@Override
	public boolean insertFriend(String id, String my_id) {
		return friendDao.insertFriend(id, my_id);
	}

	@Override
	public FriendDto searchFriend(int seq) {
		return friendDao.searchFriend(seq);
	}

	@Override
	public boolean delFriend(String[] seqs) {
		return friendDao.delFriend(seqs);
	}

	

}
