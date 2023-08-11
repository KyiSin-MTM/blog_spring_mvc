<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row">
    <div class="col-6 form-width mx-auto">
        <div class="card">
            <div class="card-header">
                <h4>Profile Edit</h4>
            </div>
            <div class="card-body">
                <c:url var="userUpdateAction" value="/users/update?${_csrf.parameterName}=${_csrf.token}"></c:url>                
                <form:form action="${userUpdateAction}" method="POST"
                    modelAttribute="userEditForm" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-12 mb-2">
                            <label>Profile upload: </label>
                            <form:input type="file" path="photo" />
                        </div>
                        <div class="col-12 mb-2">
                            <div class="form-group">
                                <label>Name: </label>
                                <form:input type="text" path="name" class="form-control"
                                    value="${user.name}" />
                                <form:errors path="name" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-12 mb-2">
                            <div class="form-group">
                                <label>Email: </label>
                                <form:input type="email" path="email" class="form-control"
                                    value="${user.email}" />
                                <form:errors path="email" cssClass="text-danger" />
                            </div>
                        </div>   
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />           
                        <div class="col-12 mt-2">
                            <button type="submit" class="btn btn-primary me-2">Update</button>                      
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>