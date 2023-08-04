<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row">
	<div class="col-6 form-width mx-auto">
		<div class="card">
			<div class="card-header">
				<h4>Login</h4>
			</div>
			<div class="card-body">
				<c:url var="loginAction" value="check/login"></c:url>
				<form:form action="${loginAction}" method="POST"
					modelAttribute="loginForm">
                    <c:if test="${errorMessage != null }">
                        <div class="alert text-danger text-center">
                            <strong>${errorMessage}</strong>
                        </div>
                    </c:if>
                    <div class="row">
						<div class="col-12 mb-2">
							<div class="form-group">
								<label>Email: </label>
								<form:input type="email" path="email" class="form-control"
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
                        <div class="col-12 mt-2">
							<button type="submit" class="btn btn-primary me-2">Submit Form</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>