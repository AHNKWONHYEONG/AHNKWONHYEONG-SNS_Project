package com.hk.sns.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hk.sns.doas.IMainBoardDao;
import com.hk.sns.dtos.MainBoardDto;

@Service
public class MainService implements IMainBoardService {

	@Autowired
	private IMainBoardDao maindao;

	@Override
	public boolean insertfileinfo(HttpServletRequest request, String id, String category,
			String title, String content) {
				MultipartHttpServletRequest multi=(MultipartHttpServletRequest)request;
				MultipartFile multiFile=multi.getFile("filename");
				String originName=multiFile.getOriginalFilename();
				String path=request.getSession().getServletContext().getRealPath("uplaod");
				System.out.println("파일저장경로:"+path);
				File f=new File(path+"/"+originName);
				boolean isS=false;
				try {
					multiFile.transferTo(f);
					isS=maindao.insertfileinfo(new MainBoardDto(0, id, category, originName, title, content, 0, null, null, null));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return isS;
	}

	@Transactional
	@Override
	public boolean multiInsertFileInfo(HttpServletRequest request, String id, String category,
			String title, String content) {
				MultipartHttpServletRequest multi=(MultipartHttpServletRequest)request;
				List<MultipartFile> multiFiles=multi.getFiles("filename");
				boolean isS=false;
				for(MultipartFile multiFile:multiFiles ) {
					String originName=multiFile.getOriginalFilename();
					String path=request.getSession().getServletContext().getRealPath("upload");
					System.out.println("파일저장경로:"+path);
					File f=new File(path+"/"+originName);
					try {
						multiFile.transferTo(f);
						isS=maindao.insertfileinfo(new MainBoardDto(0, id, category, originName, title, content, 0, null, null, null));
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				return isS;
	}

	@Override
	public List<MainBoardDto> getfilelist(String id) {
		// TODO Auto-generated method stub
		return maindao.getfilelist(id);
	}

	@Override
	public List<MainBoardDto> otherlist(String id, String category) {
		// TODO Auto-generated method stub
		return maindao.otherlist(id, category);
	}

	@Override
	public boolean delBoard(String[] seqs) {
		return maindao.delBoard(seqs);
	}

	@Override
	public boolean mainreport(int seq) {
		return maindao.mainreport(seq);
	}

	@Override
	public MainBoardDto mypostdetail(int seq) {
		return maindao.mypostdetail(seq);
	}

	@Transactional
	@Override
	public boolean mainupboard(HttpServletRequest request,int seq, String category,
			String title, String content) {
		MultipartHttpServletRequest multi=(MultipartHttpServletRequest)request;
		List<MultipartFile> multiFiles=multi.getFiles("filename");
		boolean isS=false;
		for(MultipartFile multiFile:multiFiles ) {
			String originName=multiFile.getOriginalFilename();
			String path=request.getSession().getServletContext().getRealPath("upload");
			System.out.println("파일저장경로:"+path);
			File f=new File(path+"/"+originName);
			try {
				multiFile.transferTo(f);
				isS=maindao.mainupboard(new MainBoardDto(seq, category, originName, title, content));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return isS;
	}

	@Override
	public boolean good(int seq) {
		return maindao.good(seq);
	}

	@Override
	public List<MainBoardDto> mainereportlist() {
		return maindao.mainereportlist();
	}



}
