<%@include file="header.jsp" %>
<%@page import="com.hk.sns.dtos.LDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	errorPage="index.jsp"
%>
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
<title>회원정보목록조회</title>
<script type="text/javascript">
	function updateRole(id){
		location.href="updateroleform.do?id="+id;
	}
</script>
</head>
<%
	//scope객에 저장하면 모두 object 타입
	LDto ldto=(LDto)session.getAttribute("ldto");
	
	//session에 로그인 정보가 없다면(로그아웃) 로그인 페이지로 이동
	if(ldto==null){
		pageContext.forward("index.jsp");
	//		response.sendRedirect("index.jsp");
	}
	List<LDto> list=(List<LDto>)request.getAttribute("list");
%>
<body>
<h1>회원정보목록조회</h1>
<table border="1">
	<tr>
		<th>번호</th>
		<th>아이디</th>
		<th>이름</th>
		<th>등급</th>
		<th>등급변경</th>
	</tr>
	<%
		if(list==null||list.size()==0){
			%>
				<tr>
					<td colspan="5">----가입한 회원이 없습니다.----</td>
				</tr>
			<%
		}else{
			for(int i=0; i<list.size(); i++){				
				LDto dto=list.get(i);
			%>
				<tr>
					<td><%=i+1 %></td>
					<td><%=dto.getId()%></td>
					<td><%=dto.getName()%></td>	
					<td><%=getRoleName(dto.getGrade())%></td>
					<td><button <%=dto.getId().equals(ldto.getId())?"disabled title='자신은 변경할 수 없습니다.'":"" %> 
					onclick="updateRole('<%=dto.getId()%>')">변경</button></td>
				</tr>
			<%
			}
		}
	%>
	<tr>
		<td colspan="5">
			<button onclick="location.href='a_main.do'">메인</button>
		</td>
	</tr>
</table>
<%! //자바 선언부: 메서드 선언코드 작성
	public String getRoleName(String rName){
	String s="";
	switch(rName){
	case "ADMIN":s="관리자"; break;
	case "USER":s="일반회원"; break;
	case "MANAGER":s="정회원"; break;
	default : s="미정"; break;
	}
	return s;
}
%>
</body>
</html>



