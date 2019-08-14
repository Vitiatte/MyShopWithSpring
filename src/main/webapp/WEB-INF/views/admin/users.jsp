<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
<center>
<h4>All Users:</h4>
<table border="1">
    <tr>
        <th>Login</th>
        <th>Role</th>
    </tr>
    <c:forEach items="${usersList}" var="user">
        <tr>
            <td>${user.login}</td>
            <td>${user.userRole}</td>
            <td><a href="update_user?id=${user.id}">Update</a></td>
            <td><a href="delete_user?id=${user.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<button><a href="registration">Register User</a></button>
<button><a href="/logout">Logout</a></button>
<form action="products" method="get">
    <button type="submit">Products</button>
</form>
</center>
</body>
</html>
