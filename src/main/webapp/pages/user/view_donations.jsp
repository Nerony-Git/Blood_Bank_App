<%--
  Created with IntelliJ IDEA.
  Author: George Amuzu
  User: gnamu
  Date: 20/06/2023
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Blood Bank - Donation List</title>
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
                        <li class="breadcrumb-item active" aria-current="page">  Donation List </li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>

    <div class="container p-5">
        <div class="row">
            <div class="card bg_border">
                <div class="card-header text-center">
                    <img src="assets/img/web/view_donations.png" alt="View donations title.">
                </div>

                <div class="card-body">
                    <%--<p class="fw-bold text-center text-primary fs-4">  List of Donations. </p>--%>

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
                            <th scope="col">Donation ID</th>
                            <th scope="col">Donor Name</th>
                            <th scope="col" style="text-align: center">Donation Camp</th>
                            <th scope="col" style="text-align: center">Donation Date</th>
                            <th scope="col" style="text-align: center">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="donation" items="${allDonorDonations}">
                            <tr>
                                <td><c:out value="${donation.donationID}"/></td>
                                <td><c:out value="${donation.donorID}"/></td>
                                <td style="text-align: center"><c:out value="${donation.donationCamp}"/></td>
                                <td style="text-align: center"><c:out value="${donation.donationDate}"/></td>
                                <td style="text-align: center">
                                    <div class="btn-group">
                                        <a href="view_donor_donation?id=<c:out value="${donation.donationID}"/>" class="btn btn-sm btn-primary"><i class="fa-solid fa-eye"></i>  View </a> &nbsp;
                                        <%--<a href="edit_donor?id=<c:out value="${donation.donationID}"/>" class="btn btn-sm btn-warning"><i class="fa-solid fa-user-pen"></i>  Edit </a> &nbsp;
                                        <a href="" class="btn btn-sm btn-danger"><i class="fa-solid fa-trash-can"></i>  Delete </a>--%>
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
<!-- ===== Footer ===== -->

<script type="text/javascript" src="assets/js/main.js"></script>

</body>
</html>
