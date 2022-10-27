<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Homepage</title>
</head>
<body>
<h1>
	Hoxey2 Board  
</h1>

<P>  실행시간 :  ${serverTime}. </P>

<p>
<a href="/board/listPageSearch?num=1">글 목록(페이징 + 검색)</a><br/>
<a href="/board/write">게시물 작성</a>
</p>

</body>
</html>
