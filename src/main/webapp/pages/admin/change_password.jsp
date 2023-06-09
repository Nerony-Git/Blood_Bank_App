<%--
  Created with IntelliJ IDEA.
  Author: George Amuzu
  User: gnamu
  Date: 15/06/2023
  Time: 08:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Blood Bank - Change Password</title>
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
            <li class="breadcrumb-item active" aria-current="page">  Change Password </li>
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
            <img class="bg_img" src="assets/img/web/change_password.png" alt="Change password title.">
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
            <form action="<%=request.getContextPath()%>/admin_password_change" method="post">
              <label class="form-label" for="uID">Admin ID</label>
              <div class="input-group mb-3">
                <span class="input-group-text" id="uID"><i class="fa-solid fa-user-tag"></i></span>
                <input type="text" class="form-control" aria-label="User ID" aria-describedby="uID" readonly value="<c:out value="${admin.adminID}" />" />
              </div>
              <input type="hidden" name="uID" value="<c:out value="${admin.adminID}"/>">
              <div class="input-group mb-3">
                &nbsp;
              </div>
              <label class="form-label" for="password2">Old Password</label>
              <div class="input-group mb-3">
                <span class="input-group-text" id="pass"><i class="fa-solid fa-lock"></i></span>
                <input type="password" name="password2" id="password2" class="form-control" placeholder="Enter your old password here..." aria-label="Old Password" aria-describedby="pass" required />
                <i class=" view_password input-group-text bi bi-eye-slash" id="togglePassword2" onclick="showPassword('password2', 'togglePassword2')"></i>
              </div>
              <label class="form-label" for="password">New Password</label>
              <div class="input-group mb-3">
                <span class="input-group-text" id="passed"><i class="fa-solid fa-lock"></i></span>
                <input type="password" name="password" id="password" class="form-control" placeholder="Enter your new password here..." aria-label="New Password" aria-describedby="passed" required />
                <i class=" view_password input-group-text bi bi-eye-slash" id="togglePassword" onclick="showPassword('password', 'togglePassword')"></i>
              </div>
              <br/>
              <br/>

              <div class="input-group justify" style="justify-content: center;">
                <button type="submit" class="bg_btn">&nbsp; &nbsp;<i class="fa-solid fa-user-gear"></i> &nbsp; Update Password &nbsp; &nbsp;</button>
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
<script type="text/javascript" src="assets/js/view_password.js"></script>

</body>
</html>