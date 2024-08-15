<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Notice</title>
</head>
<body>
<h1>Update Notice</h1>
<form action="${pageContext.request.contextPath}/notices/${notice.noticeID}" method="post">
    <label for="title">Title:</label>
    <input type="text" id="title" name="noticeTitle" value="${notice.noticeTitle}" required/><br/>

    <label for="content">Content:</label>
    <textarea id="content" name="noticeContent" required>${notice.noticeContent}</textarea><br/>

    <button type="submit">Update</button>
</form>
<a href="${pageContext.request.contextPath}/notices">Back to List</a>
</body>
</html>
