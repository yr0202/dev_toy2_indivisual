<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Notice</title>
</head>
<body>
<h1>새로운 공지사항 글 작성 - 관리자만 작성 가능</h1>
<!-- 글 작성 시 확인 다이얼로그 추가 -->
<script>
    function confirmCreate() {
        return confirm("작성하시겠습니까?");
    }
</script>

<form action="${pageContext.request.contextPath}/notices/insertNotice" method="post" onsubmit="return confirmCreate()">
    <label for="title">Title:</label>
    <input type="text" id="title" name="noticeTitle" required/><br/>

    <label for="content">Content:</label>
    <textarea id="content" name="noticeContent" required></textarea><br/>

    <!-- 표시여부 체크박스 추가 -->
    <label for="displayFlag">
        <input type="checkbox" id="displayFlag" name="displayFlag" value="y"/> 표시
    </label><br/>

    <button type="submit">Create</button>
</form>

<a href="${pageContext.request.contextPath}/notices">Back to List</a>
</body>
</html>


<%--<%@ page language="java" contentType="text/html; charset=UTF-8"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Create Notice</title>--%>
<%--    <script>--%>
<%--        function confirmCreate() {--%>
<%--            return confirm("작성하시겠습니까?");--%>
<%--        }--%>
<%--    </script>--%>
<%--</head>--%>
<%--<body>--%>
<%--&lt;%&ndash; create 버튼 누르면 작성하시겠습니까 alert창 띄우기&ndash;%&gt;--%>
<%--&lt;%&ndash;우선순위 체크박스/ 만들기 -> 테이블 수정...&ndash;%&gt;--%>
<%--&lt;%&ndash;title 글자 수 확인하기&ndash;%&gt;--%>
<%--&lt;%&ndash;글 작성 중에 예외가 생기면 다시 create.jsp 창 보여주고 alert창 보여주기&ndash;%&gt;--%>
<%--&lt;%&ndash;작성자 적는 폼은 없애고 임의로 세션에 저장된 "userId"를 작성자에 자동으로 넣기~!&ndash;%&gt;--%>
<%--&lt;%&ndash;사용자가 스크립트 넣지 않게 막기&ndash;%&gt;--%>

<%--<h1>새로운 공지사항 글 작성 - 관리자만 작성 가능</h1>--%>
<%--<form action="${pageContext.request.contextPath}/notices/insertNotice" method="post">--%>
<%--    <label for="title">Title:</label>--%>
<%--    <input type="text" id="title" name="noticeTitle" required/><br/>--%>

<%--    <label for="content">Content:</label>--%>
<%--    <textarea id="content" name="noticeContent" required></textarea><br/>--%>

<%--    <button type="submit">Create</button>--%>
<%--</form>--%>
<%--<a href="${pageContext.request.contextPath}/notices">Back to List</a>--%>
<%--</body>--%>
<%--</html>--%>
