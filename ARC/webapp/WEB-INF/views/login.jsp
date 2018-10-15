<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="css/custom.css">

    <script language="javascript">

        function checkForm() {

            var userId = document.login.userId;
            // 아이디 입력 유무 체크
            if (userId.value == '') {
                window.alert("아이디를 입력하시오");
                userId.focus();
                return false; // 아이디 입력이 안되어 있다면 submint 이벤트를 중지
            }

            // 암호 입력 유무 체크
            if (document.login.userPassword.value == '') {
                alert('암호를 입력하세요.');
                document.login.userPassword.focus();
                return false;
            }
        }

        function goList(){
            location.href="/list";
        }
    </script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.css">
    <title>회원가입</title>
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

<c:if test="${msg==-1}">
    <script>alert('아이디 혹은 패스워드가 일치하지 않습니다.');</script>
</c:if>

<div class="container">
    <div class="col-lg-4"></div>
    <div class="col-lg-4">
        <div class="jumbotron" style="padding-top: 20px;">
            <form method="post" id="login" name="login"onsubmit="return checkForm()" action="/login" >
                <h3 style="text-align: center;">로그인 화면</h3>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="아이디" value="${userId}"name="userId" maxlength="20">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="비밀번호" value="${userPassword}" name="userPassword" maxlength="20">
                </div>
                <div>
                    <input type="submit" class="btn btn-primary form-control" value="로그인">
                </div>
                <div>
                    <input type="button" onclick="goList()" class="btn btn-default form-control" value="취소">
                </div>
            </form>

        </div>
    </div>
</div>

<script src="js/bootstrap.js"></script>
<script type="text/javascript" src="httpRequest.js"></script>

</body>
</html>

