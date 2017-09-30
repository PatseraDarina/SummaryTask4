<%--
  Created by IntelliJ IDEA.
  User: Дарина
  Date: 16.09.2017
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link href="<c:url value="/css/view_style.css" />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>View periodicals</title>
</head>
<body>

<div class="vladmaxi-top">
    <a href="/viewPeriodicals">View periodicals</a>
    <span class="right">
        <a href="mainPage">
                <strong>Main page</strong>
        </a>
        </span>
    <div class="clr"></div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3>All users</h3>

            <div class="table-responsive">
                <table id="mytable" class="table table-bordred table-striped">
                    <thead>
                    <%--  <th><input type="checkbox" id="checkall" /></th>--%>
                        <th style="visibility: hidden">Id</th>
                        <th>Last name</th>
                        <th>First name</th>
                        <th>Middle name</th>
                        <th>Phone</th>
                        <th>Flat number</th>
                        <th>House number</th>
                        <th>Street</th>
                        <th>District</th>
                        <th>Block</th>
                    </thead>
                    <tbody>

                    <c:forEach  var="user" items="${usersList}">
                        <tr>
                                <%-- <td><input type="checkbox" class="checkthis" /></td>--%>
                            <td style="visibility: hidden"><c:out value="${user.id}"/></td>
                            <td><c:out value="${user.lastName}"/></td>
                            <td><c:out value="${user.firstName}"/></td>
                            <td><c:out value="${user.middleName}"/></td>
                            <td><c:out value="${user.phone}"/></td>
                            <td><c:out value="${user.flatNumber}"/></td>
                            <td><c:out value="${user.houseNumber}"/></td>
                            <td><c:out value="${user.street}"/></td>
                            <td><c:out value="${user.district}"/></td>
                            <c:if test="${not user.blocked}">
                               <td><a href="/blockUser?blocked=true&userId=${user.id}">
                                    <input type="submit"  class="btn" value="Block" />
                                </a></td>
                            </c:if>
                            <c:if test="${user.blocked}">
                                <td><a href="/blockUser?blocked=false&userId=${user.id}">
                                    <input type="submit"  class="btn" value="Unblock" />
                                </a></td>
                            </c:if>


                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
        </div>
    </div>
</div>

<c:if test="${not empty sessionScope.errorMessage}">
    <div class="alert alert-warning text-center" role="alert">${sessionScope.errorMessage}</div>
    <p> <c:out value="${errorMessage}"/></p>
</c:if>
<c:remove var="errorMessage" scope="session"/>


<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                <h4 class="modal-title custom_align" id="Heading">EDIT PERIODICAL</h4>
            </div>
            <div class="modal-body">
                <form action="/updatePeriodicals" id="edit-form" method="post">
                    <div class="form-group">
                        <input class="form-control " type="hidden" name="periodicalId" id="idP" required="required">
                    </div>
                    <div class="form-group">
                        <select class="form-control" id="categoryP" name="periodicalCategory">
                            <c:forEach  var="category" items="${categoryList}">
                                <option> ${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <input class="form-control " type="text" name="periodicalName" id="nameP" required="required" >
                    </div>
                    <div class="form-group">
                        <input class="form-control " type="number" min="1"  step="0.001" name="periodicalPrice" id="priceP" required="required" placeholder="Price">
                    </div>
                    <div class="form-group">
                        <p>Photo <input type="file" accept="image/jpeg" name="periodicalPhoto" id="photoP"  required="required" />
                    </div>
                    <div class="modal-footer ">
                        <input type="submit" class="btn btn-success glyphicon glyphicon-upload"  aria-hidden="true" value="Update" form="edit-form" style="width: 100%;"/>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                <h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
            </div>
            <div class="modal-body">
                <form action="/deletePeriodicals" method="post" id="delete-form">
                    <div class="form-group">
                        <input class="form-control " type="hidden" name="periodicalId" id="idPDel" required="required">
                    </div>
                    <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>

                    <div class="modal-footer ">
                        <button type="submit" class="btn btn-success" form="delete-form"><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="add" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                <h4 class="modal-title custom_align" id="Heading">ADD PERIODICAL</h4>
            </div>
            <div class="modal-body">
                <form action="/addPeriodicals" id="add-form" method="post">
                    <div class="form-group">
                        <input class="form-control " type="hidden" name="periodicalId" required="required">
                    </div>
                    <div class="form-group">
                        <select class="form-control" name="periodicalCategory">
                            <c:forEach  var="category" items="${categoryList}">
                                <option> ${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <input class="form-control " type="text" name="periodicalName" required="required" placeholder="Name">
                    </div>
                    <div class="form-group">
                        <input class="form-control " type="number" min="1"  step="0.001" name="periodicalPrice" required="required" placeholder="Price">
                    </div>
                    <div class="form-group">
                        <p>Photo <input type="file" accept="image/jpeg" name="periodicalPhoto"  required="required" />
                    </div>
                    <div class="modal-footer ">
                        <input type="submit" class="btn-primary" aria-hidden="true" value="ADD" form="add-form" style="width: 100%;"/>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<script>
    var table = document.getElementById('mytable');
    for(var i = 1; i < table.rows.length; i++) {
        table.rows[i].onclick  = function() {
            document.getElementById("idPDel").value = this.cells[0].innerHTML;
            document.getElementById("idP").value = this.cells[0].innerHTML;
            document.getElementById("categoryP").value = this.cells[1].innerHTML;
            document.getElementById("nameP").value = this.cells[2].innerHTML;
            document.getElementById("priceP").value = Number(this.cells[3].innerHTML);
            document.getElementById("photoP").value =  this.cells[4].innerHTML;
        }
    }
</script>

</body>
</html>