<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: blue">
            <div>
                <a href="https://www.xadmin.net" class="navbar-brand"> User Management Application </a>
            </div>
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
            </ul>
        </nav>
    </header>
    <br>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <form action="<c:choose>
                                <c:when test="${user != null}">update</c:when>
                                <c:otherwise>insert</c:otherwise>
                             </c:choose>" method="post">
                    <caption>
                        <h2>
                            <c:choose>
                                <c:when test="${user != null}">Edit User</c:when>
                                <c:otherwise>Add New User</c:otherwise>
                            </c:choose>
                        </h2>
                    </caption>

                    <c:if test="${user != null}">
                        <input type="hidden" name="id" value="${user.id}" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>User Name</label>
                        <input type="text" value="${user.name}" class="form-control" name="name" required="required" />
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User Email</label>
                        <input type="text" value="${user.email}" class="form-control" name="email" />
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User City</label>
                        <input type="text" value="${user.city}" class="form-control" name="city" />
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
