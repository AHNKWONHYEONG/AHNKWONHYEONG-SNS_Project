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
	
	//session에 로그인 정보가 없다면(로그아웃) 로그인 페이지로 이동
	if(ldto==null){
		pageContext.forward("index.jsp");
// 		response.sendRedirect("index.jsp");
	}
%>
<body>
<h1>관리자 메인</h1>
<div id="info">
	<span><%=ldto.getName() %></span>님 반갑습니다.(아이디:<%=ldto.getId() %>)
	<a href="afterlogout.do">로그아웃</a>
</div>

<div id="menu">
	<ul>
		<li>
			<a href="anoticeboard.do">공지게시판</a>
		</li>
		<li>
			<a href="qaboard.do">Q&A게시판</a>
		</li>
		<li class="submenu">신고게시물
			<ul>
				<li><a href="amainreportlist.do">게시물 신고</a></li>
				<li><a href="#">답변 신고</a></li>
				<li><a href="amessagereport.do">메시지 신고</a></li>
			</ul>
		</li>
		<li class="submenu">회원관리
			<ul>
				<li><a href="userliststatus.do">회원상태정보조회</a></li>
				<li><a href="userlist.do">회원정보조회(사용가능계정)</a></li>
			</ul>
		</li>
	</ul>
</div>
</body>
</html>



