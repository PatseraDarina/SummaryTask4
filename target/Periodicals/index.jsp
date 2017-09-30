<%--
  Created by IntelliJ IDEA.
  User: Дарина
  Date: 03.09.2017
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/localization.jspf"%>
<%@ taglib prefix = "cst" uri = "/WEB-INF/custom.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Periodika</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

  <body >

  <%@ include file="/WEB-INF/jspf/header.jspf"%>

  <c:if test="${not empty sessionScope.errorMessage}">
      <div class="alert alert-warning text-center" style="margin-top: 50px" role="alert">${sessionScope.errorMessage}</div>
      <c:remove var="errorMessage" scope="session"/>
  </c:if>

  <div class="container">
      <div class="col-xs-12">
          <div class="carousel slide" id="myCarousel">
              <div class="slider_periodic">
              <div class="carousel-inner">
                  <div class="item active">
                      <ul class="thumbnails">
                          <% int counter = 0; %>
                          <c:forEach items="${periodicalList}" var="periodical">
                              <% if (counter != 4) { %>
                              <li class="col-sm-3">
                                  <div class="fff">
                                      <div class="thumbnail">
                                          <a href="#"><img src="img/${periodical.photo}" alt=""></a>
                                      </div>
                                      <div class="caption">
                                          <h4>${periodical.name}</h4>
                                          <p>${periodical.category}</p>
                                      </div>
                                  </div>
                                  <%counter++;%>
                              </li>
                              <%}%>
                          </c:forEach>
                      </ul>
                  </div><!-- /Slide1 -->


                  <div class="item">
                      <ul class="thumbnails">
                          <c:forEach begin="4" end="7" varStatus="loop">
                          <c:if test="${periodicalList[loop.index] != null}">
                          <li class="col-sm-3">
                              <div class="fff">
                                  <div class="thumbnail">
                                      <a href="#"><img src="img/${periodicalList[loop.index].photo}" alt=""></a>
                                  </div>
                                  <div class="caption">
                                      <h4>${periodicalList[loop.index].name}</h4>
                                      <p>${periodicalList[loop.index].category}</p>
                                  </div>
                              </div>
                              <%counter++; %>
                          </li>
                          </c:if>
                          </c:forEach>
                      </ul>
                  </div><!-- /Slide2 --><!-- /Slide3 -->
              </div>


              <nav>
                  <ul class="control-box pager">
                      <li><a data-slide="prev" href="#myCarousel" class=""><i class="glyphicon glyphicon-chevron-left"></i></a></li>
                      <li><a data-slide="next" href="#myCarousel" class=""><i class="glyphicon glyphicon-chevron-right"></i></a></li>
                  </ul>
              </nav>
              <!-- /.control-box -->

          </div><!-- /#myCarousel -->

      </div><!-- /.col-xs-12 -->
  </div><!-- /.container -->
  </div>

  </body>

</html>
