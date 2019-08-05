<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Products</title>
</head>
<body>
<center>
<h3>Products List:</h3>
<table border="1">
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Price</th>
    </tr>
    <c:forEach items="${productsList}" var="product">
        <tr>
            <td>${product.title}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td><a href="update_product?id=${product.id}">Update</a></td>
            <td><a href="delete_product?id=${product.id}">Delete</a> </td>
        </tr>
    </c:forEach>
</table>
<button><a href="add_product">Add Product</a></button>
<button><a href="/logout">Logout</a></button>
<form action="users" method="get">
    <button type="submit">Users</button>
</form>
</center>
</body>
</html>
