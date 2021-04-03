<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%--<link rel="stylesheet" href="resources/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <title>Spring Boot Application with JSP</title>
</head>

<body>

<h1>Spring Paging</h1>

<table class="table table-hover">
    <thead>
        <tr>
            <td>이름</td>
            <td>내용</td>
        </tr>
    </thead>

    <c:forEach var="board" items="${boards}">
        <tr>
            <td>${board.writer}</td>
            <td>${board.content}</td>
        </tr>
    </c:forEach>


</table>


<div class="text-center">
    <ul class="pagination">
        <c:if test="${pageMaker.prev}">
            <li><a href="listPage?page=${pageMaker.startPage - 1}"> &laquo;</a> </li>
        </c:if>

        <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
            <li
                    <c:out value="${pageMaker.cri.page == idx ? 'class=active' :'' }"/>>
                    <a href="listPage?page=${idx}"> ${idx} </a>
            </li>
        </c:forEach>

        <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
            <li> <a href="listPage?page=${pageMaker.endPage + 1}"> &raquo; </a> </li>
        </c:if>

    </ul>
</div>

</body>

</html>

