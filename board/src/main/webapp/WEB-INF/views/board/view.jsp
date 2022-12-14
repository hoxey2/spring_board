<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 조회</title>
</head>
<body>
	<div id="nav">
		<%@ include file="../include/nav.jsp"%>
	</div>

	<form method="post">
		<label>제목 : </label> ${view.title}<br />
		<hr />
		<label>작성자 : </label> ${view.writer}<br />
		<hr />
		<label>내용 : </label> ${view.content}<br />
		<hr />

		<div>
			<a href="/board/modify?bno=${view.bno}">게시물 수정</a> / <a
				href="/board/delete?bno=${view.bno}">게시물 삭제</a>
		</div>


		<!-- 댓글 시작  -->

		<hr />

		<ul>
			<c:forEach items="${reply}" var="reply">
				<li>
					<div>
						<p>${reply.writer}/ ${reply.regDate}</p>
						<p>${reply.content }</p>
					</div>
				</li>
			</c:forEach>
		</ul>

		<div>
			<p>
				<label>댓글 작성자</label> <input type="text">
			</p>
			<p>
				<textarea rows="5" col="50"></textarea>
			</p>
			<p>
				<button type="button">댓글 작성</button>
			</p>
		</div>

		<!-- 댓글 끝  -->
	</form>

</body>
</html>
