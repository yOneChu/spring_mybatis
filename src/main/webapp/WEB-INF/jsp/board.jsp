<%@ page import="com.kyhslam.domain.Criteria" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <title>Spring Boot Application with JSP</title>
</head>--%>

<%@include file="header.jsp"%>

<%

    //Criteria cri = (Criteria) request.getParameter("cri");


%>


<table class="table table-hover">
    <thead>
    <tr>
        <td>OID</td>
        <td>이름</td>
        <td>내용</td>
    </tr>
    </thead>

    <tbody id="content_body">
        <c:forEach var="board" items="${boards}">
            <tr id="board_content">
                <td>${board.board_id}</td>
                <td>${board.writer}</td>
                <td>${board.content}</td>
            </tr>
        </c:forEach>
    </tbody>

</table>


<%--<div class="text-center">
    <ul class="pagination" id="pagination">--%>
        <%--<c:if test="${pageMaker.prev}">
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
        </c:if>--%>


        <%--<c:if test="${pageMaker.prev}">
             <li><a href="/board/listPage${pageMaker.makeQuery(pageMaker.startPage - 1)}"> &laquo;</a> </li>
         </c:if>

         <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
             <li
                     <c:out value="${pageMaker.cri.page == idx ? 'class=active' :'' }"/>>
                 <a href="/board/listPage${pageMaker.makeQuery(idx)}"> ${idx} </a>
             </li>
         </c:forEach>

         <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
             <li> <a href="/board/listPage${pageMaker.makeQuery(pageMaker.endPage + 1)}"> &raquo; </a> </li>
         </c:if>--%>



        <%--개선--%>
        <div class="text-center">
            <ul class="pagination" id="pagination">

                <c:if test="${pageMaker.prev}">
                    <li id="pageView"><a href="${pageMaker.startPage - 1}">&laquo;</a></li>
                </c:if>

                <c:forEach begin="${pageMaker.startPage }"
                           end="${pageMaker.endPage }" var="idx">
                    <li id="pageView"
                            <c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
                            <%--<a href="${idx}">${idx}</a>--%>
                            <a class="page-link" id="page-link" href="#" onClick="javascript:pageGo(${idx});">${idx}</a>

                    </li>
                </c:forEach>

                <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                    <li id="pageView"><a
                            href="${pageMaker.endPage +1}">&raquo;</a></li>
                </c:if>

            </ul>
        </div>

</div>

<form id="jobForm">
    <input type="hidden" name="page" id="page" value="${pageMaker.cri.page}">
    <input type="hidden" name="perPageNum" id="perPageNum" value="${pageMaker.cri.perPageNum}">
</form>

<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>--%>
<script>
    $(document).ready(function() {

    }); // end jquery


    function pageGo(nextPage) {
        console.log('nextPage :: ', nextPage);
        /*
        event.preventDefault(); // 실제화면의 이동을 막는다.
        var targetPage = $(this).attr('href');
        var jobForm = $('#jobForm');

        jobForm.find("[name='page']").val(targetPage);
        jobForm.attr("action", "/board/listPage").attr("method", "get");
        jobForm.submit();
        */


        //event.preventDefault(); // 실제화면의 이동을 막는다.
        var goPage = nextPage; // $('#page-link').val;
        console.log('goPage :: ', goPage);

        var perPageNum = $('#perPageNum').val();
        console.log('perPageNum :: ', perPageNum);

        $.ajax({
            type: 'Get',
            dataType: "json",

            url: "/api/pageGo",
            data: {
                "goPage" : goPage,
                "perPageNum" : perPageNum
            },
            success: function (data) {
                console.log('success :: ', data);

                var boardList = data.boardList;


                // 게시물 삭제
                $('tr').remove('#board_content');

                let str = '';
                for(var i=0; i < boardList.length; i++) {
                    //console.log(boardList[i].board_id);
                    //console.log(boardList[i].content);
                    //console.log(boardList[i].writer);

                    str += '<tr id="board_content">'
                    str += '<td>' + boardList[i].board_id + '</td>';
                    str += '<td>' + boardList[i].content + '</td>';
                    str += '<td>' + boardList[i].writer + '</td>';
                    str += '</td>'
                }
                $('#content_body').append(str);


                // 페이징 번호
                let prev = data.prev;
                let next = data.next;
                let startPage = data.startPage;
                let endPage = data.endPage;

                console.log('prev :: ' + prev);
                console.log('next :: ' + next);
                console.log('startPage :: ' + startPage);
                console.log('endPage :: ' + endPage);


                // 페이징 번호 삭제
                $('li').remove('#pageView');
                let strPage = '';
                if(prev == true) {
                    strPage += '<li id="pageView">';
                    strPage += '<a class="page-link" id="page-link" href="#" onClick="javascript:pageGo(' + (startPage - 1) + ');">' + '&laquo;' + '</a>';
                    strPage += '</li>';
                }

                for(var i=startPage; i <= endPage; i++) {
                    if(goPage == (i)){
                        strPage += '<li id="pageView" class="active" >';
                    }else{
                        strPage += '<li id="pageView">';
                    }

                    strPage += '<a class="page-link" id="page-link" href="#" onClick="javascript:pageGo(' + i + ');">' + i + '</a>';
                    strPage += '</li>';
                }

                if(next == true && endPage > 0) {
                    strPage += '<li id="pageView">';
                    strPage += '<a class="page-link" id="page-link" href="#" onClick="javascript:pageGo(' + (endPage + 1) + ');">' + '&raquo;' + '</a>';
                    strPage += '</li>';
                }

                console.log(strPage);

                $('.pagination').append(strPage);
            }
        })
    };

</script>


