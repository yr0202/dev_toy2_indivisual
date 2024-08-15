<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Notice List</title>
    <script>
        // 삭제 전체 선택/해제 기능
        function toggleCheckboxes(masterCheckbox) {
            const checkboxes = document.getElementsByName('ids');
            for (let checkbox of checkboxes) {
                checkbox.checked = masterCheckbox.checked;
            }
        }

        // 표시여부 전체 선택/해제 기능
        function toggleDisplayFlags(masterCheckbox) {
            const displayFlags = document.getElementsByName('displayFlags');
            for (let checkbox of displayFlags) {
                checkbox.checked = masterCheckbox.checked;
            }
        }

        // 삭제 확인 기능
        function confirmDelete() {
            return confirm("정말로 삭제하시겠습니까?");
        }
    </script>
</head>
<body>
<h1>공지사항 전체 목록</h1>

<!-- role가 'admin'인 경우 글작성 버튼을 표시 -->
<c:if test="${role.equals('admin')}">
    <a href="${pageContext.request.contextPath}/notices/new">글작성</a>
</c:if>

<!-- 검색 폼 -->
<form action="${pageContext.request.contextPath}/notices/search" method="get">
    <select name="searchType">
        <option value="all">제목+내용</option>
        <option value="title">제목만</option>
        <option value="content">내용만</option>
    </select>
    <input type="text" name="keyword" placeholder="검색어를 입력하세요">
    <button type="submit">검색</button>
</form>

<form action="${pageContext.request.contextPath}/notices/updateDisplayFlags" method="post">
    <table border="1">
        <thead>
        <tr>
            <!-- 관리자(role.equals('admin'))만 체크박스와 Actions 열을 볼 수 있음 -->
            <c:if test="${role.equals('admin')}">
                <th>
                    <input type="checkbox" onclick="toggleCheckboxes(this)"/> 전체 선택
                </th>
            </c:if>
            <th>글번호</th>
            <th>제목</th>
            <th>내용</th>
            <th>작성날짜</th>
            <c:if test="${role.equals('admin')}">
                <th>작성자</th>
            </c:if>
            <c:if test="${role.equals('admin')}">
                <th>
                    <input type="checkbox" onclick="toggleDisplayFlags(this)"/> 표시여부
                </th>
            </c:if>
            <c:if test="${role.equals('admin')}">
                <th>Actions</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="notice" items="${notices}">
            <tr>
                <!-- 관리자(role.equals('admin'))만 체크박스와 Actions 열을 볼 수 있음 -->
                <c:if test="${role.equals('admin')}">
                    <td><input type="checkbox" name="ids" value="${notice.noticeID}"/></td>
                </c:if>
                <td>${notice.noticeID}</td>
                <td><a href="${pageContext.request.contextPath}/notices/${notice.noticeID}">${notice.noticeTitle}</a></td>
                <td>${notice.noticeContent}</td>
                <td>${notice.regDate}</td>
                <c:if test="${role.equals('admin')}">
                    <td>${userId}</td>
                </c:if>

                <c:if test="${role.equals('admin')}">
                    <td>
                        <input type="checkbox" name="displayFlags" value="${notice.noticeID}"
                               <c:if test="${notice.displayFlag eq 'y'}">checked</c:if>/>
                    </td>
                </c:if>

                <c:if test="${role.equals('admin')}">
                <td>
                    <!-- Edit 링크 -->
                    <a href="${pageContext.request.contextPath}/notices/edit/${notice.noticeID}" style="display:inline;">
                        Edit
                    </a>

                </td>
                </c:if>

            </c:forEach>
        </tbody>
    </table>

    <!-- 관리자(role.equals('admin'))만 수정완료 및 삭제 버튼을 볼 수 있음 -->
    <c:if test="${role.equals('admin')}">
        <button type="submit" onclick="return confirm('변경사항을 저장하시겠습니까?');">수정완료</button>
        <button type="submit" formaction="${pageContext.request.contextPath}/notices/delete" onclick="return confirmDelete();">Delete Selected</button>
    </c:if>
</form>

</body>
</html>