package com.hk.sns;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.sns.dtos.AnsDto;
import com.hk.sns.dtos.BoardDto;
import com.hk.sns.dtos.FriendDto;
import com.hk.sns.dtos.LDto;
import com.hk.sns.dtos.MainBoardDto;
import com.hk.sns.dtos.MessageDto;
import com.hk.sns.service.IAnsService;
import com.hk.sns.service.IBoardService;
import com.hk.sns.service.IFriendService;
import com.hk.sns.service.ILoginService;
import com.hk.sns.service.IMainBoardService;
import com.hk.sns.service.IMessageService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired ILoginService lService;
	@Autowired IAnsService aService;
	@Autowired IBoardService bService;
	@Autowired IMainBoardService mService;
	@Autowired IMessageService messageService;
	@Autowired IFriendService fService;

	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "home";
	}

	@RequestMapping(value = "/registform.do", method = RequestMethod.GET)
	public String registform(Locale locale, Model model) {
		logger.info("회원가입폼이동 {}.", locale);

		return "registform";
	}

	@RequestMapping(value = "/regist.do", method = RequestMethod.POST)
	public String regist(LDto dto, Locale locale, Model model) {
		logger.info("회원가입성공 {}.", locale);

		boolean isS=lService.insertUser(dto);
		if(isS) {
			return "redirect:index.jsp";
		}else {
			return "error";			
		}		
	}

	@RequestMapping(value = "/idchk.do", method = RequestMethod.GET)
	public String idchk(String id,Locale locale, Model model) {
		logger.info("중복체크.", locale);
		String idchk=lService.idChk(id);
		model.addAttribute("id",idchk);
		return "idchk";
	}

	@RequestMapping(value = "/afterlogin.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String afterlogin(HttpServletRequest request, HttpServletResponse response,
			String id, String password, Locale locale, Model model) {
		logger.info("로그인.", locale);
		HttpSession session=request.getSession();
		LDto dto=lService.getLogin(id, password);

		if(dto.getId()!=null) {
			session.setAttribute("ldto", dto);
			session.setMaxInactiveInterval(10*60);


			if(dto.getGrade().equals("ADMIN")){
				return "admin";
			}
			else if(dto.getGrade().equals("MANAGER")){
				return "manager_main";
			}
			else {
				return "u_mypage";
			}
		}
		return "";
	}

	@RequestMapping(value = "/afterlogout.do", method = RequestMethod.GET)
	public String afterlogout(HttpServletRequest request, Locale locale, Model model) {
		logger.info("로그아웃 {}.", locale);
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:index.jsp";				
	}

