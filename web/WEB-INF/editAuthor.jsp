<%@ page import="model.Author" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/1/2022
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Author</title>
</head>
<body>
<%

    Author author = (Author) request.getAttribute("author");

%>
Please update author's data:
<form action="/author/edit" method="post">
    <input type="hidden" name="authorId" value="<%=author.getId()%>"/>
    <input type="file" name="profilePic" value="<%=author.getProfilePic()%>"/>
    <input type="text" name="name" value="<%=author.getName()%>"/><br>
    <input type="text" name="surname" value="<%=author.getSurname()%>"/><br>
    <input type="email" name="email" value="<%=author.getEmail()%>"/><br>
    <input type="number" name="age" value="<%=author.getAge()%>"/>

    <input type="submit" value="Edit">
</form>
</body>
</html>
