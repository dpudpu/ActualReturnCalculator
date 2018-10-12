<%--
  Created by IntelliJ IDEA.
  User: jang
  Date: 18. 10. 8
  Time: 오후 1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
<c:if test="${sessionScope.user == null}">
    <button onclick="location='/login'"> 로그인</button>
    <button onclick="location='/signUp'">회원가입</button>
</c:if>
<c:if test="${sessionScope.user != null}">
    <b>${sessionScope.user}</b>님 어서오세요.<button onclick="location='/logout'"> 로그아웃</button>
</c:if>
<div float:right>
    <button onclick="location='/list?pg=${pg}&posts=3'">3</button>
    <button onclick="location='/list?pg=${pg}&posts=5'">5</button>
    <button onclick="location='/list?pg=${pg}&posts=7'">7</button>
    <button onclick="location='/list?pg=${pg}&posts=10'">10</button>
</div>
<%--<c:if test="${sessionScope.admin != 'true'}">--%>
    <%--<a href="/login">관리자 로그인</a>--%>
<%--</c:if>--%>
<%--<c:if test="${sessionScope.admin == 'true'}">--%>
    <%--<a href="/logout">관리자 로그아웃</a>--%>
<%--</c:if>--%>
<br><br>

<table>
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
</table>
<br>
<div class="page" display:block;>
<c:forEach begin="1" end="${requestScope.totalPage}" step="1" var="page">
    <a href="/list?pg=${page}&posts=${posts}"><b>[${page}]</b></a>
</c:forEach>
</div>

<div class="reply_list">
    <form method="post" action="/reply">
        ID(연동안됨)<br>
        <textarea name="reply_content" cols="40" rows="5"></textarea><br>
        <input type="submit" value="등록"><br><br>
    </form>
    <c:forEach items="${requestScope.replylist}" var="replylist">
        ${replylist.member_id} 작성 시간 : ${replylist.getReply_time()}<br>
        ${replylist.content}<br><br>
        <a href="/reply/modify">수정</a>
        <form method="post" action="/reply/delete">
            <input type="hidden" name="reply_idx" value="${replylist.reply_idx}" />
            <button value="삭제">삭제</button>
        </form>
        <a href="/reply/tagreply">댓글</a><br>
    </c:forEach>
</div>

</body>
</html>
