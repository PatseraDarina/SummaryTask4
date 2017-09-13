<%--
  Created by IntelliJ IDEA.
  User: Дарина
  Date: 10.09.2017
  Time: 1:49
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <form action = "Login" method = "POST">
        <p>Login <input type = "text" name = "login" required="required"></p>
        <p>Password <input type = "password" name = "pswd" required="required"></p>
        <input type = "submit" valu e= "LogIn">
        <input type="reset" value="Reset">
    </form>
    <a href = "../../register.jsp">Register</a>
</body>
</html>
