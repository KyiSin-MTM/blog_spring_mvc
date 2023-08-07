<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
  rel="stylesheet"
  integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
  crossorigin="anonymous">
<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
  crossorigin="anonymous"></script>
</head>
<body>
  <div class="row mt-5 pt-5">
	<div class="col-4 form-width mx-auto">
		<div class="card">
			<div class="card-header">
				<h4>Reset Password Form</h4>
			</div>
			<div class="card-body">
				<c:url var="resetPasswordAction" value="/update/password"></c:url>
				<form:form action="${resetPasswordAction}" method="POST"
					modelAttribute="resetPasswordForm">
                    <c:if test="${errorMessage != null }">
                        <div class="alert text-danger text-center">
                            <strong>${errorMessage}</strong>
                        </div>
                    </c:if>
                    <c:if test="${successMessage != null }">
                        <p class="alert text-success text-center">
                            <strong>${successMessage}</strong>
                        </p>
                    </c:if>
                    <div class="row">
						<div class="col-12 mb-2">
							<div class="form-group">
								<label>Email: </label>
								<form:input type="text" path="email" class="form-control" 
                                    readonly="true" value="${resetPassword.user.email}" />
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
						<div class="col-12 mt-2">
							<button type="submit" class="btn btn-primary me-2">Submit</button>
                            <span><a href="<c:url value="/login" />"> Go to Login? </a></span>
						</div>             
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
</body>
</html>