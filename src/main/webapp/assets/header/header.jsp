<%--
  Created with IntelliJ IDEA.
  Author: George Amuzu
  User: gnamu
  Date: 08/06/2023
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header id="header" class="d-flex align-items-center">
    <div class="container d-flex align-items-center justify-content-between">
        <div class="logo">
            <h1 class="text-light">
                <a class="navbar-brand" href="<%= request.getContextPath() %>/">
                    <img src="assets/img/web/logo.png" alt="logo">
                </a>
            </h1>
        </div>

        <nav id="navbar" class="navbar navi">
            <ul>
                <li id="home"><a class="nav-link" href="<%=request.getContextPath()%>/"><i class="fa-solid fa-gauge-high"></i> &nbsp; Home</a> </li>
                <li id="about"><a class="nav-link" href="<%=request.getContextPath()%>/about_us"><i class="fa-solid fa-address-card"></i> &nbsp; About Us</a> </li>
                <li id="contact"><a class="nav-link" href="<%=request.getContextPath()%>/contact_us"><i class="fa-solid fa-phone-volume"></i> &nbsp; Contact Us</a> </li>
                <%--<li><a class="nav-link" href=""><i class="fas fa-sign-in-alt"></i> &nbsp; Login </a> </li>--%>
                <li class="dropdown" id="signup"><a href="#"><i class="fas fa-address-book"></i> <span> &nbsp; Signup</span> <i class="bi bi-chevron-down"></i> </a>
                    <ul>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/user_register"><i class="fas fa-user-plus"></i> &nbsp; Donor Signup </a> </li>
                        <%--<li><a class="nav-link" href="<%=request.getContextPath()%>/doctor_login"><i class="fas fa-sign-in-alt"></i> &nbsp; Doctor Login </a> </li>--%>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/admin_register"><i class="fas fa-user-gear"></i> &nbsp; Admin Signup </a> </li>
                    </ul>
                </li>
                <li class="dropdown" id="login"><a href="#"><i class="fas fa-sign-in-alt"></i> <span> &nbsp; Logins</span> <i class="bi bi-chevron-down"></i> </a>
                    <ul>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/user_login"><i class="fas fa-sign-in-alt"></i> &nbsp; Donor Login </a> </li>
                        <%--<li><a class="nav-link" href="<%=request.getContextPath()%>/doctor_login"><i class="fas fa-sign-in-alt"></i> &nbsp; Doctor Login </a> </li>--%>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/admin_login"><i class="fas fa-sign-in-alt"></i> &nbsp; Admin Login </a> </li>
                    </ul>
                </li>
            </ul>
            <i class="bi bi-list mobile-nav-toggle"></i>
        </nav>
    </div>
</header>
