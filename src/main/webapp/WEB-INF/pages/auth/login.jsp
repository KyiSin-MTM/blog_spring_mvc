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
                <c:url var="loginAction" value="/loginCheck"></c:url>
                <form action="${loginAction}" method="POST">
                    <font color="red">
                        ${SPRING_SECURITY_LAST_EXCEPTION.message} </font>
                    <div class="row">
                        <div class="col-12 mb-2">
                            <div class="form-group">
                                <label>Email: </label> <input
                                    type="email" name="useremail"
                                    class="form-control"
                                    placeholder="Enter Email" />
                            </div>
                        </div>
                        <div class="col-12 mb-2">
                            <div class="form-group">
                                <label>Password: </label> <input
                                    type="password" name="password"
                                    class="form-control"
                                    placeholder="Enter Password" />
                            </div>
                        </div>
                        <div class="col-12 mt-2 d-flex flex-row">
                            <button type="submit"
                                class="btn btn-primary me-2">Submit</button>
                            <input type="hidden"
                                name="${_csrf.parameterName}"
                                value="${_csrf.token}" />
                            <div class="mt-2">
                                <input type="checkbox" name="remember-me"
                                    class="form-check-input"
                                    id="exampleCheck1"> <label
                                    class="form-check-label"
                                    for="exampleCheck1">Remember
                                    Me</label>
                            </div>
                        </div>
                        <div class="mt-2">
                            <a class="text-decoration-none"
                                href="<c:url value='/forgot_password' />">Forgot
                                Password?</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>