<%@include file="header.jsp" %>
<%@page import="com.hk.sns.dtos.BoardDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.sns.utils.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글목록보기</title>
<script type="text/javascript">
	//전체 체크박스 기능
	function allSel(bool){//bool은 체크여부를 받는다.
		var chks=document.getElementsByName("chk");//chks[chk,chk,....]
		for (var i = 0; i < chks.length; i++) {
			chks[i].checked=bool;//각각의 체크박스에 체크여부 적용
		}
	}
</script>
</head>
<%
	//requst객체에 저장되는 객체들은 모두 object로 형변환되기 떄문에
	//다시 downcasting해야한다.
	List<BoardDto> list=(List<BoardDto>)request.getAttribute("list");
// 이미 저장할 데이터의 타입을 알고 있는 경우
// 	int[] i={1,2,3,4};
// 	List<HkDto> list2;
	if(list==null){
		request.setAttribute("msg", "요청글에 대한 정보가 없습니다.");
		pageContext.forward("error.jsp");
	}
%>
<body>
<h1>게시판 글목록</h1>
<form action="muldel.do" method="post">
<!-- <input type="hidden" name="command" value="muldel"/> -->
<table border="1">
	<col width="30px">
	<col width="50px">
	<col width="100px">
	<col width="300px">
	<col width="200px">
	<tr>
		<th><input type="checkbox" name="all" onclick="allSel(this.checked)" /></th>
		<th>번호</th><th>작성자</th><th>제목</th><th>작성일</th>
<!-- 		<th>삭제여부</th> -->
	</tr>
	<%
		for(int i=0; i<list.size(); i++){
			BoardDto dto=list.get(i);//list[dto,dto,dto....]->순차적으로 하나씩 꺼냄
			%>
			<tr>
				<td><input type="checkbox" name="chk" value="<%=dto.getSeq()%>"/></td>
				<td><%=dto.getSeq()%></td>
				<td><%=dto.getId()%></td>
				<td><a href="searchboard.do?seq=<%=dto.getSeq()%>"><%=dto.getTitle()%></a></td>
				<td><%=dto.getRegdate()%></td>
<%-- 				<td><%=dto.getReport() %></td> --%>
<%-- 				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.regdate}"/></td> --%>
			</tr>				
			<%
		}
	%>
	<tr>
		<td colspan="5">
			<a href="insertform.do"><button type="button">글쓰기</button></a>
			<input type="submit" value="글삭제" />
			<a href="a_main.do"><button type="button">메인</button></a>
		</td>
	</tr>
</table>
</form>
</body>
</html> 