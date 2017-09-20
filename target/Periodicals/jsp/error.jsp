<%--
  Created by IntelliJ IDEA.
  User: Дарина
  Date: 10.09.2017
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">

    <title>Error page</title>
</head>
<body>
    <div class="errorMsg">
        <h1><c:out value="${errorMessage}"/></h1>
    </div>
</body>
</html>
