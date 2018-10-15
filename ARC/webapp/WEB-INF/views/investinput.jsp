<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>투자 상품 입력</title>
</head>
<body>
    <form method="post" action="/investment/input">
        <h1>투자 상품 입력</h1>
        상품 이름 :
        <select name="goodsidx">
            <c:forEach items="${requestScope.allGoodsList}" var="allGoodsList">
                <option value="${allGoodsList.gdsCd}">${allGoodsList.gdsNm}</option>
                <%--<input type="hidden" name="goodsidx" value="${requestScope.gdsCd}">--%>
            </c:forEach>
        </select><br>
        투자 금액(원) : <input type="number" name="mymoney" min="1" required><br>
        투자 기간(개월) : <input type="number" name="investperiod" min="1" required><br>
        <input type="submit" value="내 투자 등록">
    </form>

</body>
</html>
