<%--
  Created with IntelliJ IDEA.
  Author: George Amuzu
  User: gnamu
  Date: 11/06/2023
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header id="header" class="d-flex align-items-center">
  <div class="container d-flex align-items-center justify-content-between">
    <div class="logo">
      <h1 class="text-light">
        <a class="navbar-brand" href="<%= request.getContextPath() %>/admin_dashboard">
          <img src="assets/img/web/logo.png" alt="logo">
        </a>
      </h1>
    </div>

    <nav id="navbar" class="navbar navi">
      <ul>
        <li id="home"><a class="nav-link" href="<%=request.getContextPath()%>/"><i class="fa-solid fa-gauge-high"></i> &nbsp; Home</a> </li>
        <%--<li id="about"><a class="nav-link" href="<%=request.getContextPath()%>/about_us"><i class="fa-solid fa-address-card"></i> &nbsp; About Us</a> </li>
        <li id="contact"><a class="nav-link" href="<%=request.getContextPath()%>/contact_us"><i class="fa-solid fa-phone-volume"></i> &nbsp; Contact Us</a> </li>--%>
        <%--<li><a class="nav-link" href=""><i class="fas fa-sign-in-alt"></i> &nbsp; Login </a> </li>--%>
        <li class="dropdown" id="user"><a href="#"><i class="fas fa-circle-user"></i> <span> &nbsp; ${admin.firstName} ${admin.lastName}</span> <i class="bi bi-chevron-down"></i> </a>
          <ul>
            <li><a class="nav-link" href="<%=request.getContextPath()%>/admin_profile"><i class="fa-solid fa-id-card"></i> &nbsp; View Profile </a> </li>
            <li><a class="nav-link" href="<%=request.getContextPath()%>/admin_edit"><i class="fa-solid fa-user-pen"></i> &nbsp; Edit Profile </a> </li>
            <li><a class="nav-link" href="<%=request.getContextPath()%>/admin_password"><i class="fa-solid fa-key"></i> &nbsp; Change Password </a> </li>
            <li><a class="nav-link" href="<%= request.getContextPath() %>/admin_logout"><i class="fas fa-sign-in-alt"></i> &nbsp; Logout </a> </li>
          </ul>
        </li>
      </ul>
      <i class="bi bi-list mobile-nav-toggle"></i>
    </nav>
  </div>
</header>
