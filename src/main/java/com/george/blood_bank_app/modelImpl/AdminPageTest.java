package com.george.blood_bank_app.modelImpl;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.george.blood_bank_app.AdminPage;
import com.github.javafaker.Faker;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@GraphWalker(value = "random(edge_coverage(100))")
public class AdminPageTest extends ExecutionContext implements AdminPage {

    Faker faker = new Faker();
    WebDriverWait waiter;
    WebDriver driver;

    @Override
    public void e_NewRequest() {
        Selenide.open("http://localhost:8080/Blood_Bank_App-1.0-SNAPSHOT/view_new_requests");
        /*executeJavaScript("document.getElementById('newRequests').click();");*/
    }

    @Override
    public void v_AdminEditProfile() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Edit Profile"));
    }

    @Override
    public void v_ProcessRequest() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Edit Request Details"));
    }

    @Override
    public void v_NewRequest() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - New Requests"));
    }

    @Override
    public void e_EditDonationCampSuccess() {
        $(By.name("postalAddress")).clear();
        $(By.name("postalAddress")).sendKeys(faker.address().zipCode());
        $(By.name("details")).clear();
        $(By.name("details")).sendKeys(faker.lorem().sentence());
        $("button[type=\"submit\"]").click();
        /*new Thread(String.valueOf(5000));*/
    }

    @Override
    public void e_ViewRequests() {
        Selenide.open("http://localhost:8080/Blood_Bank_App-1.0-SNAPSHOT/view_requests");
        /*executeJavaScript("document.getElementById('viewRequests').click();");*/
    }

    @Override
    public void e_AdminViewProfile() {
        // Click on the "View Profile" link
        $("li#user").hover();
        $("li#user ul li:nth-child(1) a").click();
    }

    @Override
    public void e_EditDonationSuccess() {
        $(By.name("bloodUnit")).clear();
        $(By.name("bloodUnit")).sendKeys(faker.number().digit());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void v_EditDonorProfile() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Edit Donor Profile"));
    }

    @Override
    public void e_ProcessRequestSuccess() {
        $(By.name("status")).sendKeys("Approved");
        $(By.name("requestResponse")).clear();
        $(By.name("requestResponse")).sendKeys(faker.lorem().sentence());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void v_EditDonation() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Edit Donation Details"));
    }

    @Override
    public void e_NewRequest_nav() {
        $("a[href*='/view_new_requests']").click();
    }

    @Override
    public void v_ViewDonorProfile() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - View Donor Profile"));
    }

    @Override
    public void v_ViewDonation() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - View Donation Details"));
    }

    @Override
    public void e_ProcessRequest() {
        // Find all links containing "Process" in their text
        ElementsCollection processLinks = $$("a").filterBy(text("Process"));

        // Check if there are any matching links
        int numLinks = processLinks.size();
        if (numLinks > 0) {
            // Generate a random index and click on the link
            int randomIndex = (int) (Math.random() * numLinks);
            processLinks.get(randomIndex).click();
        } else {
            // Handle case when no matching links are found
            System.out.println("No 'Process' links found.");
        }
    }

    @Override
    public void e_AdminEditProfile() {
        // Click on the "Edit Profile" link
        $("li#user").hover();
        $("li#user ul li:nth-child(2) a").click();
    }

    @Override
    public void e_Donors() {
        executeJavaScript("document.getElementById('viewDonors').click();");
    }

    @Override
    public void e_EditDonation() {
        // Find all links containing "Edit" in their text
        ElementsCollection editLinks = $$("a").filterBy(text("Edit"));

        // Check if there are any matching links
        int numLinks = editLinks.size();
        if (numLinks > 0) {
            // Generate a random index and click on the link
            int randomIndex = (int) (Math.random() * numLinks);
            editLinks.get(randomIndex).click();
        } else {
            // Handle case when no matching links are found
            System.out.println("No 'Edit' links found.");
        }
    }

    @Override
    public void e_AddNewDonorSuccess() {
        fillDonorData();
        $(By.name("bloodGroup")).sendKeys(faker.name().bloodGroup());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void e_Donors_nav() {
        $("a[href*='/users']").click();
    }

    @Override
    public void e_ViewRequests_nav() {
        $("a[href*='/view_requests']").click();
    }

    @Override
    public void v_EditDonationCamp() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Edit Camp Details"));
    }

    @Override
    public void e_DonationCamps() {
        executeJavaScript("document.getElementById('viewCamps').click();");
    }

    @Override
    public void v_AdminChangePassword() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Change Password"));
    }

    @Override
    public void e_AdminHomepage() {
        // Click on the "Homepage" link
        $(By.cssSelector("li#home a")).click();
    }

    @Override
    public void v_ViewRequest() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - View Request Details"));
    }

    @Override
    public void v_AddNewDonor() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - New Donor"));
    }

    @Override
    public void v_AddNewDonationCamp() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Add New Camp"));
    }

    @Override
    public void e_AdminChangePassword() {
        // Click on the "Change Password" link
        $("li#user").hover();
        $("li#user ul li:nth-child(3) a").click();
    }

    @Override
    public void v_Donors() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Donors"));
    }

    @Override
    public void e_ViewDonorProfile() {
        // Find all links containing "View" in their text
        ElementsCollection viewLinks = $$("a").filterBy(text("View"));

        // Check if there are any matching links
        int numLinks = viewLinks.size();
        if (numLinks > 0) {
            // Generate a random index and click on the link
            int randomIndex = (int) (Math.random() * numLinks);
            viewLinks.get(randomIndex).click();
        } else {
            // Handle case when no matching links are found
            System.out.println("No 'View' links found.");
        }
    }

    @Override
    public void e_EditDonationCamp() {
        // Find all links containing "Edit" in their text
        ElementsCollection editLinks = $$("a").filterBy(text("Edit"));

        // Check if there are any matching links
        int numLinks = editLinks.size();
        if (numLinks > 0) {
            // Generate a random index and click on the link
            int randomIndex = (int) (Math.random() * numLinks);
            editLinks.get(randomIndex).click();
        } else {
            // Handle case when no matching links are found
            System.out.println("No 'Edit' links found.");
        }
    }

    @Override
    public void v_AdminViewProfile() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - View Profile"));
    }

    @Override
    public void e_ViewDonationCamp() {
        // Find all links containing "View" in their text
        ElementsCollection viewLinks = $$("a").filterBy(text("View"));

        // Check if there are any matching links
        int numLinks = viewLinks.size();
        if (numLinks > 0) {
            // Generate a random index and click on the link
            int randomIndex = (int) (Math.random() * numLinks);
            viewLinks.get(randomIndex).click();
        } else {
            // Handle case when no matching links are found
            System.out.println("No 'View' links found.");
        }
    }

    @Override
    public void v_ViewDonationCamp() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - View Camp Details"));
    }

    @Override
    public void e_EditDonorProfile() {
        // Find all links containing "Edit" in their text
        ElementsCollection editLinks = $$("a").filterBy(text("Edit"));

        // Check if there are any matching links
        int numLinks = editLinks.size();
        if (numLinks > 0) {
            // Generate a random index and click on the link
            int randomIndex = (int) (Math.random() * numLinks);
            editLinks.get(randomIndex).click();
        } else {
            // Handle case when no matching links are found
            System.out.println("No 'Edit' links found.");
        }
    }

    @Override
    public void e_AddNewDonationCampSuccess() {
        fillDonationCamp();
        $(By.name("organizers")).clear();
        $(By.name("organizers")).sendKeys(faker.company().name());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void v_ViewDonations() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - View Donations"));
    }

    @Override
    public void v_DonationCamps() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - View Camps"));
    }

    @Override
    public void e_AdminEditProfileSuccess() {
        $(By.name("gender")).sendKeys(faker.options().option("M", "F"));
        $(By.name("email")).clear();
        $(By.name("email")).sendKeys(faker.internet().emailAddress());
        $(By.name("otherName")).clear();
        $(By.name("otherName")).sendKeys(faker.name().nameWithMiddle());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void e_EditDonationCampFailed() {
        $(By.name("organizers")).clear();
        $(By.name("details")).clear();
        $(By.name("details")).sendKeys(faker.lorem().sentence());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void e_AdminEditProfileFailed() {
        $(By.name("email")).clear();
        $(By.name("otherName")).clear();
        $(By.name("otherName")).sendKeys(faker.name().nameWithMiddle());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void e_AddNewDonationCamp() {
        $(By.linkText("Add New Donation Camp")).click();
    }

    @Override
    public void e_DeleteDonationCampSuccess() {
        // Find all links containing "Delete" in their text
        ElementsCollection deleteLinks = $$("a").filterBy(text("Delete"));

        // Check if there are any matching links
        int numLinks = deleteLinks.size();
        if (numLinks > 0) {
            // Generate a random index and click on the link
            int randomIndex = (int) (Math.random() * numLinks);
            deleteLinks.get(randomIndex).click();
        } else {
            // Handle case when no matching links are found
            System.out.println("No 'Delete' links found.");
        }
    }

    @Override
    public void e_ViewDonation() {
        // Find all links containing "View" in their text
        ElementsCollection viewLinks = $$("a").filterBy(text("View"));

        // Check if there are any matching links
        int numLinks = viewLinks.size();
        if (numLinks > 0) {
            // Generate a random index and click on the link
            int randomIndex = (int) (Math.random() * numLinks);
            viewLinks.get(randomIndex).click();
        } else {
            // Handle case when no matching links are found
            System.out.println("No 'View' links found.");
        }
    }

    @Override
    public void e_ViewDonations_nav() {
        $("a[href*='/view_donations']").click();
    }

    @Override
    public void e_EditDonorProfileFailed() {
        $(By.name("lastName")).clear();
        $(By.name("contact")).clear();
        $(By.name("contact")).sendKeys(faker.phoneNumber().cellPhone());
        $(By.name("email")).clear();
        $(By.name("email")).sendKeys(faker.internet().emailAddress());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void v_AdminHomepage() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Admin Dashboard"));
    }

    @Override
    public void v_ViewRequests() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - View Requests"));
    }

    @Override
    public void e_AddNewDonor() {
        $(By.linkText("Add New Donor")).click();
    }

    @Override
    public void e_EditDonorProfileSuccess() {
        $(By.name("lastName")).clear();
        $(By.name("lastName")).sendKeys(faker.name().lastName());
        $(By.name("contact")).clear();
        $(By.name("contact")).sendKeys(faker.phoneNumber().cellPhone());
        $(By.name("email")).clear();
        $(By.name("email")).sendKeys(faker.internet().emailAddress());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void e_DonationCamps_nav() {
        $("a[href*='/view_camps']").click();
    }

    @Override
    public void e_ViewRequest() {
        // Find all links containing "View" in their text
        ElementsCollection viewLinks = $$("a").filterBy(text("View"));

        // Check if there are any matching links
        int numLinks = viewLinks.size();
        if (numLinks > 0) {
            // Generate a random index and click on the link
            int randomIndex = (int) (Math.random() * numLinks);
            viewLinks.get(randomIndex).click();
        } else {
            // Handle case when no matching links are found
            System.out.println("No 'View' links found.");
        }
    }

    @Override
    public void e_ViewDonations() {
        Selenide.open("http://localhost:8080/Blood_Bank_App-1.0-SNAPSHOT/view_donations");
        /*executeJavaScript("document.getElementById('viewDonations').click();");*/
    }

    @Override
    public void e_AddNewDonationCampFailed() {
        fillDonationCamp();
        $("button[type=\"submit\"]").click();
    }

    private void fillDonorData() {
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
        $(By.name("bloodGroup")).sendKeys(faker.name().bloodGroup());
    }

    private void fillDonationCamp() {
        $(By.name("donationCampName")).clear();
        $(By.name("donationCampName")).sendKeys(faker.address().cityName());
        $(By.name("address")).clear();
        $(By.name("address")).sendKeys(faker.address().fullAddress());
        $(By.name("postalAddress")).clear();
        $(By.name("postalAddress")).sendKeys(faker.address().zipCode());
        $(By.name("details")).clear();
        $(By.name("details")).sendKeys(faker.lorem().sentence());
    }
}
