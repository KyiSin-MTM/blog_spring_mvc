<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row">
	<div class="col-6 form-width mx-auto">
		<div class="card">
			<div class="card-header">
				<h4>Create</h4>
			</div>
			<div class="card-body">
				<c:url var="storeAction" value="/posts/store"></c:url>
				<form:form action="${storeAction}" method="POST"
					modelAttribute="postForm">
					<div class="row">
						<div class="col-12 mb-2">
							<div class="form-group">
								<label>Title: </label>
								<form:input type="text" path="title" class="form-control"
									placeholder="Enter Title" />
								<form:errors path="title" cssClass="text-danger" />
							</div>
						</div>
						<div class="col-12 mb-2">
							<div class="mb-3">
								<label for="Textarea" class="form-label">Content</label>
								<form:textarea path="description" class="form-control" id="Textarea" rows="3"
									placeholder="Enter Description"></form:textarea>
								<form:errors path="description" cssClass="text-danger" />
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