package com.george.blood_bank_app.model;

import java.time.LocalDate;

public class BloodRequest {
    private String requestID;
    private String donorID;
    private String bloodGroup;
    private LocalDate requestDate;
    private String comment;


    public BloodRequest(){

    }
    public BloodRequest(String requestID, String donorID, String bloodGroup, LocalDate requestDate, String comment) {
        super();
        this.requestID = requestID;
        this.donorID = donorID;
        this.bloodGroup = bloodGroup;
        this.requestDate = requestDate;
        this.comment = comment;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getDonorID() {
        return donorID;
    }

    public void setDonorID(String donorID) {
        this.donorID = donorID;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
