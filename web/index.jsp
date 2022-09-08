<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/4/2022
  Time: 4:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library</title>
</head>

<%
    User user = (User) session.getAttribute("user");
%>

<body>
<div style="width: 1000px;margin: 80px auto">
    <div>
        <img src="/image/istockphoto-949118068-612x612.jpg" width="800" height="400">
    </div>
    <div>
        Hello from Library
        <br>

        <%

            if (user != null) { %>
        <a href="/author">Show All Authors</a>
        <a href="/author/add">Add Authors</a>
        <a href="/book">Show All Books</a>
        <a href="/book/add">Add Books</a>
        <a href="/logout">logout</a>

        <%} else {%>

        <a href="/users/add">Register</a>
        <a href="/login">Login</a>

        <%}%>


    </div>
</div>

</body>
</html>
