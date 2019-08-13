<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<center>
${error}
<form action='<spring:url value="/login"/>' method="post">
    Login:<input name="username" type="text"> </br></br>
    Password:<input name="password" type="password"> </br></br>
    <button type="submit">SIGN UP</button>
</form>
</center>
</body>
</html>
