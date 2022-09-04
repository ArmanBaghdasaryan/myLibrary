<%@ page import="java.util.List" %>
<%@ page import="model.Book" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/1/2022
  Time: 5:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books Page</title>
</head>
<body>
<%

    List<Book> books = (List<Book>) request.getAttribute("book");


%>

<table border="1px">
    <tr>
        <th>id</th>
        <th>title</th>
        <th>description</th>
        <th>price</th>
        <th>author_id</th>
        <th>action</th>
    </tr>
    <%for (Book book : books) {%>
    <tr>
        <td><%=book.getId()%>
        </td>
        <td><%=book.getTitle()%>
        </td>
        <td><%=book.getDescription()%>
        </td>
        <td><%=book.getPrice()%>
        </td>
        <td>
            <% if (book.getAuthor() != null) {%>
            <%=book.getAuthor().getName()%>
            <% } else { %>
            <span style="color: darkgreen">There isn't  Author:</span>

            <%}%>
        </td>
        <td>
            <a href="/book/remove?bookId=<%=book.getId()%>">Remove</a>
            | <a href="/book/edit?bookId=<%=book.getId()%>">Edit</a>

        </td>


    </tr>


    <% }%>

</table>

</body>
</html>