//	--------------------------------------------관리자 Q&A

	@RequestMapping(value = "/qaboard.do", method = RequestMethod.GET)
	public String qaboard(HttpServletRequest request, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);
		List<AnsDto> list=aService.getAllList();
		model.addAttribute("list", list);

		return "a_qaboard";				
	}

	@RequestMapping(value = "/a_main.do", method = RequestMethod.GET)
	public String a_main(HttpServletRequest request, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);


		return "admin";				
	}

	@RequestMapping(value = "/detailboard.do", method = RequestMethod.GET)
	public String detailboard(HttpServletRequest request, HttpServletResponse response,int seq, Locale locale, Model model) {
		logger.info("상세보기 {}.", locale);

		AnsDto dto=aService.detailBoard(seq);
		model.addAttribute("dto",dto);

		return "a_qadetailboard";
	}

	@RequestMapping(value = "/like.do", method = RequestMethod.GET)
	public String like(int seq, Locale locale, Model model) {
		logger.info("좋아요 {}.", locale);

		boolean like=aService.readCount(seq);
		if(like) {
			return "redirect:qaboard.do";
		}else {
			return "error";
		}
	}

	@RequestMapping(value = "/updateform.do", method = RequestMethod.GET)
	public String updateform(AnsDto pdto, Locale locale, Model model) {

		logger.info("글수정폼이동 {}.", locale);

		AnsDto dto=aService.detailBoard(pdto.getSeq());
		model.addAttribute("dto",dto);
		return "a_qaupboard";
	}

	@RequestMapping(value = "/updateboard.do", method = RequestMethod.POST)
	public String updateboard(AnsDto dto, Locale locale, Model model) {

		logger.info("글수정성공 {}.", locale);

		boolean isS=aService.updateBoard(dto);
		if(isS) {
			return "redirect:qaboard.do";
		}else {
			model.addAttribute("msg", "글수정실패");
			return "error";
		}
	}

	@RequestMapping(value = "/muldel.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String muldel(String[] chk, Locale locale, Model model) {

		logger.info("글삭제 {}.", locale);

		boolean isS=aService.delBoard(chk);
		if(isS) {
			return "redirect:qaboard.do";
		}else {
			model.addAttribute("msg", "글삭제실패");
			return "error";
		}
	}

	@RequestMapping(value = "/replyboard.do", method = RequestMethod.POST)
	public String replyboard(AnsDto dto, Locale locale, Model model) {
		logger.info("답글 추가 {}.", locale);
		boolean isS=aService.replyBoard(dto);
		if(isS) {
			return "redirect:qaboard.do";
		}else {
			model.addAttribute("msg", "답글추가실패");
			return "error";
		}
	}

	@ResponseBody //responsebody는 클라이언트로 text를 출력해주는 기능
	@RequestMapping(value = "/contentAjax.do", method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json; charset=utf-8")
	public Map<String, AnsDto> contentAjax(int seq, Locale locale, Model model) {
//		@RequestParam Map<String, String> map id, title, content
		logger.info(" 글내용 미리보기 {}.", locale);
		//new AnsDto(map.get("id"),map.get("title"),map.get("content"))
		AnsDto dto=aService.detailBoard(seq);

		Date regdate=dto.getRegdate();//date갑 가져오기  //쿼리에서 date를 tochar로 변환하면 간단
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String regdateStr=sf.format(regdate);//date타입->String타입으로 변환
		dto.setRegdateStr(regdateStr);//dto에 String타입의 날짜를 저장
		dto.setRegdate(null);//regdate만 값을 다시 정의

		Map<String, AnsDto> map=new HashMap<>();
		map.put("dto", dto);

		return map;
	}
//--------------------------------------------관리자 공지
	@RequestMapping(value = "/anoticeboard.do", method = RequestMethod.GET)
	public String anoticeboard(Locale locale, Model model) {
		logger.info("글목록 {}.", locale);
		List<BoardDto> list=bService.getBoardList();
		model.addAttribute("list", list);

		return "a_noticeboard";				
	}

	@RequestMapping(value = "/insertform.do", method = RequestMethod.GET)
	public String insertform(HttpServletRequest request, HttpServletResponse response,
			Locale locale, Model model) {
		logger.info("글쓰기 폼 이동 {}.", locale);
		HttpSession session=request.getSession();
		session.getAttribute("ldto");
		return "a_inboard";
	}

	@RequestMapping(value = "/insertboard.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String insertform(BoardDto dto,HttpServletRequest request, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);
		boolean isS=bService.insertBoard(dto);

		if(isS) {//return "페이지명" -> forword방식으로 응답하는 것과 같다
//			List<HkDto> list=hkService.getBoardList();
//			model.addAttribute("list", list );
			return "redirect:anoticeboard.do";
		}else {
			model.addAttribute("msg","글추가실패");
			return "error";
		}			
	}

	@RequestMapping(value = "/searchboard.do", method = RequestMethod.GET)
	public String searchboard(int seq, Locale locale, Model model) {
		logger.info(" 상세보기 {}.", locale);

		BoardDto dto=bService.searchBoard(seq);
		model.addAttribute("dto", dto );

		return "a_noticedetailboard";
	}

	@RequestMapping(value = "/noticeupform.do", method = RequestMethod.GET)
	public String noticeupform(int seq, Locale locale, Model model) {
		logger.info("수정 폼 이동 {}.", locale);

		BoardDto dto=bService.searchBoard(seq);
		model.addAttribute("dto", dto);

		return "a_noticeupform";
	}

	@RequestMapping(value = "/noticeupboard.do", method = RequestMethod.POST)
	public String noticeupboard(BoardDto dto, Locale locale, Model model) {
		logger.info("글수정성공 {}.", locale);

		boolean isS=bService.upBoard(dto);
		if(isS) {
			return "redirect:anoticeboard.do";
		}else {
			model.addAttribute("msg", "글수정실패");
			return "error";
		}
	}
//	------------------------------------------------------------------
	@RequestMapping(value = "/userliststatus.do", method = RequestMethod.GET)
	public String userliststatus(Locale locale, Model model) {
		logger.info("회원상태조회 {}.", locale);
		List<LDto> list=lService.getAllUserStatus();
		model.addAttribute("list", list);
		return "a_userstatus";			
	}

	@RequestMapping(value = "/userlist.do", method = RequestMethod.GET)
	public String userlist(Locale locale, Model model) {
		logger.info("회원목록조회 {}.", locale);
		List<LDto> list=lService.getAllUser();
		model.addAttribute("list", list);
		return "a_userlist";			

	}

	@RequestMapping(value = "/updateroleform.do", method = RequestMethod.GET)
	public String updateroleform(String id, Locale locale, Model model) {
		logger.info("등급변경폼이동 {}.", locale);
		LDto dto=lService.getUser(id);
		model.addAttribute("dto", dto);
		return "a_gradeupform";			

	}

	@RequestMapping(value = "/afterupdaterole.do", method = RequestMethod.POST)
	public String afterupdaterole(String id, String grade, Locale locale, Model model) {
		logger.info("등급변경 {}.", locale);

		boolean isS=lService.updateRoleUser(id, grade);
		if(isS) {
			return "redirect:userlist.do";						
		}else {
			model.addAttribute("msg", "글수정실패");
			return "error";
		}		
	}
	
	//------------------------------관리자 게시물 신고
	@RequestMapping(value = "/amessagereport.do", method = RequestMethod.GET)
	public String amessagereport(HttpServletRequest request, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);

		List<MessageDto> list=messageService.messagereport();
		model.addAttribute("list", list);

		return "a_messagereport";				
	}
	
	@RequestMapping(value = "/yellowmessage.do", method = RequestMethod.GET)
	public String yellowmessage(String s_id, String id, int seq, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);

		boolean isS=messageService.yellowmessage(s_id, id, seq);
		if(isS) {
			return "redirect:amessagereport.do";
		}else {
			model.addAttribute("msg", "답글추가실패");
			return "error";
		}
						
	}
	
	@RequestMapping(value = "/amainreportlist.do", method = RequestMethod.GET)
	public String amainreportlist(Locale locale, Model model) {
		logger.info("글목록 {}.", locale);

		List<MainBoardDto> list=mService.mainereportlist();
		model.addAttribute("list", list);

		return "a_mainreport";				
	}
	
	@RequestMapping(value = "/mainyellowmessage.do", method = RequestMethod.GET)
	public String yellowmessage2(String s_id, String id, int seq, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);

		boolean isS=messageService.mainyellowmessage(s_id, id, seq);
		if(isS) {
			return "redirect:amainreportlist.do";
		}else {
			model.addAttribute("msg", "답글추가실패");
			return "error";
		}
						
	}
	
