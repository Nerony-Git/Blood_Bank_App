package com.george.blood_bank_app.modelImpl;

import com.codeborne.selenide.ElementsCollection;
import com.george.blood_bank_app.AdminPage;
import com.github.javafaker.Faker;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Random;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@GraphWalker(value = "random(edge_coverage(100))")
public class AdminPageTest extends ExecutionContext implements AdminPage {

    Faker faker = new Faker();

    @Override
    public void e_NewRequest() {
        $(By.id("#newRequests a")).click();
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
    }

    @Override
    public void e_ViewRequests() {
        $(By.id("#viewRequests a")).click();
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
        ElementsCollection processLinks = $$("a:contains('Process')");
        int numLinks = processLinks.size();
        if (numLinks > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(numLinks);
            processLinks.get(randomIndex).click();
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
        $(By.id("#viewDonors a")).click();
    }

    @Override
    public void e_EditDonation() {
        ElementsCollection editLinks = $$("a:contains('Edit')");
        int numLinks = editLinks.size();
        if (numLinks > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(numLinks);
            editLinks.get(randomIndex).click();
        }
    }

    @Override
    public void e_AddNewDonorSuccess() {
        fillDonorData();
        $(By.name("bloodGroup")).clear();
        $(By.name("bloodGroup")).sendKeys(faker.name().bloodGroup());
        $(By.name("password")).clear();
        $(By.name("password")).sendKeys(faker.internet().password());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void v_EditDonationCamp() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Edit Camp Details"));
    }

    @Override
    public void e_DonationCamps() {
        $(By.id("#viewCamps a")).click();
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
        ElementsCollection viewLinks = $$("a:contains('View')");
        int numLinks = viewLinks.size();
        if (numLinks > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(numLinks);
            viewLinks.get(randomIndex).click();
        }
    }

    @Override
    public void e_EditDonationCamp() {
        ElementsCollection editLinks = $$("a:contains('Edit')");
        int numLinks = editLinks.size();
        if (numLinks > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(numLinks);
            editLinks.get(randomIndex).click();
        }
    }

    @Override
    public void v_AdminViewProfile() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - View Profile"));
    }

    @Override
    public void e_ViewDonationCamp() {
        ElementsCollection viewLinks = $$("a:contains('View')");
        int numLinks = viewLinks.size();
        if (numLinks > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(numLinks);
            viewLinks.get(randomIndex).click();
        }
    }

    @Override
    public void v_ViewDonationCamp() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - View Camp Details"));
    }

    @Override
    public void e_EditDonorProfile() {
        ElementsCollection editLinks = $$("a:contains('Edit')");
        int numLinks = editLinks.size();
        if (numLinks > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(numLinks);
            editLinks.get(randomIndex).click();
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
        $(By.name("gender")).clear();
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
        $(By.name("gender")).clear();
        $(By.name("email")).clear();
        $(By.name("email")).sendKeys(faker.internet().emailAddress());
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
        ElementsCollection deleteLinks = $$("a:contains('Delete')");
        int numLinks = deleteLinks.size();
        if (numLinks > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(numLinks);
            deleteLinks.get(randomIndex).click();
        }
    }

    @Override
    public void e_ViewDonation() {
        ElementsCollection viewLinks = $$("a:contains('View')");
        int numLinks = viewLinks.size();
        if (numLinks > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(numLinks);
            viewLinks.get(randomIndex).click();
        }
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
    public void e_ViewRequest() {
        ElementsCollection viewLinks = $$("a:contains('View')");
        int numLinks = viewLinks.size();
        if (numLinks > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(numLinks);
            viewLinks.get(randomIndex).click();
        }
    }

    @Override
    public void e_ViewDonations() {
        $(By.id("#viewDonations a")).click();
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
        $(By.name("gender")).clear();
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

        Select bloodGroup = new Select($(By.name("bloodGroup")));
        int numBloodGroup = bloodGroup.getOptions().size();
        int randomIndex = faker.random().nextInt(numBloodGroup);
        bloodGroup.selectByIndex(randomIndex);
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
