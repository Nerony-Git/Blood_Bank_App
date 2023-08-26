package com.george.blood_bank_app.modelImpl;

import com.codeborne.selenide.ElementsCollection;
import com.george.blood_bank_app.DonorPage;
import com.github.javafaker.Faker;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;

@GraphWalker(value = "random(edge_coverage(100))")
public class DonorPageTest extends ExecutionContext implements DonorPage {

    Faker faker = new Faker();

    @Override
    public void v_DonorViewProfile() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - View Profile"));
    };

    @Override
    public void e_DonorViewRequest() {
        ElementsCollection viewLinks = $$("a:contains('View')");
        int numLinks = viewLinks.size();
        if (numLinks > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(numLinks);
            viewLinks.get(randomIndex).click();
        }
    }

    @Override
    public void v_DonorChangePassword() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Change Password"));
    }

    @Override
    public void v_DonorViewDonation() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - View Blood Donation Details"));
    }

    @Override
    public void e_DonorEditProfileFailed() {
        $(By.name("gender")).clear();
        $(By.name("email")).clear();
        $(By.name("email")).sendKeys(faker.internet().emailAddress());
        $(By.name("otherName")).clear();
        $(By.name("otherName")).sendKeys(faker.name().nameWithMiddle());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void v_DonorHomepage() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - User Dashboard"));
    }

    @Override
    public void e_DonorViewDonation() {
        ElementsCollection viewLinks = $$("a:contains('View')");
        int numLinks = viewLinks.size();
        if (numLinks > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(numLinks);
            viewLinks.get(randomIndex).click();
        }
    }

    @Override
    public void v_DonorBloodRequests() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Request List"));
    }

    @Override
    public void e_DonorHomepage() {
        // Click on the "Homepage" link
        $(By.cssSelector("li#home a")).click();
    }

    @Override
    public void e_DonorChangePassword() {
        // Click on the "Change Password" link
        $("li#user").hover();
        $("li#user ul li:nth-child(3) a").click();
    }

    @Override
    public void e_DonorDonateBloodFailed() {
        donateBlood();
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void e_DonorEditProfile() {
        // Click on the "Edit Profile" link
        $("li#user").hover();
        $("li#user ul li:nth-child(2) a").click();
    }

    @Override
    public void v_DonorEditProfile() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Edit Profile"));
    }

    @Override
    public void v_DonorBloodDonations() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Donation List"));
    }

    @Override
    public void e_DonorRequestBlood() {
        // Locate the "View Request" link by its parent div and the link text
/*        WebElement viewRequestLink = driver.findElement(By.xpath("//div[@id='viewRequest']//a[contains(text(), 'View Request')]"));*/
        executeJavaScript("document.getElementById('requestBlood').click();");
    }

    @Override
    public void e_DonorBloodDonations() {
        executeJavaScript("document.getElementById('viewDonation').click();");
    }

    @Override
    public void e_DonorEditProfileSuccess() {
        $(By.name("gender")).clear();
        $(By.name("gender")).sendKeys(faker.options().option("M", "F"));
        $(By.name("email")).clear();
        $(By.name("email")).sendKeys(faker.internet().emailAddress());
        $(By.name("otherName")).clear();
        $(By.name("otherName")).sendKeys(faker.name().nameWithMiddle());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void v_DonorViewRequest() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - View Request Details"));
    }

    @Override
    public void e_DonorRequestBloodFailed() {
        requestBlood();
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void e_DonorDonateBloodSuccess() {
        donateBlood();
        $(By.name("bloodUnit")).clear();
        $(By.name("bloodUnit")).sendKeys(faker.number().digit());
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void e_DonorViewProfile() {
        // Click on the "View Profile" link
        $("li#user").hover();
        $("li#user ul li:nth-child(1) a").click();
    }

    @Override
    public void v_DonorDonateBlood() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Donate Blood"));
    }

    @Override
    public void v_DonorRequestBlood() {
        // Verify the page title
        $("title").shouldHave(attribute("text", "Blood Bank - Request Blood"));
    }

    @Override
    public void e_DonorDonateBlood() {
        executeJavaScript("document.getElementById('bloodDonation').click();");
    }

    @Override
    public void e_DonorRequestBloodSuccess() {
        requestBlood();

        // Generate a random future date
        LocalDate futureDate = faker.date().future(7, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String formattedFutureDate = futureDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        $(By.name("requestDate")).sendKeys(formattedFutureDate);
        $("button[type=\"submit\"]").click();
    }

    @Override
    public void e_DonorBloodRequests() {
        executeJavaScript("document.getElementById('viewRequest').click();");
    }

    public void donateBlood() {
        Select donationCamp = new Select($(By.name("donationCamp")));
        int numDonationCamp = donationCamp.getOptions().size();
        int randomIndex = faker.random().nextInt(numDonationCamp);
        donationCamp.selectByIndex(randomIndex);

        // Generate a random future date
        LocalDate futureDate = faker.date().future(14, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String formattedFutureDate = futureDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        $(By.name("donationDate")).sendKeys(formattedFutureDate);
        $(By.name("comment")).sendKeys(faker.lorem().sentence());
    }

    public void requestBlood() {
        Select bloodGroup = new Select($(By.name("bloodGroup")));
        int numBloodGroup = bloodGroup.getOptions().size();
        int randomIndex = faker.random().nextInt(numBloodGroup);
        bloodGroup.selectByIndex(randomIndex);
        $(By.name("comment")).sendKeys(faker.lorem().sentence());
    }
}
