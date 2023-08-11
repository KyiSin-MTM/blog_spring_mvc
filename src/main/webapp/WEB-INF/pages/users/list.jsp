<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container py-5">
    <div class="d-flex justify-content-between">
        <h3>User List</h3>
    </div>
    <div class="d-flex">
        <div class="me-3">
            <c:url var="searchAction" value="/users/search" />
            <form action="${searchAction}" method="GET" class="d-flex">
                <input type="search" name="searchKey" class="form-control" value="${searchKey}"
                    placeholder="Search....." />
                <button type="submit" class="btn btn-success">Search</button>
            </form>
        </div>
        <c:url var="exportAction" value="/posts/excel/export" />
        <a href="${exportAction}" class="btn btn-secondary">Export</a>
    </div>
    <div class="table-responsive py-5">
        <table class="table table-bordered table-striped">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Role</th>
                <th>Created Date</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td><c:forEach items="${user.roles}" var="role">
                        <span class="badge bg-primary">${role.name}</span>
                    </c:forEach></td>
                    <td>${user.created_at}</td>
                    <td><c:url
                            var="deleteAction"
                            value="/users/destroy?id=${user.id}" />
                        <a class="btn btn-danger" href="${deleteAction}"
                        onclick="return confirm('Are you sure want to delete?')">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>