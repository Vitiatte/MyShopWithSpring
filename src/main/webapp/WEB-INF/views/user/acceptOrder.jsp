<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accept Order</title>
</head>
<body>
<center>
<table border="1">
    <tr>
        <th>Order ID</th>
        <th>Address</th>
        <th>Email</th>
    </tr>
        <tr>
            <td>${order.id}</td>
            <td>${order.address}</td>
            <td>${order.email}</td>
            <form method="post" action="accept_order?orderId=${order.id}">
                <td> Code:<input name="codeFromUser" type="text"></td>
                <td><input type="submit" value="Confirm"></td>
            </form>
        </tr>
    <h1>${error}</h1>
</table>
</center>
</body>
</html>
