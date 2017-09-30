<%--
  Created by IntelliJ IDEA.
  User: Дарина
  Date: 21.09.2017
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "flt" tagdir = "/WEB-INF/tags"%>
<%@ include file="/WEB-INF/jspf/localization.jspf"%>

<html>
<head lang="${language}">
    <title>Category</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

<%@ include file="/WEB-INF/jspf/header.jspf"%>

<flt:filter isAll="${requestScope.isAll}"/>

<c:if test="${not empty sessionScope.errorMessage}">
    <div class="alert alert-warning text-center" style="margin-top: 50px" role="alert">${sessionScope.errorMessage}</div>
    <c:remove var="errorMessage" scope="session"/>
</c:if>

<div class="row text-center">
    <c:forEach var="periodical" items="${periodicalList}">
        <div class="col-sm-4">
            <div class="category-periodic">
            <div class="thumbnail">
                <img src="img/${periodical.photo}" alt="">
                <p><strong>${periodical.name}</strong></p>
                <p>${periodical.category}</p>
                <p>${periodical.price} грн.</p>
                <a href="/addSubscribes?periodicalId=${periodical.id}&periodicalName=${periodical.name}&periodicalPhoto=${periodical.photo}&periodicalCategory=${periodical.category}&periodicalPrice=${periodical.price}">
                    <input type="submit" class="btn" value="<fmt:message key="label.subscribes"/>" />
                </a>

            </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
