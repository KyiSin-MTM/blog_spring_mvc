<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container py-5">
    <div class="d-flex justify-content-between">
        <h3>Category List</h3>
        <c:url var="createAction" value="/categories/create" />
        <a class="inline-block btn btn-primary" href="${createAction}">Create
            Category</a>
    </div>
    <div class="table-responsive py-5">
        <table class="table table-bordered table-striped">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Created Date</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${pagedListHolder.pageList}" var="category">
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <td>${category.created_at}</td>
                    <td><c:url var="editAction"
                            value="/categories/edit?id=${category.id}" /> <c:url
                            var="deleteAction"
                            value="/categories/destroy?id=${category.id}" /> <a
                        class="btn btn-info" href="${editAction}">Edit</a>
                        <a class="btn btn-danger" href="${deleteAction}"
                        onclick="return confirm('Are you sure want to delete?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <jsp:useBean id="pagedListHolder" scope="request"
            type="org.springframework.beans.support.PagedListHolder" />
        <c:url value="/categories" var="pagedLink">
            <c:param name="p" value="~" />
        </c:url>
        <tg:paging pagedListHolder="${pagedListHolder}"
            pagedLink="${pagedLink}" />
    </div>
</div>