<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container mt-5 mb-4 p-3 d-flex justify-content-center">
    <div class="card p-5">
        <div
            class=" image d-flex flex-column justify-content-center align-items-center">
            <c:choose>
                <c:when test="${authUser.getUserProfile() != null}">
                    <img src="${authUser.userProfile.photoPath}" alt="profile"
                        height="100" width="100" />
                </c:when>
                <c:otherwise>
                    <button class="btn btn-secondary">
                        <img src="https://i.imgur.com/wvxPV9S.png"
                            height="100" width="100" />
                    </button>
                </c:otherwise>
            </c:choose>
            <span class="name mt-3">${authUser.name}</span> <span
                class="idd">${authUser.email}</span>
            <div class=" d-flex mt-2">
                <button class="btn border-dark bg-secondary">
                    <a href="<c:url value='/users/edit' />"
                        class="text-decoration-none text-white">Edit
                        Profile</a>
                </button>
            </div>
            <div class=" px-2 rounded mt-4 date ">
                <fmt:formatDate value="${authUser.created_at}"
                    pattern="yyyy-MM-dd" var="formattedDate" />
                <span class="join">Joined ${formattedDate}</span>
            </div>
        </div>
    </div>
</div>