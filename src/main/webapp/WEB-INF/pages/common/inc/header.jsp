<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
    prefix="security"%>
<nav class="navbar-dark bg-dark px-5">
    <div class="navbar navbar-expand navbar-dark">
        <a class="navbar-brand"
            href="${pageContext.request.contextPath}/posts">Blog</a>
        <ul class="navbar-nav me-auto">
            <security:authorize access="hasAnyRole('USER','ADMIN')"
                var="isLoggedin" />
            <c:choose>
                <c:when test="${isLoggedin}">
                    <c:set var="shouldContinue" value="true" />
                    <c:forEach items="${loginedUser.roles}" var="role"
                        varStatus="loop">
                        <c:if test="${shouldContinue}">
                            <c:choose>
                                <c:when test="${role.id == 1}">
                                    <li class="nav-item"><a
                                        class="nav-link">Posts</a></li>
                                    <div class="btn-group">
                                        <button type="button"
                                            class="btn btn-secondary dropdown-toggle"
                                            data-bs-toggle="dropdown"
                                            data-bs-display="static"
                                            aria-expanded="false">
                                            <c:out
                                                value="${sessionScope.loginedUser.name}" />
                                        </button>
                                        <ul
                                            class="dropdown-menu dropdown-menu-start">
                                            <c:url var="logoutAction"
                                                value="/logout" />
                                            <li><a
                                                href="${logoutAction}"
                                                class="dropdown-item">Logout</a></li>
                                        </ul>
                                    </div>
                                    <c:set var="shouldContinue"
                                        value="false" />
                                </c:when>
                                <c:otherwise>
                                    <li class="nav-item"><a
                                        class="nav-link">Users</a></li>
                                    <div class="btn-group">
                                        <button type="button"
                                            class="btn btn-secondary dropdown-toggle"
                                            data-bs-toggle="dropdown"
                                            data-bs-display="static"
                                            aria-expanded="false">
                                            <c:out
                                                value="${sessionScope.loginedUser.name}" />
                                        </button>
                                        <ul
                                            class="dropdown-menu dropdown-menu-start">
                                            <c:url var="logoutAction"
                                                value="/logout" />
                                            <li><a
                                                href="${logoutAction}"
                                                class="dropdown-item">Logout</a></li>
                                        </ul>
                                    </div>
                                    <c:set var="shouldContinue"
                                        value="false" />
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <li class="nav-item"><a
                        href="<c:url value='/login' />" class="nav-link">Login</a></li>
                    <li class="nav-item"><a
                        href="<c:url value='/register' />"
                        class="nav-link">Get Started</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>