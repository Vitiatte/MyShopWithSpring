<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creating Order</title>
</head>
<body>
<center>
<h1>${error}</h1>
    <form action="create_order" method="post">
        Email:<input name="email" type="text">
        Address:<input name="address" type="text">
        <input value="Buy" type="submit">
    </form>
</center>
</body>
</html>
