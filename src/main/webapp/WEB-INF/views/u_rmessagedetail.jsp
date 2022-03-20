<%@include file="header.jsp" %>
<%@page import="com.hk.sns.dtos.MessageDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>받은 메시지 상세보기</title>
<style type="text/css">
	#replyForm{	display: none;}
</style>
</head>
<%
	MessageDto dto=(MessageDto)request.getAttribute("dto");
	int seq=(int)request.getAttribute("seq");
%>
<body>
<h1>받은 메시지 상세보기</h1>
<form action="report.do" method="post">
<input type="hidden" name="seq" value="<%=seq%>"/>
<table border="1" class="table" class="table table-hover">
	<tr>
		<th>보낸 아이디</th>
		<td><%=dto.getS_id()%></td>
	</tr>
	<tr>
		<th>받은 아이디</th>
		<td><%=dto.getId()%></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getContent()%></textarea></td>
	</tr>
	<tr>
		<th>등록일</th>
		<td><%=dto.getRegdate()%></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit"  value="신고" />
<!-- 			<input type="button" onclick="delBoard()" value="삭제" /> -->
			<input type="button" onclick="boardList('<%=dto.getId()%>')" value="목록" />
			<input type="button" onclick="replyForm()" value="답장" />
		</td>
	</tr>
</table>
</form>
<hr/>
	<div id="replyForm">
	<h1>답글추가하기</h1>
	<form action="insertmessage.do" method="post">
	<input type="hidden" name="seq" value="${dto.seq}"/>
		<table class="table" class="table table-hover">
			<tr>
				<th>보낸 아이디</th>
				<td><input readonly="readonly" type="text" name="s_id" value="<%=dto.getId()%>"/></td>
			</tr>
			<tr>
				<th>받은 아이디</th>
				<td><input readonly="readonly" type="text" name="id" value="<%=dto.getS_id()%>"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea required="required" rows="10" cols="60" name="content" ></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="답장하기" />
					<button type="button"  onclick="boardList('<%=dto.getId()%>')" >취소</button>
				</td>
			</tr>
		</table>
	</form>
	</div>
<script type="text/javascript">
	function replyForm(){
		$("#replyForm").show();
		var replyPosition=$("#replyForm").offset().top;
		$("#tablebox").animate({
			"scrollTop":replyPosition
		},1000);
	}
// 	function delBoard(){
<%-- 		location.href="muldel.do?chk=<%=dto.getSeq()%>"; --%>
// 	}
	function boardList(id){
		location.href="urmessage.do?id="+id;
	}
	
// 	function report(){
// 		if(confirm("신고하시겠습니까?")){
// 			alert("신고를 완료 합니다.");
// 		}else{
// 			alert("신고를 취소 합니다.");
// 		}
// 	}
</script>
</body>
</html> 