<%@include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
	#tablebox{width: 500px;margin: 0 auto;}
	#replyForm{	display: none;}
	#tablebox{overflow: auto;	height: 500px;}
</style>
</head>
<body>
<div id="tablebox">
<h1>관리자 게시글 상세보기</h1>
<table class="table" class="table table-hover">
	<tr>
		<th>작성자</th>
		<td id="check">${dto.id}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${dto.title}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" class="form-control" readonly>${dto.content}</textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<button type="button" class="btn btn-primary" onclick="updateForm(${dto.seq})">수정</button>
			<button type="button" class="btn btn-danger" onclick="delBoard(${dto.seq})">삭제</button>
			<button type="button" class="btn btn-warning" onclick="replyForm()" >답글</button>
			<button type="button" class="btn btn-info" onclick="boardList()" >목록</button>
		</td>
		<td><button type="button" class="btn btn-primary" onclick="like(${dto.seq})">좋아요</button></td>
	</tr>
</table>
<hr/>
	<div id="replyForm">
	<h1>답글추가하기</h1>
	<form action="replyboard.do" method="post">
<!-- 	<input type="hidden" name="command" value="replyboard"/> -->
	<input type="hidden" name="seq" value="${dto.seq}"/>
		<table class="table" class="table table-hover">
			<tr>
				<th>작성자</th>
				<td><input readonly="readonly" type="text" name="id" class="form-control" value="${ldto.id}"/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input required="required" type="text" name="title" class="form-control"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea required="required" rows="10" cols="60" name="content" class="form-control"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="답글추가" class="btn btn-primary"/>
					<button type="button" class="btn btn-info" onclick="boardList()" >취소</button>
				</td>
			</tr>
		</table>
	</form>
	</div>
</div>
<script type="text/javascript">
	
	function delBoard(seq){
		//글삭제 기능
		location.href="muldel.do?chk="+seq;
	}
	
	//답글 폼의 위치를 스크롤 이동을 통해 보여주는 기능
	function replyForm(){
		$("#replyForm").show();
		var replyPosition=$("#replyForm").offset().top;//div의 상단위치를 구함
// 		alert(replyPosition);
		$("#tablebox").animate({
			"scrollTop":replyPosition
		},1000);
	}
	//글 수정
	function updateForm(seq){
 		location.href="updateform.do?seq="+seq;
	}
		
	//글 목록 돌아가기
	function boardList(){
		location.href="qaboard.do";
	}
	function like(seq){
		location.href="like.do?seq="+seq;
	}
</script>
</body>
</html> 