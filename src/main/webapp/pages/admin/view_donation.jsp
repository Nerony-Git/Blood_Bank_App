<%--
  Created with IntelliJ IDEA.
  Author: George Amuzu
  User: gnamu
  Date: 22/06/2023
  Time: 07:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Blood Bank - View Donation Details</title>
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
            <li class="breadcrumb-item active"><a href="<%=request.getContextPath()%>/view_donations">  Blood Donations </a></li>
            <li class="breadcrumb-item active" aria-current="page">View Blood Donation Details</li>
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
            <img src="assets/img/web/view_donation.png" alt="Donation title">
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

            <label class="form-label"> Donation ID</label>
            <div class="input-group mb-3">
              <span class="input-group-text" id="donationID"><i class="fa-solid fa-tags"></i></span>
              <input type="text" class="form-control" aria-label="User ID" aria-describedby="donationID" disabled value="${donation.donationID}" />
            </div>
            <input type="hidden" id="donorID" value="${donation.donorID}">

            <label class="form-label"> Donation Camp</label>
            <div class="input-group mb-3">
              <span class="input-group-text" id="donationCamp"><i class="fa-solid fa-hospital"></i> </span>
              <input type="text" class="form-control" aria-label="Donation Camp" aria-describedby="donationCamp" disabled value="${donation.camp}">
            </div>

            <label class="form-label">Donation Date</label>
            <div class="input-group mb-3">
              <span class="input-group-text" id="donationDate"><i class="fa-solid fa-calendar"></i> </span>
              <input type="date" name="donationDate" class="form-control" aria-label="Donation Date" aria-describedby="donationDate" disabled value="${donation.donationDate}">
            </div>

            <label class="form-label">Units Donated</label>
            <div class="input-group mb-3">
              <span class="input-group-text" id="units"><i class="fa-solid fa-droplet"></i> </span>
              <input type="number" name="bloodUnit" class="form-control" aria-label="Units Donated" aria-describedby="units" disabled value="${donation.bloodUnit}">
            </div>

            <label class="form-label">Comments</label>
            <div class="input-group mb-3">
              <span class="input-group-text" id="comments"><i class="fa-solid fa-comments"></i> </span>
              <textarea name="comments" class="form-control" rows="4" aria-label="Comments" aria-describedby="comments" disabled>${donation.comment}</textarea>
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
<!-- ===== End Footer ===== -->

<script type="text/javascript" src="assets/js/main.js"></script>
</body>
</html>
