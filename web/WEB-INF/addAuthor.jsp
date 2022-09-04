<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/1/2022
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Author</title>
</head>
<body>
Please input author's data:
<form action="/author/add" method="post">
    <input type="text" name="name" placeholder="please input name"/><br>
    <input type="text" name="surname" placeholder="please input surname"/><br>
    <input type="email" name="email" placeholder="please input email">/><br>
    <input type="number" name="age" placeholder="please input age">

    <input type="submit" value="Add">
</form>
</body>
</html>
