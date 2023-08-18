<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row">
	<div class="col-6 form-width mx-auto">
		<div class="card">
			<div class="card-header">
				<h4>Edit Category</h4>
			</div>
			<div class="card-body">
				<c:url var="updateAction" value="/categories/update?id=${categoryForm.getId()}"></c:url>
				<form:form action="${updateAction}" method="POST"
					modelAttribute="categoryForm">
					<div class="row">
						<div class="col-12 mb-2">
							<div class="form-group">
								<label>Name: </label>
								<form:input type="text" path="name" class="form-control"
									placeholder="Enter Category Name" />
								<form:errors path="name" cssClass="text-danger" />
							</div>
						</div>
						<div class="col-12 mt-2">
							<button type="submit" class="btn btn-primary me-2">Submit</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>