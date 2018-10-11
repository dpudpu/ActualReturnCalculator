<%--
  Created by IntelliJ IDEA.
  User: o
  Date: 2018-10-10
  Time: 오후 7:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="viewport" content ="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.css">
    <title>회원가입</title>
</head>
<body>
<div class="container">
    <div class="col-lg-4"></div>
    <div class="col-lg-4">
        <div class="jumbotron" style="padding-top: 20px;">
            <form method="post" action="/sighUp">
                <h3 style="text-align: center;">회원가입 화면</h3>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="아이디" name="userID" maxlength="20">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="이름" name="userName" maxlength="20">
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" placeholder="이메일" name="userEmail" maxlength="30">
                </div>
                <input type="submit" class="btn btn-primary form-control" value="회원가입">
            </form>
        </div>
    </div>
</div>

<script src="js/bootstrap.js"></script>
</body>
</html>
