<%--
  Created by IntelliJ IDEA.
  User: Дарина
  Date: 03.09.2017
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Periodika</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

  <body style="height:1500px">

  <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
          <div class="navbar-header">
              <a class="navbar-brand" href="#">Periodika</a>
          </div>
              <ul class="nav navbar-nav">
                  <li class="active"><a href="#">HOME</a></li>
                  <li><a href="#">HEALTH</a></li>
                  <li><a href="#">TECHNOLOGY</a></li>
                  <li><a href="#">ENTERTAINMENT</a></li>
              </ul>
          <form class="navbar-form navbar-right">
              <div class="input-group">
                  <input type="text" class="form-control" placeholder="Search">
                  <div class="input-group-btn">
                      <button class="btn btn-default" type="submit">
                          <i class="glyphicon glyphicon-search"></i>
                      </button>
                  </div>
              </div>
          </form>
          <ul class="nav navbar-nav navbar-right">
              <li><a href="#" data-toggle="modal" data-target="#registerModal"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
              <li><a href="#" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-log-in" data-target="#myModal"></span> Login</a></li>
          </ul>
      </div>
  </nav>

  <!-- Modal for login-->
  <div class="modal fade" id="myModal" role="dialog">
      <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                  <h4><span class="glyphicon glyphicon-lock"></span>LOGIN</h4>
              </div>
              <div class="modal-body">
                  <form role="form" action="/login" method="POST">
                      <div class="form-group">
                          <label for="user_email"><span class="glyphicon glyphicon-user"></span> Email</label>
                          <input type="email" class="form-control" name="email" id="user_email" placeholder="Enter email">
                      </div>
                      <div class="form-group">
                          <label for="psw"><span class="glyphicon glyphicon-user"></span>Password</label>
                          <input type="text" class="form-control" id="psw"  name="password" placeholder="Enter password">
                      </div>
                      <button type="submit" class="btn btn-block">Login
                          <span class="glyphicon glyphicon-ok"></span>
                      </button>
                  </form>
              </div>
          </div>
      </div>
  </div>


  <!-- Modal for register-->
  <div class="modal fade" id="registerModal" role="dialog">
      <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                  <h4><span class="glyphicon glyphicon-lock"></span>Register</h4>
              </div>
              <div class="modal-body">
                  <form role="form" action="registration" method="POST">
                      <div class="form-group">
                          <label for="fName">First name</label>
                          <input type="text" class="form-control" name="firstName" id="fName" placeholder="Enter first name" required="required"/>
                      </div>
                      <div class="form-group">
                          <label for="lName">Last name</label>
                          <input type="text" class="form-control" name="lastName" id="lName" placeholder="Enter last name" required="required"/>
                      </div>
                      <div class="form-group">
                          <label for="mName">Middle name</label>
                          <input type="text" class="form-control" name="middleName" id="mName" placeholder="Enter middle name" required="required"/>
                      </div>
                      <div class="form-group">
                          <label for="phone">Phone number</label>
                          <input type="text" class="form-control" name="phone" id="phone" placeholder="Enter phone number" required="required"/>
                      </div>
                      <div class="form-group">
                          <label for="city">City</label>
                          <select class="form-control" name="city" id="city">
                              <c:forEach  var="city" items="${cityList}">
                                  <option> ${city.name}</option>
                              </c:forEach>
                          </select>
                      </div>
                      <div class="form-group">
                          <label for="district">District</label>
                          <select class="form-control" name="district" id="district">
                              <c:forEach  var="district" items="${districtList}">
                                  <option>${district.name}</option>
                              </c:forEach>
                          </select>
                      </div>
                      <div class="form-group">
                          <label for="street">Street</label>
                          <input type="text" class="form-control" name="street" id="street" placeholder="Enter street" required="required"/>
                      </div>
                      <div class="form-group">
                          <label for="hNumber">House number</label>
                          <input type="text" class="form-control" name="houseNumber" id="hNumber" placeholder="Enter house number" required="required"/>
                      </div>
                      <div class="form-group">
                          <label for="fNumber">Flat number</label>
                          <input type="text" class="form-control" name="flatNumber" id="fNumber" placeholder="Enter flat number" required="required"/>
                      </div>
                      <div class="form-group">
                          <label for="u_email">Email</label>
                          <input type="email" class="form-control" name="email" id="u_email" placeholder="Enter email">
                      </div>
                      <div class="form-group">
                          <label for="pswd">Password</label>
                          <input type="text" class="form-control" id="pswd"  name="password" placeholder="Enter password">
                      </div>
                      <button type="submit" class="btn btn-block">Sign up
                          <span class="glyphicon glyphicon-ok"></span>
                      </button>
                  </form>
              </div>
          </div>
      </div>
  </div>

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
                                  <%counter++;  %>
                              </li>
                              <%
                                  }
                              %>
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

  <c:if test="${sessionScope.isExist == false}">
      <p><c:out value="${loginError}"/></p>
  </c:if>


  <form action="/login" method="POST">
          <p>Email<input type="email" name="email" required="required"/></p>
          <p>Password<input type="password" name="password" required="required"/></p>
            <p> <input type="submit" value="Sign In"/></p>
      </form>
        <c:if test="${sessionScope.isExist == false}">
           <p><c:out value="${loginError}"/></p>
        </c:if>

        <p><a href="/viewPeriodicals">View periodicals</a></p>
  <p><a href="/addPeriodic">Add periodicals</a></p>
  </body>

</html>
