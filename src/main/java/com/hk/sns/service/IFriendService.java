package com.hk.sns.service;

import java.util.List;

import com.hk.sns.dtos.FriendDto;

public interface IFriendService {

	public List<FriendDto> getFriendList(String my_id);
	public boolean insertFriend(String id, String my_id);
	public FriendDto searchFriend(int seq);
	public boolean delFriend(String[] seqs);
}
