<%--
  Created with IntelliJ IDEA.
  Author: George Amuzu
  User: gnamu
  Date: 18/06/2023
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Blood Bank - Donors</title>
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
            <li class="breadcrumb-item active" aria-current="page">  Donors List </li>
          </ol>
        </nav>
      </div>
    </div>
  </div>

  <div class="container p-5">
    <div class="row">
      <div class="card bg_border">
        <div class="card-header text-center">
          <img class="bg_img" src="assets/img/admin/list_of_donors.png" alt="List of donors title">
          <%--<p class="fs-4 text-center text-white mt-2">
            <i class="fa-solid fa-user"></i>   Blood Donated
          </p>--%>
        </div>

        <div class="card-body">
          <c:if test="${not empty successMsg}">
            <p class="text-center text-success fs-5">${successMsg}</p>
            <c:remove var="successMsg" scope="session" />
          </c:if>

          <c:if test="${not empty errorMsg}">
            <p class="text-center text-danger fs-5">${errorMsg}</p>
            <c:remove var="errorMsg" scope="session" />
          </c:if>

          <table class="table table-striped table-hover">
            <thead>
            <tr class="bg_color text-white">
              <th scope="col">Donor ID</th>
              <th scope="col">Donor Name</th>
              <th scope="col" style="text-align: center">Gender</th>
              <th scope="col" style="text-align: center">Contact</th>
              <th scope="col" style="text-align: center">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="donor" items="${allDonors}">
              <tr>
                <td><c:out value="${donor.donorID}"/></td>
                <td><c:out value="${donor.firstName} ${donor.otherName} ${donor.lastName}"/></td>
                <td style="text-align: center"><c:out value="${donor.gender}"/></td>
                <td style="text-align: center"><c:out value="${donor.contact}"/></td>
                <td style="text-align: center">
                  <div class="btn-group">
                    <a href="view_donor?id=<c:out value="${donor.donorID}"/>" class="btn btn-sm btn-primary"><i class="fa-solid fa-eye"></i>  View </a> &nbsp;
                    <a href="edit_donor?id=<c:out value="${donor.donorID}"/>" class="btn btn-sm btn-warning"><i class="fa-solid fa-user-pen"></i>  Edit </a> &nbsp;
                    <a href="delete_user?id=<c:out value="${donor.donorID}"/>" class="btn btn-sm btn-danger"><i class="fa-solid fa-trash-can"></i>  Delete </a>
                  </div>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
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
