<%--
  Created by IntelliJ IDEA.
  User: Дарина
  Date: 03.09.2017
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Periodicals Home</title>
  </head>
  <body>
        <form action="/login" method="POST">
          <p>Email<input type="email" name="email" required="required"></p>
          <p>Password<input type="password" name="password" required="required"></p>
            <p> <input type="submit" value="Sign In"></p>
      </form>
        <c:if test="${sessionScope.isExist == false}">
           <p><c:out value="${loginError}"/></p>
        </c:if>
  <p> <a href="/showRegisterInfo">Sign up</a></p>
  </body>
</html>
