<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Boot Application with JSP</title>
</head>

<body>

<h1>Spring Paging</h1>

<table>
    <tr>
        <td>이름</td>
        <td>내용</td>
    </tr>

    <c:forEach var="board" items="${boards}">
        <tr>
            <td>${board.writer}</td>
            <td>${board.content}</td>
        </tr>
    </c:forEach>


</table>


</body>

</html>

