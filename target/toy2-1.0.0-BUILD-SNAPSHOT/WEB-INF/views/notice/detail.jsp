<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- 작성자 표시 추가 --%>

<!DOCTYPE html>
<html>
<head>
    <title>Notice Detail</title>
    <script>
        // 삭제 확인 기능
        function confirmDelete() {
            return confirm("정말로 삭제하시겠습니까?");
        }
    </script>
</head>
<body>
<h1>Notice Detail</h1>
<p>ID: ${notice.noticeID}</p>
<p>Title: ${notice.noticeTitle}</p>
<p>Content: ${notice.noticeContent}</p>
<p>작성자: ${notice.regId}</p>
<p>작성날짜: ${notice.regDate}</p>
<p>수정자: ${notice.modId}</p>
<p>수정날짜: ${notice.regDate}</p>

<a href="${pageContext.request.contextPath}/notices">전체목록</a>
<a href="${pageContext.request.contextPath}/notices/${notice.noticeID}/edit">편집하기</a>
<form action="${pageContext.request.contextPath}/notices/${notice.noticeID}/delete" method="post" style="display:inline;" onsubmit="return confirmDelete();">
    <button type="submit">Delete</button>
</form>


</body>
</html>
