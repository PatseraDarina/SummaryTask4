<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Personal cabinet</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
    <jsp:useBean id="user" class="ua.nure.patsera.periodicals.bean.User" scope="session"/>
<%@ include file="/WEB-INF/jspf/header.jspf"%>


<div class="account-form">
<form action="/putMoney" id="money-form" method="post">
        <label>${sessionScope.user.account}</label>
    <div class="form-group">
        <input class="form-control " type="number" min="1"  step="0.001" name="account" required="required">
    </div>
        <input type="submit" class="btn-primary" aria-hidden="true" value="ADD" form="money-form"/>
</form>
</div>

<div class="row text-center">
    <c:forEach var="periodic" items="${sessionScope.subscriptionList}">
        <div class="col-sm-4">
            <div class="category-periodic">
                <div class="thumbnail">
                    <img src="/img/${periodic.photo}" alt="">
                    <p><strong>${periodic.name}</strong></p>
                    <p>${periodic.category}</p>
                    <p>${periodic.price} грн.</p>
                    <a href="/deleteSubscribe?periodicalId=${periodic.id}&periodicalPrice=${periodic.price}">
                        <input type="submit" class="btn" value="Unsubscribes" />
                    </a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>