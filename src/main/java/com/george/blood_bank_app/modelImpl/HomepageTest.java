package com.george.blood_bank_app.modelImpl;

import com.george.blood_bank_app.Homepage;
import com.github.javafaker.Faker;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.*;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.*;

@GraphWalker(value = "random(edge_coverage(100))", start = "e_init")
public class HomepageTest extends ExecutionContext implements Homepage {

    Faker faker = new Faker();
    @Override
    public void e_DonorLoginFailed() {
        $(By.name("username")).sendKeys(new Faker().name().username());
        $(By.name("password")).sendKeys(new Faker().internet().password());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void v_DonorLogin() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - User Login"));
    }

    @Override
    public void e_AboutUs() {
        // Click on the "About Us" link
        $(By.cssSelector("li#about a")).click();
    }

    @Override
    public void e_DonorSignup() {
        // Click on the "Donor Signup" link
        $("li#signup").hover();
        $("li#signup ul li:nth-child(1) a").click();
    }

    @Override
    public void v_AdminSignup() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Admin Registration"));
    }

    @Override
    public void e_ContactUs() {
        // Click on the "Contact Us" link
        $(By.linkText("CONTACT US")).click();
    }

    @Override
    public void e_AdminSignupFailed() {
        fillAdminData();
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void e_DonorSignupSuccess() {
        fillAdminData();
        $(By.name("bloodGroup")).sendKeys(faker.name().bloodGroup());
        $(By.name("password")).clear();
        $(By.name("password")).sendKeys(faker.internet().password());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void v_DonorPage() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - User Dashboard"));
    }

    @Override
    public void e_AdminLoginSuccess() {
        $(By.name("username")).sendKeys("test");
        $(By.name("password")).sendKeys("12345");
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void e_AdminLoginFailed() {
        $(By.name("username")).sendKeys(new Faker().name().username());
        $(By.name("password")).sendKeys(new Faker().internet().password());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void e_DonorLogout() {
        // Click on the "Donor Logout" link
        $("li#user").hover();
        $("li#user ul li:nth-child(4) a").click();
    }

    @Override
    public void v_Homepage() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank"));
    }

    @Override
    public void e_DonorLoginSuccess() {
        $(By.name("username")).sendKeys("test");
        $(By.name("password")).sendKeys("12345");
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void e_Homepage() {
        // Click on the "Homepage" link
        $(By.cssSelector("li#home a")).click();
    }

    @Override
    public void v_DonorSignup() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - User Registration"));
    }

    @Override
    public void e_AdminLogin() {
        // Click on the "Admin Login" link
        $("li#login").hover();
        $("li#login ul li:nth-child(2) a").click();
    }

    @Override
    public void v_ContactUs() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Contact Us"));
    }

    @Override
    public void v_AdminLogin() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Admin Login"));
    }

    @Override
    public void e_AdminSignup() {
        // Click on the "Admin Signup" link
        $("li#signup").hover();
        $("li#signup ul li:nth-child(2) a").click();
    }

    @Override
    public void e_init() {
        open("http://localhost:8080/Blood_Bank_App-1.0-SNAPSHOT/");
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank"));
    }

    @Override
    public void e_AdminSignupSuccess() {
        fillAdminData();
        $(By.name("bloodGroup")).sendKeys(faker.name().bloodGroup());
        $(By.name("password")).clear();
        $(By.name("password")).sendKeys(faker.internet().password());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void e_AdminLogout() {
        // Click on the "Admin Logout" link
        $("li#user").hover();
        $("li#user ul li:nth-child(4) a").click();
    }

    @Override
    public void v_AdminPage() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Admin Dashboard"));
    }

    @Override
    public void e_DonorSignupFailed() {
        fillAdminData();
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void e_DonorLogin() {
        // Click on the "Donor Signup" link
        $("li#login").hover();
        $("li#login ul li:nth-child(1) a").click();
    }

    @Override
    public void v_AboutUs() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - About Us"));
    }

    private void fillAdminData() {
        // Format the date in "yyyy-MM-dd" format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        $(By.name("firstName")).clear();
        $(By.name("firstName")).sendKeys(faker.name().firstName());
        $(By.name("lastName")).clear();
        $(By.name("lastName")).sendKeys(faker.name().lastName());
        $(By.name("otherName")).clear();
        $(By.name("otherName")).sendKeys(faker.name().nameWithMiddle());
        $(By.name("username")).clear();
        $(By.name("username")).sendKeys(faker.name().username());
        $(By.name("gender")).sendKeys(faker.options().option("M", "F"));
        $(By.name("dob")).clear();
        $(By.name("dob")).sendKeys(dateFormat.format(faker.date().birthday()));
        $(By.name("contact")).clear();
        $(By.name("contact")).sendKeys(faker.phoneNumber().cellPhone());
        $(By.name("email")).clear();
        $(By.name("email")).sendKeys(faker.internet().emailAddress());
        $(By.name("address")).clear();
        $(By.name("address")).sendKeys(faker.address().fullAddress());
        $(By.name("postalAddress")).clear();
        $(By.name("postalAddress")).sendKeys(faker.address().zipCode());
    }
    
    @BeforeExecution
    public void setup() {
        browser = "firefox";
    }

    @AfterExecution
    public void cleanup() {

    }

    @BeforeElement
    public void printBeforeElement() {
        System.out.println("Before element: " + getCurrentElement().getName());
    }

    @AfterElement
    public void printAfterElement() {
        System.out.println("After Element: " + getCurrentElement().getName());
    }
}
