<%--
  Created with IntelliJ IDEA.
  Author: George Amuzu
  User: gnamu
  Date: 20/06/2023
  Time: 08:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Blood Bank - Donate Blood</title>
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
            <li class="breadcrumb-item active" aria-current="page">  Blood Donation </li>
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

            <!-- Form -->
            <form action="<%=request.getContextPath()%>/new_donation" method="post">
              <label class="form-label"> Donation ID</label>
              <div class="input-group mb-3">
                <span class="input-group-text" id="donationID"><i class="fa-solid fa-tags"></i></span>
                <input type="text" class="form-control" aria-label="User ID" aria-describedby="donationID" disabled placeholder="Donation ID will be automatically assigned." />
              </div>
              <input type="hidden" id="donorID" value="${user.donorID}">

              <label class="form-label"> Donation Camp</label>
              <div class="input-group mb-3">
                <span class="input-group-text" id="donationCamp"><i class="fa-solid fa-hospital"></i> </span>
                <select class="form-select" aria-label="Donation Camp" aria-describedby="donationCamp">
                  <option selected disabled> --- Select Donation Camp --- </option>
                </select>
              </div>

              <label class="form-label">Donation Date</label>
              <div class="input-group mb-3">
                <span class="input-group-text" id="donationDate"><i class="fa-solid fa-calendar"></i> </span>
                <input type="date" name="donationDate" class="form-control" aria-label="Donation Date" aria-describedby="donationDate" required>
              </div>

              <label class="form-label">Units Donated</label>
              <div class="input-group mb-3">
                <span class="input-group-text" id="units"><i class="fa-solid fa-droplet"></i> </span>
                <input type="number" name="bloodUnit" class="form-control" aria-label="Units Donated" aria-describedby="units" required placeholder="Number of blood units donated.">
              </div>

              <label class="form-label">Comments</label>
              <div class="input-group mb-3">
                <span class="input-group-text" id="comments"><i class="fa-solid fa-comments"></i> </span>
                <textarea name="comments" class="form-control" rows="4" aria-label="Comments" aria-describedby="comments" required placeholder="Type your comments here ..."></textarea>
              </div>
              <br/>
              <div class="input-group justify" style="justify-content: center;">
                <button type="submit" class="bg_btn">&nbsp;<i class="fa-solid fa-hand-holding-medical"></i>    Register Donation &nbsp;</button>
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
