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

<div class="container">
    <h3 class="text-center">List of Users</h3>
    <hr>
    <div class="text-left">
        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
    </div>
    <br>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>City</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td><c:out value="${user.city}" /></td>
                    <td>
                        <a href="<c:url value='/edit'/>?id=<c:out value='${user.id}' />" class="btn btn-primary btn-sm">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; 
                        <a href="<c:url value='/delete'/>?id=<c:out value='${user.id}' />" class="btn btn-danger btn-sm">Delete</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/view'/>?id=<c:out value='${user.id}' />" class="btn btn-info btn-sm">View</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
