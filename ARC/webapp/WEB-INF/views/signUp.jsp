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

    <script language="javascript">

        function checkForm() {

            var userId = document.signUp.userId;
            // 아이디 입력 유무 체크
            if (userId.value == '') {
                window.alert("아이디를 입력하시오");
                userId.focus();
                return false; // 아이디 입력이 안되어 있다면 submint 이벤트를 중지
            } else if (!(userId.value.length >= 3 && userId.value.length <= 20)) {
                window.alert("아이디 3~20자 이내로 입력하시오.");
                userId.focus();
                return false;
            }

            // 암호 입력 유무 체크
            if (document.signUp.userPassword.value == '') {
                alert('암호를 입력하세요.');
                document.signUp.userPassword.focus();
                return false;
            }

            if (document.signUp.userPassword.value != document.signUp.userPasswordCheck.value) {
                document.getElementById('checkPwd').style.color = "red";
                document.getElementById('checkPwd').innerHTML = "일치하지 않습니다.";
                return false;
            } else {
                document.getElementById('checkPwd').style.color = "black";
                document.getElementById('checkPwd').innerHTML = "암호가 확인 되었습니다.";
            }

        }
    </script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.css">
    <title>회원가입</title>
</head>
<body>


<div class="container">
    <div class="col-lg-4"></div>
    <div class="col-lg-4">
        <div class="jumbotron" style="padding-top: 20px;">

            <form method="post" id="signUp" name="signUp"onsubmit="return checkForm()" action="/signUp" >
                <h3 style="text-align: center;">회원가입 화면</h3>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="아이디" name="userId" maxlength="20">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="비밀번호확인" name="userPasswordCheck" maxlength="20">
                    <div id="checkPwd">동일한 암호를 입력하세요.</div>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="이름" name="userName" maxlength="20">
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" placeholder="이메일" name="userEmail" maxlength="30">
                </div>
                <div>
                    <input type="submit" class="btn btn-primary form-control" value="회원가입">
                </div>
                <div>
                    <input type="reset" class="btn btn-toolbar form-control" value="취소">
                </div>
            </form>

        </div>
    </div>
</div>

<script src="js/bootstrap.js"></script>
<script type="text/javascript" src="httpRequest.js"></script>

</body>
</html>
