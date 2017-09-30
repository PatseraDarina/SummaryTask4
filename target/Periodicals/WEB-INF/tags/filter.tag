<%--
  Created by IntelliJ IDEA.
  User: Дарина
  Date: 24.09.2017
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@tag pageEncoding="UTF-8" %>
<%@tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="isAll" required="true" type="java.lang.Boolean" %>

<c:if test="${isAll}">
    <div class="filters" style="margin-top: 70px; margin-left: 20px">
        <label style="font-size: large;">Price</label>
        <a href="/sorting?type=price&sort=ASC">
            <input type="submit" class="btn" value="ASC" />
        </a>
        <a href="/sorting?type=price&sort=DESC">
            <input type="submit" class="btn" value="DESC" />
        </a>
        <label style="font-size: large; padding-left: 30px">Name</label>
        <a href="/sorting?type=name&sort=ASC">
            <input type="submit" class="btn" value="ASC" />
        </a>
        <a href="/sorting?type=name&sort=DESC">
            <input type="submit" class="btn" value="DESC" />
        </a>
        <form class="navbar-form navbar-right" id="form-search" action="/search" method="post" style="margin-right: 50%; margin-top: 5px">
            <div class="input-group">
                <input type="text" name="periodicalName" class="form-control" placeholder="Search">
                <div class="input-group-btn">
                    <button class="btn" type="submit" form="form-search">
                        <i class="glyphicon glyphicon-search"></i>
                    </button>
                </div>
            </div>
        </form>
    </div>
</c:if>

