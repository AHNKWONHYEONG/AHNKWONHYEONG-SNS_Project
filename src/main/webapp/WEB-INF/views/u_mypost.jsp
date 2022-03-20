<%@include file="header.jsp"%>
<%@page import="com.hk.sns.dtos.MainBoardDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<%
response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
	function allSel(bool) {
		var chks = document.getElementsByName("chk");
		for (var i = 0; i < chks.length; i++) {
			chks[i].checked = bool;
		}
	}

	function mypage() {
		location.href = "mypage.do";
	}
</script>
<style type="text/css">
	#tablebox{
		width: 500px;
		margin: 0 auto;
	}
</style>
</head>
<%
List<MainBoardDto> list = (List<MainBoardDto>) request.getAttribute("list");
String id = (String) request.getAttribute("id");
if (list == null) {
	request.setAttribute("msg", "요청글에 대한 정보가 없습니다.");
	pageContext.forward("error.jsp");
}
%>
<body>
	<div id="tablebox">
	<h1>나의 게시물</h1>
		<form action="mypostmuldel.do" method="post">
			<input type="hidden" name="id" value="<%=id%>" />
			<table border="1">
				<tr>
					<td colspan="9"><input type="submit" value="글삭제" /> 
					<input type="button" onclick="mypage()" value="메인" /></td>
				</tr>
				<%
				for (int i = 0; i < list.size(); i++) {
					MainBoardDto dto = list.get(i);
				%>
				<tr>
					<th></th>
					<th><input type="checkbox" name="all" onclick="allSel(this.checked)" />전체선택</th>
				</tr>
				<tr>
					<th>번호</th>
					<td><a href="mypostupdateform.do?seq=<%=dto.getSeq()%>"><%=dto.getSeq()%></a></td>
				</tr>
				<tr>
					<th>아이디</th>
					<td><%=dto.getId()%></td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td><%=dto.getCategory()%></td>
				</tr>
				<tr>
					<th>이미지1</th>
					<td><img src="upload/<%=dto.getImg_1()%>" width="300px;" height="300px;"></td>
				<tr>
					<th>제목</th>
					<td><%=dto.getTitle()%></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><%=dto.getContent()%></td>
				</tr>
				<tr>
					<th>좋아요</th>
					<td><%=dto.getGood()%></td>
				</tr>
				<tr>
					<th>작성일</th>
					<td><%=dto.getRegdate()%></td>
				</tr>
				<%
				}
				%>
				<tr>
					<td colspan="9"><input type="submit" value="글삭제" /> <input
						type="button" onclick="mypage()" value="메인" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
