<%@page import="com.hk.sns.dtos.MainBoardDto"%>
<%@include file="header.jsp" %>
<%@page import="com.hk.sns.dtos.MessageDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
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
	List<MainBoardDto> list=(List<MainBoardDto>)request.getAttribute("list");
// 이미 저장할 데이터의 타입을 알고 있는 경우
// 	int[] i={1,2,3,4};
// 	List<HkDto> list2;
	if(list==null){
		request.setAttribute("msg", "요청글에 대한 정보가 없습니다.");
		pageContext.forward("error.jsp");
	}
%>
<body>
<h1>보낸메시지함</h1>
<form action="smessagemuldel.do" method="post">
<input type="hidden" name="id" value="${ldto.id}"/>
<table border="1">
	<col width="100px">
	<col width="100px">
	<col width="300px">
	<col width="70px">
	<col width="70px">
	<col width="200px">
	<tr>
<!-- 		<th><input type="checkbox" name="all" onclick="allSel(this.checked)" /></th> -->
		<th>작성자</th><th>받은 아이디</th><th>내용</th><th>신고여부</th><th>신고횟수</th><th>메시지전송날짜</th>
<!-- 		<th>삭제여부</th> -->
	</tr>
	<%
		for(int i=0; i<list.size(); i++){
			MainBoardDto dto=list.get(i);//list[dto,dto,dto....]->순차적으로 하나씩 꺼냄
			%>
			<tr>
				<td><%=dto.getSeq()%></td>
				<td><%=dto.getId()%></td>
				<td><img src="upload/<%=dto.getImg_1()%>" width="300px;" height="300px;" ></td>
				<td><%=dto.getTitle()%></td>
				<td><%=dto.getContent()%></td>
				<td><%=dto.getGood()%></td>
				<td><%=dto.getRegdate()%></td>
				<td><a href="mainyellowmessage.do?seq=<%=dto.getSeq()%>&s_id=${ldto.id}&id=<%=dto.getId()%>">경고메시지</a></td>
<%-- 				<td><%=dto.getReport() %></td> --%>
<%-- 				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.regdate}"/></td> --%>
			</tr>				
			<%
		}
	%>
	<tr>
		<td colspan="6">
			<a href="#"><button type="button">메시지작성</button></a>
<!-- 			<input type="submit" value="글삭제" /> -->
			<a href="a_main.do"><button type="button">메인</button></a>
		</td>
	</tr>
</table>
</form>
</body>
</html> 