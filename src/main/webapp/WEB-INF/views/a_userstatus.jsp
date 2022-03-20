<%@include file="header.jsp" %>
<%@page import="com.hk.sns.dtos.LDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원전체조회(상태정보)</title>
</head>
<%
	List<LDto> list=(List<LDto>)request.getAttribute("list");
%>
<body>
<h1>회원전체조회(상태정보)</h1>
<table border="1">
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>주소</th>
		<th>전화번호</th>
		<th>이메일</th>
		<th>사용여부</th>
		<th>등급</th>
		<th>가입일</th>
	</tr>
	<%
		if(list==null||list.size()==0){
			%>
				<tr>
					<td colspan="8">----가입한 회원이 없습니다.----</td>
				</tr>
			<%
		}else{
			for(int i=0; i<list.size(); i++){				
				LDto dto=list.get(i);
			%>
				<tr>
					<td><%=dto.getId()%></td>
					<td><%=dto.getName()%></td>
					<td><%=dto.getAddress()%></td>
					<td><%=dto.getPhone()%></td>
					<td><%=dto.getEmail()%></td>
					<td><%=dto.getEnabled().toUpperCase().equals("Y")?"사용중":"탈퇴"%></td>
					<td><%=dto.getGrade()%></td>
					<td><%=dto.getRegdate()%></td>
				</tr>
			<%
			}
		}
	%>
	<tr>
		<td colspan="8">
			<button onclick="location.href='a_main.do'">메인</button>
		</td>
	</tr>
</table>
</body>
</html>



