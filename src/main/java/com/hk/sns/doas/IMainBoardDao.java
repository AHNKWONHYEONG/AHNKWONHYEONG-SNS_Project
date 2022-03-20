package com.hk.sns.doas;

import java.util.List;

import com.hk.sns.dtos.MainBoardDto;

public interface IMainBoardDao {

public boolean insertfileinfo(MainBoardDto dto);

	public List<MainBoardDto> getfilelist(String id);

	public List<MainBoardDto> otherlist(String id, String category);

	public boolean delBoard(String[] seqs);

	public boolean mainreport(int seq);
	
	public MainBoardDto mypostdetail(int seq);
	
	public boolean mainupboard(MainBoardDto dto);
	
	public boolean good(int seq);
	
	public List<MainBoardDto> mainereportlist();
}
