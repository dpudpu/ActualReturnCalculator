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
