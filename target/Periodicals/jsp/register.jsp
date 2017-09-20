<%--
  Created by IntelliJ IDEA.
  User: Дарина
  Date: 10.09.2017
  Time: 1:59
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <form action="registration" method="POST">
        <p>First Name<input type="text" name="firstName" required="required"></p>
        <p>Last Name<input type="text" name="lastName" required="required"></p>
        <p>Middle Name<input type="text" name="middleName" required="required"></p>
        <p>Phone<input type="tel" name="phone" required="required"></p>
        <p>City<select name="city">
            <c:forEach  var="city" items="${cityList}">
                <option> ${city.name}</option>
            </c:forEach>
        </select></p>
        <p>District<select name="district">
            <c:forEach  var="district" items="${districtList}">
                <option> ${district.name}</option>
            </c:forEach>
        </select></p>
        <p>Street<input type="text" name="street" required="required"></p>
        <p>House number<input type="text" name="houseNumber" required="required"></p>
        <p>Flat number<input type="number" name="flatNumber" required="required"></p>
        <p>E-Mail<input type="email" name="email" required="required"></p>
        <p>Password<input type="password" name="password" required="required"></p>
        <p>Confirm password<input type="password" name="confirmPassword" required="required"></p>
        <input type="submit" value="Register"/>
    </form>


</body>
</html>
