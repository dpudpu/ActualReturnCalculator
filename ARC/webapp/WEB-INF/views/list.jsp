<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>

    <meta name="viewport" content="width=device-width", initial-scale="1">
    <link rel="stylesheet" href="css/custom.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/bootstrap.js"></script>
    <script type="text/javascript" src="httpRequest.js"></script>

   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

</head>
<body>

    <nav class="navbar navbar-default">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <b class="navbar-brand">게시판 웹사이트</b>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="list">게시판</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.user == null}">
                    <li><a href="/login">로그인</a></li>
                    <li><a href="/signUp">회원가입</a></li>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <li class="navbar-text"><b>${sessionScope.user}</b>님 어서오세요.</li>
                    <li><a href="/logout">로그아웃</a></li>
                </c:if>

            </ul>
        </div>
    </nav>



    <div class="container">
        <h2>투자리스트 목록</h2>
        <div class="dropdown pull-right" >
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                게시글 개수
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <li class="active"><a href="/list?pg=${pg}&posts=3">3</a></li>
                <li class><a href="/list?pg=${pg}&posts=5">5</a></li>
                <li class><a href="/list?pg=${pg}&posts=7">7</a></li>
                <li class><a href="/list?pg=${pg}&posts=10">10</a></li>
            </ul>
        </div>
        <div class="row">
            <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
                <thead>
                    <tr>
                        <th style="background-color: #eeeeee; text-align: center;">번호</th>
                        <th style="background-color: #eeeeee; text-align: center;">상품명</th>
                        <th style="background-color: #eeeeee; text-align: center;">기간</th>
                        <th style="background-color: #eeeeee; text-align: center;">투자금액</th>
                        <th style="background-color: #eeeeee; text-align: center;">수익률(세전)</th>
                        <th style="background-color: #eeeeee; text-align: center;">수익금(세전)</th>
                        <th style="background-color: #eeeeee; text-align: center;">세금</th>
                        <th style="background-color: #eeeeee; text-align: center;">수수료</th>
                        <th style="background-color: #eeeeee; text-align: center;">예상 총 수익금</th>
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
                <c:forEach begin="1" end="${requestScope.totalPage}" step="1" var="page">
                    <a href="/list?pg=${page}&posts=${posts}" class="btn btn-success"><b>${page}</b></a>
                </c:forEach>

            <c:if test="${sessionScope.user != null}">
              <a href="/investment/input" class="btn btn-primary pull-right">내 투자목록 등록</a>
            <c:if test="${sessionScope.user == null}">
            </c:if>
        </div>
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
            <c:if test="${sessionScope.user} eq ${replylist.reply_idx}}">
            <button value="삭제">삭제</button>
            </c:if>
        </form>
        <<c:if test="${sessionScope.user} eq ${replylist.reply_idx}">
        <a href="/reply/tagreply">댓글</a><br>
        </c:if>

    </c:forEach>
</div>

</body>
</html>
