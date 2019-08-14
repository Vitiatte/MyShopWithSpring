<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Basket</title>
</head>
<body>
<center>
<h3>Your Basket:</h3>
<table border="1">
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Price</th>
    </tr>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.title}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
        </tr>
    </c:forEach>
</table>
</form>
<h3>${error}</h3>
<button><a href="create_order">BUY</a></button>
<button><a href="products">Back</a></button>
</center>
</body>
</html>
