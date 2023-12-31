<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container py-5">
    <div class="d-flex justify-content-between">
        <h3>Post List</h3>
        <c:url var="createAction" value="/posts/create" />
        <a class="inline-block btn btn-primary" href="${createAction}">Create
            Post</a>
    </div>
    <div class="d-flex">
        <div class="me-3">
            <c:url var="searchAction" value="/posts/search" />
            <form action="${searchAction}" method="GET" class="d-flex">
                <input type="search" name="searchKey"
                    class="form-control" value="${searchKey}"
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
                <th>Title</th>
                <th>Description</th>
                <th>Created Date</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${posts}" var="post">
                <tr>
                    <td>${post.id}</td>
                    <td>${post.title}</td>
                    <td>${post.description}</td>
                    <td>${post.created_at}</td>
                    <td><c:if test="${post.user.id == authUser.id}">
                            <c:url var="editAction"
                                value="/posts/edit?id=${post.id}" />
                            <c:url var="deleteAction"
                                value="/posts/destroy?id=${post.id}" />
                            <a class="btn btn-info" href="${editAction}">Edit</a>
                            <a class="btn btn-danger"
                                href="${deleteAction}"
                                onclick="return confirm('Are you sure want to delete?')">Delete</a>
                        </c:if></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>