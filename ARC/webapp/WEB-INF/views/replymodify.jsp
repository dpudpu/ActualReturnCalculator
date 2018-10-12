<%--
  Created by IntelliJ IDEA.
  User: jang
  Date: 18. 10. 12
  Time: 오후 4:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
댓글 수정하기<br>
<form method="post" action="/reply/modify">
    ID(연동안됨)<br>
    <input type="hidden" name="reply_idx" value="${requestScope.reply_idx}" />
    <textarea name="reply_content" cols="40" rows="5"></textarea><br>
    <input type="submit" value="등록"><br><br>
</form>
</body>
</html>
