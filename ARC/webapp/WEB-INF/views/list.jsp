<%--
  Created by IntelliJ IDEA.
  User: jang
  Date: 18. 10. 8
  Time: 오후 1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>list</h1>
<%--<c:if test="${sessionScope.admin != 'true'}">--%>
    <%--<a href="/login">관리자 로그인</a>--%>
<%--</c:if>--%>
<%--<c:if test="${sessionScope.admin == 'true'}">--%>
    <%--<a href="/logout">관리자 로그아웃</a>--%>
<%--</c:if>--%>
<br><br>

<c:forEach items="${requestScope.arcList}" var="arc">
    ${arc.gdsNm}  ${arc.investPeriod} ${arc.prfRto}  ${arc.myPrice} <br>
</c:forEach>

이름 : 홍길동 <br>
내용 : 자바가...<br>
<a href="/delete?id=1">삭제</a><br><br>

이름 : 고길동 <br>
내용 : 하하...<br>
<a href="/delete?id=2">삭제</a><br><br>
</body>
</html>
