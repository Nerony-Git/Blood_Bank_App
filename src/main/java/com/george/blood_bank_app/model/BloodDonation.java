package com.george.blood_bank_app.model;

import java.time.LocalDate;

public class BloodDonation {
    private String donationID;
    private String donorID;
    private String camp;
    private String donationCampName;
    private LocalDate donationDate;
    private Integer bloodUnit;
    private String comment;


    public BloodDonation(){

    }

    public BloodDonation(String donationID, String donorID, String donationCamp, LocalDate donationDate, int bloodUnits, String comment) {
        super();
        this.donationID = donationID;
        this.donorID = donorID;
        this.camp = donationCamp;
        this.donationDate = donationDate;
        this.bloodUnit = bloodUnits;
        this.comment = comment;
    }

    public BloodDonation(String donationID, String donationCamp, LocalDate donationDate, int bloodUnit) {
        super();
        this.donationID = donationID;
        this.camp = donationCamp;
        this.donationDate = donationDate;
        this.bloodUnit = bloodUnit;
    }

    public BloodDonation(String donationID, String donorID, String donationCamp, String donationCampName, LocalDate donationDate, int bloodUnits, String comment) {
        super();
        this.donationID = donationID;
        this.donorID = donorID;
        this.camp = donationCamp;
        this.donationCampName = donationCampName;
        this.donationDate = donationDate;
        this.bloodUnit = bloodUnits;
        this.comment = comment;
    }

    public String getDonationID() {
        return donationID;
    }

    public void setDonationID(String donationID) {
        this.donationID = donationID;
    }

    public String getDonorID() {
        return donorID;
    }

    public void setDonorID(String donorID) {
        this.donorID = donorID;
    }

    public String getCamp() {
        return camp;
    }

    public void setCamp(String camp) {
        this.camp = camp;
    }

    public LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    public Integer getBloodUnit() {
        return bloodUnit;
    }

    public void setBloodUnit(Integer bloodUnit) {
        this.bloodUnit = bloodUnit;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDonationCampName() {
        return donationCampName;
    }

    public void setDonationCampName(String donationCampName) {
        this.donationCampName = donationCampName;
    }
}
