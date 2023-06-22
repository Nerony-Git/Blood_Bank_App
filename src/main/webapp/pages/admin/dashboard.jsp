<%--
  Created with IntelliJ IDEA.
  Author: George Amuzu
  User: gnamu
  Date: 11/06/2023
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Blood Bank - Admin Dashboard</title>
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
    <div class="container p-5">
        <div class="row mb-5">
            <div class="breadcrumbs">
                <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item active" aria-current="page"><i class="fa-solid fa-gauge"></i>  Home </li>
                    </ol>
                </nav>
            </div>
        </div>

        <div class="row dash mb-5">

            <div class="col-md-4 offset-md-2">
                <a href="<%=request.getContextPath()%>/view_donations">
                    <div class="card bg_border">
                        <div class="card-body text-center">
                            <br/>
                            <i class="fa-solid fa-droplet fa-3x"></i>
                            <br/>
                            <br/>
                            <p class="fs-4 text-center">View <br/> Donations</p>
                        </div>
                    </div>
                </a>
            </div>

            <div class="col-md-4">
                <a href="">
                    <div class="card bg_border">
                        <div class="card-body text-center">
                            <br/>
                            <i class="fa-solid fa-hand-holding-heart fa-3x"></i>
                            <br/>
                            <br/>
                            <p class="fs-4 text-center">View <br/> Requests</p>
                        </div>
                    </div>
                </a>
            </div>

        </div>

        <div class="row dash">

            <div class="col-md-4">
                <a href="">
                    <div class="card bg_border">
                        <div class="card-body text-center">
                            <br/>
                            <i class="fa-solid fa-dna fa-3x"></i>
                            <br/>
                            <br/>
                            <p class="fs-4 text-center">Blood <br/> Groups</p>
                        </div>
                    </div>
                </a>
            </div>

            <div class="col-md-4">
                <a href="">
                    <div class="card bg_border">
                        <div class="card-body text-center">
                            <br/>
                            <i class="fa-solid fa-tents fa-3x"></i>
                            <br/>
                            <br/>
                            <p class="fs-4 text-center">Donation <br/> Camps</p>
                        </div>
                    </div>
                </a>
            </div>

            <div class="col-md-4">
                <a href="<%=request.getContextPath()%>/users">
                    <div class="card bg_border">
                        <div class="card-body text-center">
                            <br/>
                            <i class="fa-solid fa-users-line fa-3x"></i>
                            <br/>
                            <br/>
                            <p class="fs-4 text-center">Donors <br/> List </p>
                        </div>
                    </div>
                </a>
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
