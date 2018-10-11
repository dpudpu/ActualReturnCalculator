<%--
  Created by IntelliJ IDEA.
  User: jang
  Date: 18. 10. 8
  Time: 오후 1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            width: 80%;
        }
        table, th, td {
            border: 1px solid #bcbcbc;
            margin: auto;
            text-align: center;
        }
         p ,h1, a
         {
             text-align: center;
         }
    </style>
</head>
<body>
<h1>투자 리스트</h1>
<%--<c:if test="${sessionScope.admin != 'true'}">--%>
    <%--<a href="/login">관리자 로그인</a>--%>
<%--</c:if>--%>
<%--<c:if test="${sessionScope.admin == 'true'}">--%>
    <%--<a href="/logout">관리자 로그아웃</a>--%>
<%--</c:if>--%>
<br><br>

<table  >
    <thead>
    <tr>
        <th valign="middle">번호</th>
        <th valign="middle">상품명</th>
        <th valign="middle">기간</th>
        <th valign="middle">투자금액</th>
        <th valign="middle">수익률(세전)</th>
        <th valign="middle">수익금(세전)</th>
        <th valign="middle">세금</th>
        <th valign="middle">수수료</th>
        <th valign="middle">예상 총 수익금</th>
    </tr>
    </thead>

    <c:forEach items="${requestScope.myGoodsList}" var="myGoodsList">
        <tr>
            <td valign="middle">${myGoodsList.rownum}</td>
            <td valign="middle">${myGoodsList.goodsName}</td>
            <td valign="middle">${myGoodsList.investPeriod}</td>
            <td valign="middle">${myGoodsList.myPrice}</td>
            <td valign="middle">${myGoodsList.prfRto}</td>
            <td valign="middle">${myGoodsList.profits}</td>
            <td valign="middle">8%</td>
            <td valign="middle">${myGoodsList.cms}</td>
            <td valign="middle"><fmt:formatNumber value="${ myGoodsList.profits-(myGoodsList.profits*(myGoodsList.cms+8)/100) }" pattern="0.00"/></td>
        </tr>
    </c:forEach>
</table
<br>
<div class="page" display:block;>
<c:forEach begin="1" end="${requestScope.page}" step="1" var="page">
    <a href="http://localhost:8080/list?pg=${page}"><b>[${page}]</b></a>
</c:forEach>
</div>
${requestScope.cnt}

<div class="reply_list">
    ID<br>
    <form method="post" action="/reply">
        <textarea name="reply_content" cols="40" rows="5"></textarea><br>
        <input type="submit" value="등록"><br><br>

        ID2<br>
        댓글 테스트<br>
        수정 삭제 댓글<br>
    </form>
</div>

</body>
</html>
