<%@include file="header.jsp" %>
<%@page import="com.hk.sns.dtos.LDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<%
	response.setHeader("Cache-control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
	a{
		text-decoration: none;
	}
	li{
		list-style: none;
	}
	.submenu>ul>li{
		display: none;
	}
	
	.submenu:hover{
		cursor: pointer;
	}
	
	.submenu:hover>ul>li{
		display: block;		
	}
</style>
</head>
<%
	//scope객에 저장하면 모두 object 타입
	LDto ldto=(LDto)session.getAttribute("ldto");
	
	//session에 로그인 정보가 ㅇ벗다면(로그아웃) 로그인 페이지로 이동
	if(ldto==null){
		pageContext.forward("index.jsp");
// 		response.sendRedirect("index.jsp");
	}
%>
<body>
<h1>마이페이지</h1>
<div class="info">
	<ul>
		<li><span>${ldto.id}</span>님 반갑습니다.(등급:<%=ldto.getGrade().equals("USER")?"일반회원":"준회원" %>)</li>
		<li><span>${ldto.id}</span><a href="friendlist.do?my_id=${ldto.id}">님 친구목록</a></li>
		<li><a href="afterlogout.do">로그아웃</a></li>
	</ul>
</div>

<div class="menu1">
	<ul>
		<li>
			<a href="mypost.do?id=${ldto.id}">사용자 게시물</a>
		</li>
		<li>
			<a href="calendar.do">즐겨찾기</a>
		</li>
		<li>
			<a href="calendar.do">회원탈퇴</a>
		</li>
	</ul>
</div>

<div class="menu2">
	<ul>
		<li class="submenu">메시지함
			<ul>
				<li ><a href="usmessage.do?s_id=${ldto.id}">보낸 메시지함</a></li>
				<li><a href="urmessage.do?id=${ldto.id}">받은 메시지함</a></li>
			</ul>
		</li>
		<li class="submenu">
			<a href="insert1.do">게시물작성</a>
		</li>
		<li class="submenu">다른사용자게시물보기
			<ul>
				<li><a href="other1.do?id=${ldto.id}&category=여행장소">여행장소</a></li>
				<li><a href="other1.do?id=${ldto.id}&category=맛집">맛집</a></li>
				<li><a href="other1.do?id=${ldto.id}&category=숙박시설">숙박시설</a></li>
			</ul>
		</li>
		<li>
			<a href="unoticeboard.do">공지게시판</a>
		</li>
		<li>
			<a href="uqaboard.do">Q&A게시판</a>
		</li>
	</ul>
</div>
</body>
</html>


