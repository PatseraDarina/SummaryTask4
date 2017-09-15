<%--
  Created by IntelliJ IDEA.
  User: Дарина
  Date: 15.09.2017
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Personal cabinet</title>
</head>
<body>
    Hello, <c:out value="${sessionScope.registrationDTO.firstName}"/>
    <p><a href="/logout">LogOut</a></p>
</body>
</html>
