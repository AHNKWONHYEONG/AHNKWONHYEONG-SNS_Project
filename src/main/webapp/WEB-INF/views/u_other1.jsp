<%@include file="header.jsp" %>
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
<style type="text/css">
	table{
		table-layout: fixed;
	}
</style>
<script type="text/javascript">
	//전체 체크박스 기능
	function allSel(bool) {//bool은 체크여부를 받는다.
		var chks = document.getElementsByName("seq");//chks[chk,chk,....]
		for (var i = 0; i < chks.length; i++) {
			chks[i].checked = bool;//각각의 체크박스에 체크여부 적용
		}
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
//requst객체에 저장되는 객체들은 모두 object로 형변환되기 떄문에
//다시 downcasting해야한다.
List<MainBoardDto> list = (List<MainBoardDto>) request.getAttribute("list");
String lid = (String) session.getAttribute("lid");
// 이미 저장할 데이터의 타입을 알고 있는 경우
// 	int[] i={1,2,3,4};
// 	List<HkDto> list2;
if (list == null) {
	request.setAttribute("msg", "요청글에 대한 정보가 없습니다.");
	pageContext.forward("error.jsp");
}
%>
<body>
	<div id="tablebox">
	<h1>다른 사용자 게시물</h1>
	<form action="star.do" method="post">
		<table border="1">
			<col width="100px">
			<col width="200px">
			<tr>
				<td colspan="9">
					<input type="submit" value="즐겨찾기" /> 
					<a href="mypage.do"><button	type="button">메인</button></a>
				</td>
			</tr>
			<tr>
				<th>전체선택</th>
				<th><input type="checkbox" name="all" onclick="allSel(this.checked)" /></th>
			</tr>
			<%
			for (int i = 0; i < list.size(); i++) {
				MainBoardDto dto = list.get(i);//list[dto,dto,dto....]->순차적으로 하나씩 꺼냄
			%>
			<tr>
				<th>선택</th>
				<th><input type="checkbox" name="seq" value="<%=dto.getSeq() %>" /></th>
			</tr>
			<tr>
				<th>게시물 신고</th>
				<td><a href="mainreport.do?seq=<%=dto.getSeq()%>&category=<%=dto.getCategory()%>&id=${ldto.id}">신고하기</a></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td><a href="addfriend3.do?id=<%=dto.getId()%>"><%=dto.getId()%></a></td>
			</tr>
			<tr>
				<th>카테고리</th>
				<td><%=dto.getCategory()%></td>
			</tr>
			<tr>
				<th>이미지1</th>
				<td><img src="upload/<%=dto.getImg_1()%>" width="300px;"
					height="300px;"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><%=dto.getTitle()%></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><a href="mainansboard.do?seq=<%=dto.getSeq()%>"><%=dto.getContent()%></a></td>
				<!-- 메인게시글 답변형게시판 제작 -->
			</tr>
			<tr>
				<th>좋아요</th>
				<td><a href="like2.do?seq=<%=dto.getSeq()%>&id=${ldto.id}&category=<%=dto.getCategory()%>"><img
						src="img/good.JPG" width="20;" height="20;" /></a><%=dto.getGood()%></td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><%=dto.getRegdate()%></td>
			</tr>
			<%
			}
			%>
			<tr>
				<td colspan="9">
					<input type="submit" value="즐겨찾기" /> 
					<a href="mypage.do"><button	type="button">메인</button></a>
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html> 