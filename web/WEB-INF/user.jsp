<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/8/2022
  Time: 1:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Users</title>
</head>
<body>

<%

    List<User> users = (List<User>) request.getAttribute("users");


%>

<table border="1px">
    <tr>
        <th>image</th>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>password</th>
        <th>action</th>

    </tr>
    <%for (User user : users) {%>
    <tr>
        <td>
            <%if (user.getProfilePic() == null || user.getProfilePic().length() == 0) {%>
            <img src="/image/defaultPic.png" width="100"/>
            <%} else {%>
            <img src="/getImage?profilePic=<%=user.getProfilePic()%> " width="100">
            <%}%>
        </td>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getSurname()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getPassword()%>
        </td>
        <td>

            <a href="/user/remove?userId=<%=user.getId()%> ">Remove</a> |
            <a href="/user/edit?authorId=<%=user.getId()%>">Edit</a>

        </td>


    </tr>


    <% }%>

</table>

</body>
</html>
