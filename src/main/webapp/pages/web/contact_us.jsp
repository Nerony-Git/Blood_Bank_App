<%--
  Created with IntelliJ IDEA.
  Author: George Amuzu
  User: gnamu
  Date: 09/06/2023
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Blood Bank - Contact Us</title>
    <jsp:include page="../../assets/head/head.jsp"></jsp:include>
    <link rel="stylesheet" href="assets/css/main.css">

</head>
<body style="background-color: cornsilk;">
<!-- ===== Header ===== -->
<jsp:include page="../../assets/header/header.jsp"></jsp:include>
<!-- ===== End Header ===== -->

<!-- ===== Main Body ===== -->
<main id="main">
    <!-- Contact Us Section -->
    <section id="contact" class="contact">
        <div class="container">
            <div class="section-title">
                <img src="assets/img/web/contact.png" alt="Contact us image">
            </div>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-6 d-flex align-items-stretch infos">
                    <div class="row">
                        <div class="col-lg-6 info d-flex flex-column align-items-stretch">
                            <i class="fa-solid fa-street-view"></i>
                            <h4>Address</h4>
                            <p>N354 Oxford Street <br/> London, UK</p>
                        </div>
                        <div class="col-lg-6 info d-flex flex-column align-items-stretch">
                            <i class="fa-solid fa-phone-volume"></i>
                            <h4>Call Us</h4>
                            <p>+449845678200 <br/> +442000000000</p>
                        </div>
                        <div class="col-lg-6 info d-flex flex-column align-items-stretch">
                            <i class="fa-solid fa-envelopes-bulk"></i>
                            <h4>Email Us</h4>
                            <p>info@gmail.com <br/> contact@gmail.com</p>
                        </div>
                        <div class="col-lg-6 info d-flex flex-column align-items-stretch">
                            <i class="fa-solid fa-clock"></i>
                            <h4>Working Hours</h4>
                            <p>Mon - Fri: 9AM to 5PM <br/> Saturday: 10AM to 2PM </p>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6 d-flex align-items-stretch contact-form-wrap">
                    <form action="" method="post" role="form" class="php-email-form">
                        <div class="row">
                            <div class="col-md-6 form-group">
                                <label for="fname">Full Name</label>
                                <div class="input-group">
                                    <span class="input-group-text" id="fname"><i class="fa-solid fa-user"></i> </span>
                                    <input type="text" name="fname" class="form-control" placeholder="Enter your full name here ..." aria-label="Full Name" aria-describedby="fname" required>
                                </div>
                            </div>
                            <div class="col-md-6 form-group mt-3 mt-md-0">
                                <label for="email">Email</label>
                                <div class="input-group">
                                    <span class="input-group-text" id="email"><i class="fa-solid fa-envelope"></i> </span>
                                    <input type="email" name="email" class="form-control" placeholder="Enter your email here ..." aria-label="Email" aria-describedby="email" required>
                                </div>
                            </div>
                        </div>
                        <div class="form-group mt-3">
                            <label for="subject">Subject</label>
                            <div class="input-group">
                                <span class="input-group-text" id="subject"><i class="fa-solid fa-newspaper"></i></span>
                                <input type="text" name="subject" class="form-control" placeholder="Enter the subject here ..." aria-label="Subject" aria-describedby="subject" required>
                            </div>
                        </div>
                        <div class="form-group mt-3">
                            <label for="message">Message</label>
                            <div class="input-group">
                                <span class="input-group-text" id="message"><i class="fa-solid fa-newspaper"></i></span>
                                <textarea name="message" class="form-control" rows="8" placeholder="Enter your message here ..." aria-label="Message" aria-describedby="message" required></textarea>
                            </div>
                        </div>
                        <div class="my-3">
                            <div class="loading">Loading</div>
                            <div class="error-message"></div>
                            <div class="sent-message">Your message was sent successfully.</div>
                        </div>
                        <div class="text-center">
                            <button type="submit"><i class="fa-solid fa-paper-plane"></i> &nbsp; Send Message </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <!-- End Contact Us Section -->
</main>
<!-- ===== End Main Body ===== -->

<!-- ===== Footer ===== -->
<jsp:include page="../../assets/footer/footer.jsp"></jsp:include>
<!-- ===== Footer ===== -->

<script type="text/javascript" src="assets/js/main.js"></script>

</body>
</html>
