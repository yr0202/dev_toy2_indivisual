<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Notice List</title>
    <style>
        /* 전체 페이지 스타일 */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
            color: #007BFF;
        }

        /* 테이블 스타일 */
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #007BFF;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        /* 링크 스타일 */
        a {
            color: #007BFF;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        /* 버튼 스타일 */
        button, input[type="submit"] {
            background-color: #007BFF;
            color: white;
            border: none;
            padding: 10px 20px;
            margin: 10px 5px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
        }

        button:hover, input[type="submit"]:hover {
            background-color: #0056b3;
        }

        /* 글작성 버튼 스타일 */
        .new-notice-btn {
            display: block;
            width: 120px;
            margin: 20px auto;
            text-align: center;
            padding: 10px 0;
            background-color: #28a745;
            color: white;
            border-radius: 5px;
            font-size: 14px;
        }

        .new-notice-btn:hover {
            background-color: #218838;
        }

        /* 검색창 스타일 */
        .search-container {
            background-color: #f8f8f8;
            padding: 20px;
            text-align: center;
            margin-bottom: 20px;
        }

        .search-form {
            display: inline-block;
            margin-right: 20px;
        }

        .search-option, .search-input {
            padding: 10px;
            font-size: 14px;
            margin-right: 10px;
        }

        .search-button {
            padding: 10px 20px;
            font-size: 14px;
            background-color: #007BFF;
            color: white;
            border: none;
            cursor: pointer;
        }

        .search-button:hover {
            background-color: #0056b3;
        }

        .paging-container {
            width: 100%;
            text-align: center;
            margin-top: 20px;
        }

        .paging a {
            padding: 5px 10px;
            margin: 0 5px;
            text-decoration: none;
            color: #007BFF;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .paging a.paging-active {
            background-color: #007BFF;
            color: white;
        }
    </style>
</head>
<body>

<h1>공지사항 전체 목록</h1>

<!-- 검색 폼 -->
<div class="search-container">
    <form action="<c:url value='/notices/list'/>" method="get" class="search-form">
        <select class="search-option" name="searchType">
            <option value="A" ${sc.searchType == 'A' ? "selected" : ""}>제목+내용</option>
            <option value="T" ${sc.searchType == 'T' ? "selected" : ""}>제목만</option>
            <option value="C" ${sc.searchType == 'C' ? "selected" : ""}>내용만</option>
        </select>
        <input type="text" name="keyword" class="search-input" placeholder="검색어 입력" value="${sc.keyword}">
        <input type="submit" class="search-button" value="검색">
    </form>
    <c:if test="${role.equals('admin')}">
        <a href="${pageContext.request.contextPath}/notices/new" class="new-notice-btn">글작성</a>
    </c:if>
</div>

<form action="${pageContext.request.contextPath}/notices/updateDisplayFlags" method="post">
    <table>
        <thead>
        <tr>
            <!-- 관리자(role.equals('admin'))만 체크박스와 Actions 열을 볼 수 있음 -->
            <c:if test="${role.equals('admin')}">
                <th><input type="checkbox" onclick="toggleCheckboxes(this)"/> 전체 선택</th>
            </c:if>
            <th>글번호</th>
            <th>제목</th>
            <th>내용</th>
            <th>작성날짜</th>
            <c:if test="${role.equals('admin')}">
                <th>작성자</th>
            </c:if>
            <c:if test="${role.equals('admin')}">
                <th>상단고정</th>
            </c:if>
            <c:if test="${role.equals('admin')}">
                <th>표시여부</th>
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
                <td><a href="${pageContext.request.contextPath}/notices/${notice.noticeID}">${notice.title}</a></td>
                <td>${notice.content}</td>
                <td>${notice.regDate}</td>
                <c:if test="${role.equals('admin')}">
                    <td>${notice.regId}</td>
                </c:if>
                <c:if test="${role.equals('admin')}">
                    <td><input type="checkbox" name="fixedChecks" value="${notice.noticeID}" <c:if test="${notice.fixedCheck eq 'y'}">checked</c:if>/></td>
                </c:if>
                <c:if test="${role.equals('admin')}">
                    <td><input type="checkbox" name="displayChecks" value="${notice.noticeID}" <c:if test="${notice.displayCheck eq 'y'}">checked</c:if>/></td>
                </c:if>
                <c:if test="${role.equals('admin')}">
                    <td>
                        <a href="${pageContext.request.contextPath}/notices/edit/${notice.noticeID}" style="display:inline;">Edit</a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- 관리자(role.equals('admin'))만 수정완료 및 삭제 버튼을 볼 수 있음 -->
    <c:if test="${role.equals('admin')}">
        <div style="text-align: center;">
            <button type="submit" onclick="return confirm('변경사항을 저장하시겠습니까?');">수정완료</button>
            <button type="submit" formaction="${pageContext.request.contextPath}/notices/delete" onclick="return confirmDelete();">Delete Selected</button>
        </div>
    </c:if>
</form>

<!-- 페이징 처리 -->
<div class="paging-container">
    <c:if test="${totalCnt == 0}">
        <p>게시물이 없습니다.</p>
    </c:if>
    <c:if test="${totalCnt > 0}">
        <c:if test="${ph.showPrev}">
            <a href="<c:url value='/notices/list${sc.getQueryString(ph.beginPage - 1)}'/>">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
            <a class="${i == sc.page ? 'paging-active' : ''}" href="<c:url value='/notices/list${sc.getQueryString(i)}'/>">${i}</a>
        </c:forEach>
        <c:if test="${ph.showNext}">
            <a href="<c:url value='/notices/list${sc.getQueryString(ph.endPage + 1)}'/>">&gt;</a>
        </c:if>
    </c:if>
</div>

</body>
</html>
