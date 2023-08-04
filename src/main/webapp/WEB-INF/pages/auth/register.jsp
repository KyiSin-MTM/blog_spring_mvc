<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row">
	<div class="col-6 form-width mx-auto">
		<div class="card">
			<div class="card-header">
				<h4>Register</h4>
			</div>
			<div class="card-body">
				<c:url var="registerAction" value="/users/store"></c:url>
				<form:form action="${registerAction}" method="POST"
					modelAttribute="registerForm">
                    <c:if test="${errorMessage != null }">
                        <div class="alert text-danger text-center">
                            <strong>${errorMessage}</strong>
                        </div>
                    </c:if>
                    <div class="row">
						<div class="col-12 mb-2">
							<div class="form-group">
								<label>Name: </label>
								<form:input type="text" path="name" class="form-control"
									placeholder="Enter Name" />
								<form:errors path="name" cssClass="text-danger" />
							</div>
						</div>
						<div class="col-12 mb-2">
							<div class="form-group">
								<label>Email: </label>
								<form:input type="text" path="email" class="form-control"
									placeholder="Enter Email" />
								<form:errors path="email" cssClass="text-danger" />
							</div>
						</div>
						<div class="col-12 mb-2">
							<div class="form-group">
								<label>Password: </label>
								<form:input type="password" path="password" class="form-control"
									placeholder="Enter Password" />
								<form:errors path="password" cssClass="text-danger" />
							</div>
						</div>
                        <div class="col-12 mb-2">
                            <div class="form-group">
                                <label>Confirm Password: </label>
                                <form:input type="password" path="confirmPassword" class="form-control"
                                    placeholder="Enter Password" />
                                <form:errors path="confirmPassword" cssClass="text-danger" />
                            </div>
                        </div>
						<div class="col-12 mb-2">
							<div class="mb-3">
								<label for="role">Select Role:</label><br>
                                <form:select path="role.id"
                                  class="form-select">
                                  <%-- <form:options items="${roles}" itemValue="id" itemLabel="name" /> --%>
                                  <c:forEach var="role" items="${roles}"
                                    varStatus="loop">
                                    <form:option value="${role.id}" label="${role.name}" />
                                  </c:forEach>
                                </form:select>
                                <form:errors path="role.id" cssClass="text-danger" />
							</div>
						</div>
						<div class="col-12 mt-2">
							<button type="submit" class="btn btn-primary me-2">Submit Form</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>