//--------------------------------------------------------------사용자
	//-----------------------게시물 작성
	@RequestMapping(value = "/insert1.do", method = RequestMethod.GET)
	public String insert1(HttpServletRequest request, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);


		return "u_insert_1";				
	}


	@RequestMapping(value = "/mypage.do", method = RequestMethod.GET)
	public String mypage(HttpServletRequest request, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);


		return "u_mypage";				
	}

	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, String id, String category,
			String title, String content, Locale locale, Model model) {
		logger.info("파일업로드 요청 {}.", locale);
//		boolean isS=fileService.insertFileInfo(request);
		boolean isS=mService.multiInsertFileInfo(request, id, category, title, content);
		if(isS) {
			List<MainBoardDto> list=mService.getfilelist(id);
			model.addAttribute("list",list);
			return "u_mypost";
		}

		return "home";
	}

//	------------------------------------메시지

	@RequestMapping(value = "/usmessage.do", method = RequestMethod.GET)
	public String usmessage(String s_id, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);
		List<MessageDto> list=messageService.sgetBoardList(s_id);
		model.addAttribute("list", list);
		return "u_smessage";				
	}


	@RequestMapping(value = "/urmessage.do", method = RequestMethod.GET)
	public String urmessage(String id, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);
		List<MessageDto> list=messageService.rgetBoardList(id);
		model.addAttribute("list", list);

		return "u_rmessage";				
	}



	@RequestMapping(value = "/sendmessage.do", method = RequestMethod.GET)
	public String sendmessage(Locale locale, Model model) {
		logger.info("글목록 {}.", locale);


		return "u_messageform";				
	}

	@RequestMapping(value = "/insertmessage.do", method = RequestMethod.POST)
	public String insertmessage(MessageDto dto,String s_id, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);

		boolean isS=messageService.insertBoard(dto);
		if(isS) {//return "페이지명" -> forword방식으로 응답하는 것과 같다
//			List<HkDto> list=hkService.getBoardList();
//			model.addAttribute("list", list );
			return "redirect:usmessage.do?s_id="+s_id;
		}else {
			model.addAttribute("msg","글추가실패");
			return "error";
		}		
	}

	@RequestMapping(value = "/messagedetail.do", method = RequestMethod.GET)
	public String messagedetail(int seq, Locale locale, Model model) {
		logger.info(" 상세보기 {}.", locale);

//		BoardDto dto=bService.searchBoard(seq);
//		model.addAttribute("dto", dto );
		MessageDto dto=messageService.messagedetail(seq);
		model.addAttribute("dto", dto);
		model.addAttribute("seq", seq);
		return "u_rmessagedetail";
	}

	@RequestMapping(value = "/ansmessage.do", method = RequestMethod.GET)
	public String ansmessage(MessageDto dto, Locale locale, Model model) {
		logger.info(" 상세보기 {}.", locale);
		model.addAttribute("dto", dto);

		return "u_messageform";
	}

	@RequestMapping(value = "/report.do", method = RequestMethod.POST)
	public String report(int seq, Locale locale, Model model) {
		logger.info("메시지 삭제 {}.", locale);

		boolean isS=messageService.report(seq);
		if(isS) {
			return "redirect:messagedetail.do?seq="+seq;
		}else {
			model.addAttribute("msg","글추가실패");
			return "error";
		}		
	}

