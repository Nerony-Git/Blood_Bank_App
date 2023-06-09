<%--
  Created with IntelliJ IDEA.
  Author: George Amuzu
  User: gnamu
  Date: 20/06/2023
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Blood Bank - Request Blood</title>
    <jsp:include page="../../assets/head/head.jsp"></jsp:include>
    <link rel="stylesheet" href="assets/css/main.css">

</head>
<body style="background-color: cornsilk; min-height: 100vh; display: flex; flex-direction: column" class="blood">

<!-- Confirm user is login -->
<c:if test = "${empty user}">
    <c:redirect url="/user_login"></c:redirect>
</c:if>
<!-- Confirm user is login -->

<!-- ===== Header ===== -->
<jsp:include page="../../assets/navbar/user_navbar.jsp"></jsp:include>
<!-- ===== End Header ===== -->

<!-- ===== Main Body ===== -->
<main id="main">
    <div class="container mt-5">
        <div class="row">
            <div class="breadcrumbs">
                <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><i class="fa-solid fa-gauge"></i><a href="<%=request.getContextPath()%>/user_dashboard">  Home </a></li>
                        <li class="breadcrumb-item active" aria-current="page">  Request Blood </li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>

    <div class="container p-5">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="card bg_border">
                    <div class="card-header text-center">
                        <img class="bg_img" src="assets/img/donor/request_blood.png" alt="Request blood title">
                        <%--<p class="fs-4 text-center text-white mt-2">
                            <i class="fa-solid fa-user"></i>   Request Blood
                        </p>--%>
                    </div>

                    <div class="card-body">
                        <!-- Success Message -->
                        <c:if test="${not empty successMsg}">
                            <p class="text-center text-success fs-5">${successMsg}</p>
                            <c:remove var="successMsg" scope="session" />
                        </c:if>
                        <!-- End Success Message -->

                        <!-- Error Message -->
                        <c:if test="${not empty errorMsg}">
                            <p class="text-center text-danger fs-5">${errorMsg}</p>
                            <c:remove var="errorMsg" scope="session" />
                        </c:if>
                        <!-- End Error Message -->

                        <!-- Form -->
                        <form action="<%=request.getContextPath()%>/new_request" method="post">
                            <div class="row">
                                <div class="col-md-6">
                                    <label class="form-label"> Request ID</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="requestID"><i class="fa-solid fa-tags"></i></span>
                                        <input type="text" name="requestID" class="form-control" aria-label="Request ID" aria-describedby="requestID" disabled placeholder="Request ID will be automatically assigned." />
                                    </div>

                                    <label class="form-label">Blood Group</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="bloodGroup"><i class="fa-solid fa-staff-snake"></i> </span>
                                        <select name="bloodGroup" class="form-select" aria-label="Blood Group" aria-describedby="bloodGroup" required>
                                            <option selected="selected"> --- Select Blood Group --- </option>
                                            <c:forEach var="bloodGroup" items="${bloodGroups}">
                                                <option value="<c:out value="${bloodGroup.bloodGroupName}"/>"><c:out value="${bloodGroup.bloodGroupName}"/></option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <label class="form-label">Request Date</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="requestDate"><i class="fa-solid fa-calendar"></i> </span>
                                        <input type="date" name="requestDate" class="form-control" aria-label="Request Date" aria-describedby="requestDate" required>
                                    </div>

                                    <label class="form-label">Comments</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="comments"><i class="fa-solid fa-comments"></i> </span>
                                        <textarea name="comment" class="form-control" rows="4" aria-label="Comments" aria-describedby="comments" placeholder="Type your comments here ..."></textarea>
                                    </div>

                                </div>

                                <div class="col-md-6">
                                    <label class="form-label">Donor ID</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="uID"><i class="fa-solid fa-user-tag"></i> </span>
                                        <input type="text" name="uID" class="form-control" aria-label="User ID" aria-describedby="uID" readonly value="${user.donorID}">
                                    </div>
                                    <input type="hidden" name="donorID" value="${user.donorID}">

                                    <label class="form-label">Donor Name</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="firstName"><i class="fa-solid fa-user"></i> </span>
                                        <input type="text" name="firstName" class="form-control" aria-label="First Name" aria-describedby="firstName" disabled value="${user.firstName} ${user.otherName} ${user.lastName}">
                                    </div>

                                    <label class="form-label">Gender</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="gender"><i class="fa-solid fa-venus-mars"></i> </span>
                                        <input type="text" name="gender" class="form-control" aria-label="Gender" aria-describedby="gender" disabled value="${user.gender}">
                                    </div>

                                    <label class="form-label">Contact</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="contact"><i class="fa-solid fa-phone-volume"></i> </span>
                                        <input type="text" name="contact" class="form-control" aria-label="Contact" aria-describedby="contact" disabled value="${user.contact}">
                                    </div>

                                    <label class="form-label">Postal Address</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="postalAddress"><i class="fa-solid fa-street-view"></i> </span>
                                        <input type="text" name="postalAddress" class="form-control" aria-label="Postal Address" aria-describedby="postalAddress" disabled value="${user.postalAddress}">
                                    </div>
                                </div>
                            </div>

                            <br/>
                            <br/>
                            <div class="input-group justify" style="justify-content: center;">
                                <button type="submit" class="bg_btn">&nbsp; &nbsp;<i class="fa-solid fa-hand-holding-droplet"></i> &nbsp; Request Blood &nbsp; &nbsp;</button>
                            </div>
                            <br/>
                            <br/>

                        </form>
                        <!-- End Form -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<!-- ===== End Main Body ===== -->

<!-- ===== Footer ===== -->
<jsp:include page="../../assets/footer/footer.jsp"></jsp:include>
<!-- ===== Footer ===== -->

<script type="text/javascript" src="assets/js/main.js"></script>

</body>
</html>
