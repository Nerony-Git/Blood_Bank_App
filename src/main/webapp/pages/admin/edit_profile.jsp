<%--
  Created with IntelliJ IDEA.
  Author: George Amuzu
  User: gnamu
  Date: 13/06/2023
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Blood Bank - View Profile</title>
    <jsp:include page="../../assets/head/head.jsp"></jsp:include>
    <link rel="stylesheet" href="assets/css/main.css">

</head>
<body style="background-color: cornsilk; min-height: 100vh; display: flex; flex-direction: column" class="blood">

<!-- Confirm user is login -->
<c:if test = "${empty admin}">
    <c:redirect url="/admin_login"></c:redirect>
</c:if>
<!-- Confirm user is login -->

<!-- ===== Header ===== -->
<jsp:include page="../../assets/navbar/admin_navbar.jsp"></jsp:include>
<!-- ===== End Header ===== -->

<!-- ===== Main Body ===== -->
<main id="main">
    <div class="container mt-5">
        <div class="row">
            <div class="breadcrumbs">
                <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><i class="fa-solid fa-gauge"></i><a href="<%=request.getContextPath()%>/admin_dashboard">  Home </a></li>
                        <li class="breadcrumb-item active" aria-current="page">  View Profile </li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>

    <div class="container p-5">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="card bg_border">
                    <div class="card-header text-center text-white bg_color">
                        <p class="fs-4 text-center text-white mt-2">
                            <i class="fa-solid fa-user-pen"></i>  View Profile Details
                        </p>
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

                        <div class="row">
                            <!-- Form -->
                            <form action="<%=request.getContextPath()%>" method="post">
                                <div class="col-md-6">
                                    <label class="form-label" for="uID">User ID</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="uID"><i class="fa-solid fa-user-tag"></i> </span>
                                        <input type="text" name="uID" class="form-control" aria-label="User ID" aria-describedby="uID" readonly value="${admin.adminID}">
                                    </div>
                                    <input type="hidden" name="uID" value="${admin.adminID}">

                                    <label class="form-label" for="firstName">First Name</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="firstName"><i class="fa-solid fa-user"></i> </span>
                                        <input type="text" name="firstName" class="form-control" aria-label="First Name" aria-describedby="firstName" required value="${admin.firstName}">
                                    </div>

                                    <label class="form-label" for="lastName">Last Name</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="lastName"><i class="fa-solid fa-user"></i> </span>
                                        <input type="text" name="lastName" class="form-control" aria-label="Last Name" aria-describedby="lastName" required value="${admin.lastName}">
                                    </div>

                                    <label class="form-label" for="otherName">Other Name</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="otherName"><i class="fa-solid fa-user"></i> </span>
                                        <input type="text" name="otherName" class="form-control" aria-label="Other Name" aria-describedby="otherName" value="${admin.otherName}">
                                    </div>

                                    <label class="form-label" for="username">Username</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="username"><i class="fa-solid fa-user-tag"></i> </span>
                                        <input type="text" name="username" class="form-control" aria-label="Username" aria-describedby="username" readonly value="${admin.username}">
                                    </div>

                                    <label class="form-label" for="address">Address</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="address"><i class="fa-solid fa-map-location-dot"></i> </span>
                                        <input type="text" name="address" class="form-control" aria-label="Address" aria-describedby="address" required value="${admin.address}">
                                    </div>

                                </div>
                                <div class="col-md-6">

                                    <label class="form-label" for="dob">Date of Birth</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="dob"><i class="fa-solid fa-calendar"></i> </span>
                                        <input type="date" name="dob" class="form-control" aria-label="Date of Birth" aria-describedby="dob" required value="${admin.dob}">
                                    </div>

                                    <label class="form-label" for="gender">Gender</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="gender"><i class="fa-solid fa-venus-mars"></i> </span>
                                        <select name="gender" class="form-select" aria-label="Gender" aria-describedby="gender" required>
                                            <option selected="selected" disabled="disabled">${admin.gender}</option>
                                            <option value="F">F</option>
                                            <option value="M">M</option>
                                        </select>
                                    </div>

                                    <label class="form-label" for="contact">Contact</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="contact"><i class="fa-solid fa-phone-volume"></i> </span>
                                        <input type="text" name="contact" class="form-control" aria-label="Contact" aria-describedby="contact" required value="${admin.contact}">
                                    </div>

                                    <label class="form-label" for="email">Email</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="email"><i class="fa-solid fa-envelope"></i> </span>
                                        <input type="text" name="email" class="form-control" aria-label="Email" aria-describedby="email" required value="${admin.email}">
                                    </div>

                                    <label class="form-label" for="postalAddress">Postal Address</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="postalAddress"><i class="fa-solid fa-street-view"></i> </span>
                                        <input type="text" name="postalAddress" class="form-control" aria-label="Postal Address" aria-describedby="postalAddress" required value="${admin.postalAddress}">
                                    </div>

                                    <label class="form-label" for="bloodGroup">Blood Group</label>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text" id="bloodGroup"><i class="fa-solid fa-staff-snake"></i> </span>
                                        <input type="text" name="bloodGroup" class="form-control" aria-label="Blood Group" aria-describedby="bloodGroup" required value="${admin.bloodGroup}">
                                    </div>
                                    <br/>
                                    <br/>

                                </div>
                                <button type="submit" class="btn bg_color text-white"><i class="fa-solid fa-user-gear"></i>   Update Profile </button>
                            </form>
                            <!-- End Form -->

                        </div>
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
