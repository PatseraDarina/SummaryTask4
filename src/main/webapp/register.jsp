<%--
  Created by IntelliJ IDEA.
  User: Дарина
  Date: 10.09.2017
  Time: 1:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <form action="RegistrationServlet" method="POST">
        <p>First Name<input type="text" name="firstName"></p>
        <p>Last Name<input type="text" name="lastName"></p>
        <p>Middle Name<input type="text" name="middleName"></p>
        <p>Phone<input type="tel" name="phone"></p>
        <p>City<input type="text" name="city"></p>
        <p>District<input type="text" name="district"></p>
        <p>Street<input type="text" name="street"></p>
        <p>House number<input type="text" name="houseNumber"></p>
        <p>Flat number<input type="text" name="flatNumber"></p>
        <p>E-Mail<input type="email" name="email"></p>
        <p>Password<input type="password" name="password"></p>
        <p>Confirm password<input type="password" name="confirmPassword"></p>
        <input type="submit" value="Register">
    </form>
</body>
</html>
