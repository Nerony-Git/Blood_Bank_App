<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Blood Bank</title>
    <jsp:include page="assets/head/head.jsp"></jsp:include>
    <link rel="stylesheet" href="assets/css/main.css">

</head>
<body style="background-color: cornsilk;">
<!-- ===== Header ===== -->
<jsp:include page="assets/header/header.jsp"></jsp:include>
<!-- ===== End Header ===== -->

<!-- ===== Main Body ===== -->
<main id="main">

    <!-- Slider Section -->
    <section class="slider section-head">
        <div class="container">
            <div class="row">
                <div class="flexslider carousel">
                    <div class="ribbon ribbon-top-left"><span>ribbon</span></div>
                    <ul class="slides">
                        <li><img src="assets/img/slides/slider1.jpg"></li>
                        <li><img src="assets/img/slides/slider2.jpg"></li>
                        <li><img src="assets/img/slides/slider3.jpg"></li>
                        <li><img src="assets/img/slides/slider4.jpg"></li>
                        <li><img src="assets/img/slides/slider5.jpg"></li>
                        <li><img src="assets/img/slides/slider6.jpg"></li>
                        <li><img src="assets/img/slides/slider7.jpg"></li>
                        <li><img src="assets/img/slides/slider8.jpg"></li>
                        <li><img src="assets/img/slides/slider9.jpg"></li>
                        <li><img src="assets/img/slides/slider10.jpg"></li>
                        <li><img src="assets/img/slides/slider11.jpg"></li>
                        <li><img src="assets/img/slides/slider12.jpg"></li>
                        <li><img src="assets/img/slides/slider13.jpg"></li>
                        <li><img src="assets/img/slides/slider14.jpg"></li>
                        <li><img src="assets/img/slides/slider15.jpg"></li>
                    </ul>
                    <div class="ribbon ribbon-bottom-right"><span>ribbon</span></div>
                </div>
            </div>
        </div>
    </section>
    <!-- End Slider Section -->

    <section id="about" class="about section-bg">
        <div class="container">
            <div class="section-title">
                <h2> Life Saver </h2>
                <p class="fst-italic">
                    Blood, an essential element for sustaining life, is universally recognized as invaluable. It plays a crucial role in saving countless lives under various circumstances worldwide. The demand for blood is substantial, with approximately 39,000 units of Red Blood Cells required each day. Annually, over 29 million units of blood components are transfused. However, despite an increase in the number of donors, there continues to be a shortage during emergencies, primarily due to inadequate information and limited accessibility. We firmly believe that this tool can effectively address these challenges by connecting blood donors with recipients, offering a promising solution.
                </p>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <img src="assets/img/web/about.jpg" class="img-fluid" alt="Blood donation image.">
                </div>
                <div class="col-lg-6 pt-4 pt-lg-0 content">
                    <h3>Blood Donor</h3>
                    <p class="fst-italic" style="text-align: justify">
                        Thank you for visiting our website. If you are interested in becoming a blood donor, we greatly appreciate your online registration. If you are in need of blood, we are here to provide assistance. While the organizers have taken every precaution to gather accurate and up-to-date information about potential blood donors, neither the organizers nor ICM Computers can guarantee the accuracy of the information or the suitability of the individuals listed.
                    </p>
                    <ul>
                        <li class="fst-italic" style="text-align: justify"><i class="bi bi-check-circle"></i> This blood donor list is hosted on this site on behalf of the "Life Saver Blood Bank" as a non-profit public service. </li>
                        <li class="fst-italic" style="text-align: justify"><i class="bi bi-check-circle"></i> It is important to note that this service is completely free of charge.</li>
                        <li class="fst-italic" style="text-align: justify"><i class="bi bi-check-circle"></i> They cannot be held liable for any direct or indirect damages that may arise, including loss of life or infections resulting from blood transfusions.</li>
                        <%--<li><i class="bi bi-check-circle"></i> </li>
                        <li><i class="bi bi-check-circle"></i> </li>--%>
                    </ul>
                    <p class="fst-italic">
                        It is kindly requested that donors keep their contact details regularly updated.
                    </p>
                </div>
            </div>
        </div>
    </section>



</main>
<!-- ===== End Main Body ===== -->

<!-- ===== Footer ===== -->
<jsp:include page="assets/footer/footer.jsp"></jsp:include>
<!-- ===== Footer ===== -->

<script type="text/javascript" src="assets/js/main.js"></script>

</body>
</html>