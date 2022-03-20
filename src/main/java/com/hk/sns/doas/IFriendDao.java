package com.hk.sns.doas;

import java.util.List;

import com.hk.sns.dtos.FriendDto;



public interface IFriendDao {

	public List<FriendDto> getFriendList(String my_id);
	public boolean insertFriend(String id, String my_id);
	public FriendDto searchFriend(int seq);
	public boolean delFriend(String[] seqs);
	
}
