<%--
  Created by IntelliJ IDEA.
  User: Дарина
  Date: 16.09.2017
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/css/add_style.css" />" rel="stylesheet">
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <title>Periodicals admin</title>

</head>
<body>

<div class="vladmaxi-top">
    <a href="/viewPeriodicals">View periodicals</a>
    <span class="right">
        <a href="showRegisterInfo">
                <strong>Main page</strong>
            </a>
        </span>
    <div class="clr"></div>
</div>

<div id="add-form">
    <h1>Add periodicals</h1>

    <fieldset>
        <form action="/addPeriodicals" method="POST">
            <select name="categoryPeriodical">
                <c:forEach  var="category" items="${categoryList}">
                    <option> ${category.name}</option>
                </c:forEach>
            </select>
            <input type="text" name="namePeriodical" required="required" placeholder="Name" onBlur="if(this.value=='')this.value='Логин'" onFocus="if(this.value=='Логин')this.value='' ">
            <input type="number" min="1" step="0.01" name="pricePeriodical" required="required" placeholder="Price" onBlur="if(this.value=='')this.value='Price'" onFocus="if(this.value=='Price')this.value=''" /></p>
            <p>Photo <input type="file" accept="image/jpeg" name="photoPeriodical"  required="required" /></p>
            <input type="submit" value="ADD">
            <footer class="clearfix">
                <input type="reset" value="RESET"/>
            </footer>
            <c:if test="${not empty errorMessage}">
                <c:out value="${errorMessage}"/>
            </c:if>
        </form>
    </fieldset>
</div>
</body>
</html>
