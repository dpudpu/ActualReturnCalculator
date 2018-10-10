<%--
  Created by IntelliJ IDEA.
  User: jang
  Date: 18. 10. 8
  Time: 오후 4:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>투자 상품 입력</title>
</head>
<body>
    <form method="post" action="/investment/input">
        <h1>투자 상품 입력</h1>
        상품 이름 : <input type="text" name="productname"><br>
        투자 금액(원) : <input type="number" name="mymoney" min="1"><br>
        투자 기간(개월) : <input type="number" name="investperiod" min="1"><br>
        <input type="submit" value="submit">
    </form>
</body>
</html>
