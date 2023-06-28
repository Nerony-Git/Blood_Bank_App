<%--
  Created with IntelliJ IDEA.
  Author: George Amuzu
  User: gnamu
  Date: 22/06/2023
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Blood Bank - View Camp Details</title>
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
<jsp:include page="../../assets/navbar/user_navbar.jsp"></jsp:include>
<!-- ===== End Header ===== -->

<!-- ===== Main Body ===== -->
<main id="main">
    <div class="container mt-5">
        <div class="row">
            <div class="breadcrumbs">
                <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><i class="fa-solid fa-gauge"></i><a href="<%=request.getContextPath()%>/admin_dashboard">  Home </a></li>
                        <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/view_camps"> Donation Camps</a> </li>
                        <li class="breadcrumb-item active" aria-current="page">  View Camp Details </li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>

    <div class="container p-5">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="card bg_border">
                    <div class="card-header text-center">
                        <img src="assets/img/web/donation.png" alt="Donation title">
                        <%--<p class="fs-4 text-center text-white mt-2">
                          <i class="fa-solid fa-user"></i>   Blood Donated
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

                        <label class="form-label"> Donation Camp ID</label>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="donationID"><i class="fa-solid fa-tags"></i></span>
                            <input type="text" class="form-control" aria-label="User ID" aria-describedby="donationID" disabled value="${donationCamp.campID}" />
                        </div>

                        <label class="form-label">Donation Camp Name</label>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="donationCampName"><i class="fa-solid fa-tent"></i> </span>
                            <input type="text" name="donationCampName" class="form-control" aria-label="Donation Camp Name" aria-describedby="donationCampName" disabled value="${donationCamp.campName}">
                        </div>

                        <label class="form-label">Organizers</label>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="organizers"><i class="fa-solid fa-users-rectangle"></i> </span>
                            <input type="text" name="organizers" class="form-control" aria-label="Organizers" aria-describedby="organizers" disabled value="${donationCamp.organizers}">
                        </div>

                        <label class="form-label">Address</label>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="address"><i class="fa-solid fa-map-location-dot"></i> </span>
                            <input type="text" name="address" class="form-control" aria-label="Address" aria-describedby="address" disabled value="${donationCamp.address}">
                        </div>

                        <label class="form-label">Postal Address</label>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="postalAddress"><i class="fa-solid fa-street-view"></i> </span>
                            <input type="text" name="postalAddress" class="form-control" aria-label="Postal Address" aria-describedby="postalAddress" disabled value="${donationCamp.postal_address}">
                        </div>

                        <label class="form-label">Details</label>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="details"><i class="fa-solid fa-text-width"></i> </span>
                            <textarea name="details" class="form-control" rows="4" aria-label="Details" aria-describedby="details" disabled >${donationCamp.details}</textarea>
                        </div>
                        <br/>
                        <br/>
                        <br/>

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
