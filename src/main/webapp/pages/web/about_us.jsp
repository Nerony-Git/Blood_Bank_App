<%--
  Created with IntelliJ IDEA.
  Author: George Amuzu
  User: gnamu
  Date: 09/06/2023
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Blood Bank - About Us</title>
  <jsp:include page="../../assets/head/head.jsp"></jsp:include>
  <link rel="stylesheet" href="assets/css/main.css">

</head>
<body style="background-color: cornsilk;">
<!-- ===== Header ===== -->
<jsp:include page="../../assets/header/header.jsp"></jsp:include>
<!-- ===== End Header ===== -->

<!-- ===== Main Body ===== -->
<main id="main">
  <!-- About Section -->
  <section id="about" class="about">
    <div class="container">
      <div class="section-title">
        <img src="assets/img/web/about.png" alt="About us logo.">
      </div>
      <div class="row">
        <div class="col-lg-4">
          <img src="assets/img/web/about_us.jpg" class="img-fluid" alt="Blood transfusion image.">
        </div>
        <div class="col-lg-8 pt-4 pt-lg-0 content">
          <p class="fst-italic" style="text-align: justify">
            Life Saver is a valuable outcome of the community welfare initiative. Blood is universally acknowledged as the vital lifeline that sustains human existence, saving countless lives in diverse situations worldwide. Every two seconds, there is someone, somewhere in dire need of blood. The annual requirement for blood components exceeds a staggering 29 million units, with approximately 39,000 units of Red Blood Cells needed daily. Regrettably, our ability to meet our nation's blood transfusion demand is limited, fulfilling only around 1% of the total.
            <br/>
            Despite the significant increase in the number of generous blood donors, the availability of this life-saving resource often falls short during critical emergencies. This shortage can be primarily attributed to the lack of comprehensive information and limited accessibility. However, we firmly believe that our innovative tool holds the potential to overcome these challenges by seamlessly connecting blood donors with recipients in a timely and efficient manner.
          </p>
        </div>
      </div>
      <div class="row">
        <p class="fst-italic" style="text-align: justify">
          <br/>
          As we reflect on the impact of our collective efforts, we are humbled by the profound difference we have made in the lives of countless individuals. Each blood donation is a precious gift of life, providing hope and healing to patients facing challenging medical circumstances. It is through the selflessness and compassion of our blood donors that we can continue to meet the growing demand for this vital resource. We remain steadfast in our commitment to promoting awareness about the importance of blood donation and encouraging more individuals to join this noble cause. Together, let us inspire others to embrace the spirit of giving and make a lasting difference in our communities. Thank you for being a part of this remarkable journey towards saving lives and making our world a better place.
          <br/>
          <br/>
          We take this opportunity to remind each and every visitor of the immense power we hold to positively impact lives. Let us join hands and take action right now, right here. If you meet the eligibility criteria for blood donation, we implore you to take a proactive step towards becoming a registered blood donor today, thereby becoming a beacon of hope for those in need.
          <br/>
          <br/>
          Moreover, we extend our heartfelt gratitude to our dedicated and hardworking team, whose unwavering commitment and tireless efforts have transformed this dream into a reality. Their exceptional ideas and relentless pursuit of excellence have been instrumental in the success of this noble initiative. Additionally, we would like to express our deep appreciation to our cherished friends and well-wishers for their unwavering support and encouragement throughout the course of this remarkable project. It is with great satisfaction that we declare that, together, we have made significant strides in creating a slightly better and safer society for all to thrive in.
          <br/>
          <br/>
          Once again, we extend our sincere thanks and wish you a fulfilling and rewarding blood donation journey. Your selfless act of donating blood will undoubtedly make a meaningful difference in the lives of those in need.
          <br/>
          <br/>
        </p>
      </div>
    </div>
  </section>
  <!-- End About Section -->
</main>
<!-- ===== End Main Body ===== -->

<!-- ===== Footer ===== -->
<jsp:include page="../../assets/footer/footer.jsp"></jsp:include>
<!-- ===== Footer ===== -->

<script type="text/javascript" src="assets/js/main.js"></script>

</body>
</html>
