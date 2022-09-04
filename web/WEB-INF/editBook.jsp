<%@ page import="model.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/1/2022
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>
<%
    Book book = (Book) request.getAttribute("book");
    List<Author> authors = (List<Author>) request.getAttribute("authors");

%>
Please update book's data:
<form action="/book/edit" method="post">
    <input type="hidden" name="bookId" value="<%=book.getId()%>">
    <input type="text" name="title" value="<%=book.getTitle()%>"/><br>
    <input type="text" name="description" value=" <%=book.getDescription()%>"/><br>
    <input type="text" name="price" value=" <%=book.getPrice()%>"/><br>
    <select name="authorId">

        <% for (Author author : authors) {

            if (author.equals(book.getAuthor())) {%>

        <option selected value="<%=author.getId()%>"><%=author.getName()%>
        </option>
        <% } else {%>
        <option value="<%=author.getId()%>"><%=author.getName()%>
                <%}}%>
    </select>
    <input type="submit" value="Edit">
</form>
</body>
</html>
