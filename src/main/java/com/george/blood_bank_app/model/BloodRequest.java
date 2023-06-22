package com.george.blood_bank_app.model;

import java.time.LocalDate;

public class BloodRequest {
    private String requestID;
    private String donorID;
    private String bloodGroup;
    private LocalDate requestDate;
    private String comment;
    private String status;
    private String response;


    public BloodRequest(){

    }
    public BloodRequest(String requestID, String donorID, String bloodGroup, LocalDate requestDate, String comment, String status) {
        super();
        this.requestID = requestID;
        this.donorID = donorID;
        this.bloodGroup = bloodGroup;
        this.requestDate = requestDate;
        this.comment = comment;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
