<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardOneList.jsp</title>
</head>
<body>
<h2>수정</h2>
<form method="post" action="/board/modifyBoard"  enctype="multipart/form-data">
    <table>
        <tr>
            <th>localName</th>
            <td><input type = "text" name="localName" value="${board.localName}"></td>
        </tr>
        <tr>
            <th>boardTitle</th>
            <td><input type = "text" name="boardTitle" value="${board.boardTitle}"></td>
        </tr>
        <tr>
            <th>boardContent</th>
            <td><input type = "text" name=boardContent value="${board.boardTitle}"></td>
        </tr>
        <tr>
            <th>memberId</th>
            <td><input type = "text" name="memberId" value="${board.memberId}" readonly="readonly"></td>
        </tr>
        <tr>
            <th>createdate</th>
            <td>${board.createdate}</td>
        </tr>
        <tr>
            <th>updatedate</th>
            <td>${board.updatedate}</td>
        </tr>
        <!-- 파일이 존재할때 -->
         <c:forEach var="f" items="${fileList}">
		 <tr>
             <th>파일</th>
             <td><td><a href="/upload/${f.saveFilename}">${f.originFilename}</a></td></td>
         </tr>
        </c:forEach>
        <!-- 파일이 존재하지 않을때 -->
         <tr>
			<th>파일</th>
          	<td><input type="file" name="MultipartFile" multiple></td>
          	<td><input type="hidden" name="boardfileNo" value="${f.boardfileNo}"></td>
        </tr>
    </table>
    <input type="hidden" name="boardNo" value="${board.boardNo}">
    <button type="submit">수정</button>
</form>
</body> 