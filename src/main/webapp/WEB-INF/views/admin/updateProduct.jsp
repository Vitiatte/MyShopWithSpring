<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Product</title>
</head>
<body>
<center>
    <form action="update_product?id=${product.id}" method="post">
        Title:<input value="${product.title}" name="title" type="text">
        Description:<input value="${product.description}" name="description" type="text">
        Price:<input value="${product.price}" name="price" type="text">
        <input type="submit" value="Update">
    </form>
</center>
</body>
</html>
