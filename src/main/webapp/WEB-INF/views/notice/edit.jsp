<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Notice</title>
</head>
<body>
<h1>공지사항 수정</h1>

<!-- 공지사항 수정 폼 -->
<form action="${pageContext.request.contextPath}/notices/edit/${notice.noticeID}" method="post">
    <table>
        <tr>
            <th>글번호:</th>
            <td>${notice.noticeID}</td>
        </tr>
        <tr>
            <th>제목:</th>
            <td><input type="text" name="noticeTitle" value="${notice.noticeTitle}" required></td>
        </tr>
        <tr>
            <th>내용:</th>
            <td><textarea name="noticeContent" rows="10" cols="50" required>${notice.noticeContent}</textarea></td>
        </tr>
        <tr>
            <th>표시여부:</th>
            <td>
                <input type="checkbox" name="displayFlag" value="y"
                       <c:if test="${notice.displayFlag eq 'y'}">checked</c:if>> 표시
            </td>
        </tr>
    </table>

    <button type="submit">수정완료</button>
    <a href="${pageContext.request.contextPath}/notices">취소</a>
</form>
</body>
</html>
