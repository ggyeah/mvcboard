<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시글입력</h2>
	<form method="post" action="/board/addBoard" enctype="multipart/form-data">
		<table>
			<tr>
				<th>localName</th>
				<td><input type ="text" name="localName"></td>
			</tr>
			<tr>
				<th>boardTitle</th>
				<td><input type ="text" name="boardTitle"></td>
			</tr>
			<tr>
				<th>boardContent</th>
				<td><input type ="text" name="boardContent"></td>
			</tr>
			<tr>
				<th>memberId</th>
				<td><input type ="text" name="memberId"></td>
			</tr>
			<tr>
				<th>file</th>
				<td><input type ="file" name="MultipartFile" multiple></td>
			</tr>
		</table>
		<button type="submit">입력</button>
	</form>
</body>
</html>