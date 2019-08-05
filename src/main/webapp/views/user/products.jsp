<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All products</title>
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
            <td><a href="add_product_to_basket?product_id=${product.id}">To Basket</a></td>
        </tr>
    </c:forEach>
</table>
<button><a href="/logout">Logout</a></button>
<button><a href="basket">To Basket</a></button>
</center>
</body>
</html>
