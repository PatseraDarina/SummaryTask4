<%--
  Created by IntelliJ IDEA.
  User: Дарина
  Date: 22.09.2017
  Time: 1:41
  To change this template use File | Settings | File Templates.
--%>
<%@tag pageEncoding="UTF-8" %>
<%@tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="user" required="true" type="ua.nure.patsera.periodicals.bean.User" %>
<%@ attribute name="role" required="true" type="java.lang.String" %>

<c:if test="${empty user}">
    <li><a href="#" data-toggle="modal" data-target="#registerModal"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
    <li><a href="#" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-log-in" data-target="#myModal"></span> Login</a></li>
</c:if>
<c:if test="${user != null and role == 'admin'}">
    <li><a href="/viewPeriodicals"><span class="glyphicon glyphicon-user"></span> Admin page</a></li>
    <li><a href="/logout" ><span class="glyphicon glyphicon-log-in" data-target="#myModal"></span> Logout</a></li>
</c:if>
<c:if test="${user != null and role == 'reader'}">
    <li><a href="/jsp/personalCabinet.jsp"><span class="glyphicon glyphicon-user"></span> Cabinet</a></li>
    <li><a href="/logout" ><span class="glyphicon glyphicon-log-in" data-target="#myModal"></span> Logout</a></li>
</c:if>