//---------------------------------사용자게시물

	@RequestMapping(value = "/mypost.do", method = RequestMethod.GET)
	public String mypost(String id, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);

		List<MainBoardDto> list=mService.getfilelist(id);
		model.addAttribute("list",list);
		model.addAttribute("id",id);
		return "u_mypost";				
	}
	
	@RequestMapping(value = "/mypostmuldel.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String mypostmuldel(String[] chk, String id, Locale locale, Model model) {

		logger.info("글삭제 {}.", locale);

		boolean isS=mService.delBoard(chk);
		if(isS) {
			return "redirect:mypost.do?id="+id;
		}else {
			model.addAttribute("msg", "글삭제실패");
			return "error";
		}
	}
	
	@RequestMapping(value = "/mypostupdateform.do", method = RequestMethod.GET)
	public String mypostupdateform(int seq, Locale locale, Model model) {
		logger.info("수정 폼 이동 {}.", locale);

		MainBoardDto dto=mService.mypostdetail(seq);
		model.addAttribute("dto", dto);

		return "u_mypostupform";
	}
	
	@RequestMapping(value = "/mypostupdate.do", method = RequestMethod.POST)
	public String mypostupdate(HttpServletRequest request, int seq, String category,
			String title, String content, String id, Locale locale, Model model) {

		logger.info("글수정성공 {}.", locale);

		boolean isS=mService.mainupboard(request, seq, category, title, content);
		if(isS) {
			return "redirect:mypost.do?id="+id;
		}else {
			model.addAttribute("msg", "글수정실패");
			return "error";
		}
	}

	//---------------------------------다른사용자게시물
	@RequestMapping(value = "/other1.do", method = RequestMethod.GET)
	public String other1(String id, String category, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);

		List<MainBoardDto> list=mService.otherlist(id, category);
		model.addAttribute("list",list);

		return "u_other1";				
	}
	
	@RequestMapping(value = "/mainreport.do", method = RequestMethod.GET)
	public String mainreport(int seq, String id, String category, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);

		boolean isS=mService.mainreport(seq);
		if(isS) {
			model.addAttribute("id",id);
			model.addAttribute("category",category);
			return "redirect:other1.do";
		}else {
			model.addAttribute("msg","글추가실패");
			return "error";
		}
			
	}
	
	@RequestMapping(value = "/like2.do", method = RequestMethod.GET)
	public String like2(int seq, String id, String category, Locale locale, Model model) {
		logger.info("좋아요 {}.", locale);

		boolean like=mService.good(seq);
		if(like) {
			model.addAttribute("id",id);
			model.addAttribute("category",category);
			return "redirect:other1.do";
		}else {
			return "error";
		}
	}

	//---------------------------------유저공지게시판
	@RequestMapping(value = "/unoticeboard.do", method = RequestMethod.GET)
	public String unoticeboard(Locale locale, Model model) {
		logger.info("글목록 {}.", locale);
		List<BoardDto> list=bService.getBoardList();
		model.addAttribute("list", list);

		return "u_noticeboard";				
	}

	@RequestMapping(value = "/usearchboard.do", method = RequestMethod.GET)
	public String usearchboard(int seq, HttpServletRequest request,HttpServletResponse response, Locale locale, Model model) {
		logger.info(" 상세보기 {}.", locale);

//		String seq=request.getParameter("seq");
//		int sseq = Integer.parseInt(seq);
		BoardDto dto=bService.searchBoard(seq);
		model.addAttribute("dto", dto );

		return "u_noticedetailboard";
	}
	//---------------------------------유저 Q&A
	@RequestMapping(value = "/uqaboard.do", method = RequestMethod.GET)
	public String uqaboard(HttpServletRequest request, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);
		List<AnsDto> list=aService.getAllList();
		model.addAttribute("list", list);

		return "u_qaboard";				
	}

	@RequestMapping(value = "/uqainsertform.do", method = RequestMethod.GET)
	public String uqainsertform(Locale locale, Model model) {
		logger.info("글추가폼이동 {}.", locale);

		return "u_qainsertform";
	}

	@RequestMapping(value = "/qainsertboard.do", method = RequestMethod.POST)
	public String qainsertboard(AnsDto dto, Locale locale, Model model) {
//		@RequestParam Map<String, String> map id, title, content
		logger.info("글추가성공 {}.", locale);
		//new AnsDto(map.get("id"),map.get("title"),map.get("content"))
		boolean isS=aService.insertBoard(dto);
		if(isS) {
			return "redirect:uqaboard.do";
		}else {
			model.addAttribute("msg", "글추가실패");
			return "error";
		}
	}

	@RequestMapping(value = "/udetailboard.do", method = RequestMethod.GET)
	public String udetailboard(HttpServletRequest request, HttpServletResponse response,int seq, Locale locale, Model model) {
		logger.info("상세보기 {}.", locale);

		AnsDto dto=aService.detailBoard(seq);
		model.addAttribute("dto",dto);

		return "u_qadetailboard";
	}

	@RequestMapping(value = "/uupdateform.do", method = RequestMethod.GET)
	public String uupdateform(AnsDto pdto, Locale locale, Model model) {

		logger.info("글수정폼이동 {}.", locale);

		AnsDto dto=aService.detailBoard(pdto.getSeq());
		model.addAttribute("dto",dto);
		return "u_qaupboard";
	}

	@RequestMapping(value = "/uupdateboard.do", method = RequestMethod.POST)
	public String uupdateboard(AnsDto dto, Locale locale, Model model) {

		logger.info("글수정성공 {}.", locale);

		boolean isS=aService.updateBoard(dto);
		if(isS) {
			return "redirect:uqaboard.do";
		}else {
			model.addAttribute("msg", "글수정실패");
			return "error";
		}
	}

	@RequestMapping(value = "/ureplyboard.do", method = RequestMethod.POST)
	public String ureplyboard(AnsDto dto, Locale locale, Model model) {
//		@RequestParam Map<String, String> map id, title, content
		logger.info("답글 추가 {}.", locale);
		//new AnsDto(map.get("id"),map.get("title"),map.get("content"))
		boolean isS=aService.replyBoard(dto);
		if(isS) {
			return "redirect:uqaboard.do";
		}else {
			model.addAttribute("msg", "답글추가실패");
			return "error";
		}
	}
	
	//--------------------친구
	@RequestMapping(value = "/friendlist.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String friendlist(String my_id, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);
		List<FriendDto> list=fService.getFriendList(my_id);
		model.addAttribute("list", list);

		return "u_friend";				
	}
	
	@ResponseBody 
	@RequestMapping(value = "/addfriend.do", method = {RequestMethod.POST,RequestMethod.GET},produces = "application/text; charset=utf-8")
	public String addfriend(String id, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);
		String idchk=lService.idChk(id);
		

		return idchk;				
	}
	
	@RequestMapping(value = "/addfriend2.do", method = RequestMethod.GET)
	public String addfriend2(String id, String my_id, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);
		boolean isS=fService.insertFriend(id, my_id);

		if(isS) {
			return "redirect:friendlist.do";
		}else {
			model.addAttribute("msg", "실패");
			return "error";
		}
					
	}
	
	@RequestMapping(value = "/addfriend3.do", method = RequestMethod.GET)
	public String addfriend3(String id, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);
			
		model.addAttribute("id", id );
		return "u_addfriend";
					
	}
	
	@RequestMapping(value = "/frienddel.do", method = RequestMethod.GET)
	public String frienddel(String[] seq, String id, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);
			
		boolean isS=fService.delFriend(seq);
		if(isS) {
			model.addAttribute("my_id", id );
			return "redirect:friendlist.do";
		}else {
			model.addAttribute("msg", "친구삭제실패");
			return "error";
		}		
	}
	
