<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<center>
<c:set var="role" value="${userRole}"/>
<form action="update_user?id=${param["id"]}" method="post">
    Update user ${login}<br>
    Login:<input value="${login}" name="login" type="text"><br>
    Password:<input value="${password}" name="password" type="text"><br>
    <c:forEach items="${roles}" var="item">
        <c:choose>
            <c:when test="${item == role}">
                <p><input name="role" type="radio" value="${item}" checked>${item}</p>
            </c:when>
            <c:otherwise>
                <p><input name="role" type="radio" value="${item}">${item}</p>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <input value="Update" type="submit">
</form>
</center>
</body>
</html>
