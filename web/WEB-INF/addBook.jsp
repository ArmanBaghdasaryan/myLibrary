<%@ page import="model.Author" %>
<%@ page import="java.util.List" %>
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/1/2022
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>
</head>
<body>
<%
    List<Author> authors = (List<Author>) request.getAttribute("authors");


%>
Please input book's:
<form action="/book/add" method="post">
    <input type="text" name="title" placeholder="please input title"/><br>
    <input type="text" name="description" placeholder="please input description"/><br>
    <input type="text" name="price" placeholder="please input price"/><br>
    <select name="authorId">

        <% for (Author author : authors) { %>
        <option value="<%=author.getId()%>"><%=author.getName()%>
        </option>
        <% } %><br>
    </select>
    <input type="submit" value="Add">
</form>
</body>
</html>