//	--------------------------------------------유저 메인 게시판 Q&A
	@RequestMapping(value = "/mainansboard.do", method = RequestMethod.GET)
	public String mainansboard(int seq, Locale locale, Model model) {
		logger.info("글목록 {}.", locale);
		MainBoardDto dto=mService.mypostdetail(seq);
		model.addAttribute("dto", dto);

		return "u_mainansboard";				
	}

//	@RequestMapping(value = "/a_main.do", method = RequestMethod.GET)
//	public String a_main(HttpServletRequest request, Locale locale, Model model) {
//		logger.info("글목록 {}.", locale);
//
//
//		return "admin";				
//	}
//
//	@RequestMapping(value = "/detailboard.do", method = RequestMethod.GET)
//	public String detailboard(HttpServletRequest request, HttpServletResponse response,int seq, Locale locale, Model model) {
//		logger.info("상세보기 {}.", locale);
//
//		AnsDto dto=aService.detailBoard(seq);
//		model.addAttribute("dto",dto);
//
//		return "a_qadetailboard";
//	}
//
//	@RequestMapping(value = "/like.do", method = RequestMethod.GET)
//	public String like(int seq, Locale locale, Model model) {
//		logger.info("좋아요 {}.", locale);
//
//		boolean like=aService.readCount(seq);
//		if(like) {
//			return "redirect:qaboard.do";
//		}else {
//			return "error";
//		}
//	}
//
//	@RequestMapping(value = "/updateform.do", method = RequestMethod.GET)
//	public String updateform(AnsDto pdto, Locale locale, Model model) {
//
//		logger.info("글수정폼이동 {}.", locale);
//
//		AnsDto dto=aService.detailBoard(pdto.getSeq());
//		model.addAttribute("dto",dto);
//		return "a_qaupboard";
//	}
//
//	@RequestMapping(value = "/updateboard.do", method = RequestMethod.POST)
//	public String updateboard(AnsDto dto, Locale locale, Model model) {
//
//		logger.info("글수정성공 {}.", locale);
//
//		boolean isS=aService.updateBoard(dto);
//		if(isS) {
//			return "redirect:qaboard.do";
//		}else {
//			model.addAttribute("msg", "글수정실패");
//			return "error";
//		}
//	}
//
//	@RequestMapping(value = "/muldel.do", method = {RequestMethod.POST,RequestMethod.GET})
//	public String muldel(String[] chk, Locale locale, Model model) {
//
//		logger.info("글삭제 {}.", locale);
//
//		boolean isS=aService.delBoard(chk);
//		if(isS) {
//			return "redirect:qaboard.do";
//		}else {
//			model.addAttribute("msg", "글삭제실패");
//			return "error";
//		}
//	}
//
//	@RequestMapping(value = "/replyboard.do", method = RequestMethod.POST)
//	public String replyboard(AnsDto dto, Locale locale, Model model) {
//		logger.info("답글 추가 {}.", locale);
//		boolean isS=aService.replyBoard(dto);
//		if(isS) {
//			return "redirect:qaboard.do";
//		}else {
//			model.addAttribute("msg", "답글추가실패");
//			return "error";
//		}
//	}
//
//	@ResponseBody //responsebody는 클라이언트로 text를 출력해주는 기능
//	@RequestMapping(value = "/contentAjax.do", method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json; charset=utf-8")
//	public Map<String, AnsDto> contentAjax(int seq, Locale locale, Model model) {
////		@RequestParam Map<String, String> map id, title, content
//		logger.info(" 글내용 미리보기 {}.", locale);
//		//new AnsDto(map.get("id"),map.get("title"),map.get("content"))
//		AnsDto dto=aService.detailBoard(seq);
//
//		Date regdate=dto.getRegdate();//date갑 가져오기  //쿼리에서 date를 tochar로 변환하면 간단
//		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
//		String regdateStr=sf.format(regdate);//date타입->String타입으로 변환
//		dto.setRegdateStr(regdateStr);//dto에 String타입의 날짜를 저장
//		dto.setRegdate(null);//regdate만 값을 다시 정의
//
//		Map<String, AnsDto> map=new HashMap<>();
//		map.put("dto", dto);
//
//		return map;
//	}
}




