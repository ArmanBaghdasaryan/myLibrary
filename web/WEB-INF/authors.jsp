<%@ page import="java.util.List" %>
<%@ page import="model.Author" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/1/2022
  Time: 5:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors Page</title>
</head>
<body>
<%

    List<Author> authors = (List<Author>) request.getAttribute("author");


%>

<table border="1px">
    <tr>
        <th>image</th>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>age</th>
        <th>action</th>

    </tr>
    <%for (Author author : authors) {%>
    <tr>
        <td>
            <%
                if (author.getAuthorPic() == null || author.getAuthorPic().length() == 0) {%>
            <img src="/image/defaultPic.png" width="100"/>
            <%} else {%>
            <img src="/getImage?profilePic=<%=author.getAuthorPic()%> " width="100"/>
            <%}%>
        </td>
        <td><%=author.getId()%>
        </td>
        <td><%=author.getName()%>
        </td>
        <td><%=author.getSurname()%>
        </td>
        <td><%=author.getEmail()%>
        </td>
        <td><%=author.getAge()%>
        </td>
        <td>

            <a href="/author/remove?authorId=<%=author.getId()%> ">Remove</a> |
            <a href="/author/edit?authorId=<%=author.getId()%>">Edit</a>

        </td>


    </tr>


    <% }%>

</table>

</body>
</html>
