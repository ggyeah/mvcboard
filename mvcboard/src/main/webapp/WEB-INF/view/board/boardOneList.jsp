<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardOneList.jsp</title>
</head>
<body>
    <table>
        <tr>
            <th>localName</th>
            <td>${board.localName}</td>
        </tr>
        <tr>
            <th>boardTitle</th>
            <td>${board.boardTitle}</td>
        </tr>
        <tr>
            <th>boardContent</th>
            <td>${board.boardTitle}</td>
        </tr>
        <tr>
            <th>memberId</th>
            <td>${board.memberId}</td>
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
             <th>파일 이름</th>
             <td><a href="/upload/${f.saveFilename}" download>${f.originFilename}d</a>
             <a href="/board/deleteBoardfile?boardfileNo=${f.boardfileNo}&boardNo=${board.boardNo}" onclick="return confirm('파일을 삭제하시겠습니까?');">삭제</a></td>
         </tr>
        </c:forEach>
        <!-- 파일이 존재하지 않을때 -->
        <c:if test="${empty fileList}">
            <tr>
 			<th>파일 이름</th>
             <td>파일이 존재하지 않습니다</td>
            </tr>
        </c:if>
    </table>
    <a href="/board/modifyBoard?boardNo=${board.boardNo}">수정</a>
    <a href="/board/removeBoard?boardNo=${board.boardNo}">삭제</a>
</body>  