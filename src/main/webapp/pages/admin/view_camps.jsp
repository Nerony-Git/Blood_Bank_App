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
    <title>Blood Bank - View Camps</title>
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
                        <li class="breadcrumb-item active" aria-current="page">  Donation Camps </li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>

    <div class="container p-5">
        <div class="row">
            <div class="card bg_border">
                <div class="card-header text-center">
                    <img class="bg_img" src="assets/img/admin/donation_camps.png" alt="Donation camps title.">
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

                        <div class="input-group justify mb-3" style="justify-content: end;">
                            <%--<button type="submit" class="bg_btn">&nbsp;<i class="fa-solid fa-tent"></i> &nbsp; Add New Donation Camp &nbsp;</button>--%>
                            <a class="btn btn_sm bg_btn" style="color: cornsilk" href="<%=request.getContextPath()%>/new_camp">&nbsp; <i class="fa-solid fa-tent"></i> &nbsp; Add New Donation Camp &nbsp;</a>
                        </div>


                    <table class="table table-striped table-hover">
                        <thead>
                        <tr class="bg_color text-white">
                            <th scope="col">Donation Camp ID</th>
                            <th scope="col">Donation Camp Name</th>
                            <th scope="col" style="text-align: center">Organizers</th>
                            <th scope="col" style="text-align: center">Postal Address</th>
                            <th scope="col" style="text-align: center">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="donationCamp" items="${donationCamps}">
                            <tr>
                                <td><c:out value="${donationCamp.campID}"/></td>
                                <td><c:out value="${donationCamp.campName}"/></td>
                                <td style="text-align: center"><c:out value="${donationCamp.organizers}"/></td>
                                <td style="text-align: center"><c:out value="${donationCamp.postal_address}"/></td>
                                <td style="text-align: center">
                                    <div class="btn-group">
                                        <a href="view_camp?id=<c:out value="${donationCamp.campID}"/>" class="btn btn-sm btn-primary"><i class="fa-solid fa-eye"></i>  View </a> &nbsp;
                                        <a href="edit_camp?id=<c:out value="${donationCamp.campID}"/>" class="btn btn-sm btn-warning"><i class="fa-solid fa-user-pen"></i>  Edit </a> &nbsp;
                                        <a href="" class="btn btn-sm btn-danger"><i class="fa-solid fa-trash-can"></i>  Delete </a>
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
