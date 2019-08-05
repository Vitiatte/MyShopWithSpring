<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding Product</title>
</head>
<body>
<center>
${error} <br>
<form action="add_product" method="post">
    Title:<input name="title" type="text"> <br>
    Description:<input name="description" type="text"> <br>
    Price:<input name="price" type="text"><br>
    <button type="submit">Add</button>
</form>
</center>
</body>
</html>
