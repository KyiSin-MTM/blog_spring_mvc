<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
    prefix="security"%>
<nav class="navbar-dark bg-dark px-5">
    <div class="navbar navbar-expand navbar-dark">
    <c:choose>
        <c:when test="${pageContext.request.userPrincipal.name != null}">
            <a class="navbar-brand"
            href="${pageContext.request.contextPath}/home">Blog</a>
        </c:when>
        <c:otherwise>
            <a class="navbar-brand">Blog</a>
        </c:otherwise>
            </c:choose>
        <ul class="navbar-nav me-auto">
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name != null}">                          
                                    <div class="btn-group">
                                        <button type="button"
                                            class="btn btn-secondary dropdown-toggle"
                                            data-bs-toggle="dropdown"
                                            data-bs-display="static"
                                            aria-expanded="false">
                                        </button>
                                        <ul
                                            class="dropdown-menu dropdown-menu-start">
                                            <li><a
                                                href="<c:url value='/profile' />"
                                                class="dropdown-item">
                                                    Profile </a></li>
                                            <c:url var="logoutAction"
                                                value="/logout" />
                                            <li>
                                                <form id="logout"
                                                    action="${logoutAction}"
                                                    method="post">
                                                    <input type="hidden"
                                                        name="${_csrf.parameterName}"
                                                        value="${_csrf.token}" />
                                                </form> <a
                                                class="dropdown-item"
                                                href="javascript:document.getElementById('logout').submit()">Logout</a>
                                            </li>
                                        </ul>
                                    </div>
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