<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<center>
${error}
<form action="registration" method="post">
    <p>Login:<input value="${login}" name="login" type="text"></p>
    <p>Password:<input value="${password}" name="password" type="password"></p>
    <p>Repeat password:<input value="${password}" name="passwordRepeat" type="password"></p>
    <c:forEach items="${roles}" var="role">
        <p><input name="role" type="radio" value="${role}">${role}</p>
    </c:forEach>

    <button type="submit">SIGN UP</button>
</form>
</center>
</body>
</html>
