<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/board/removeBoard">
	<h2>삭제하시겠습니까?</h2>
	<input type="hidden" name="boardNo" value="${boardNo}">
	<button type="submit">삭제</button>
</form>
</body>
</html>