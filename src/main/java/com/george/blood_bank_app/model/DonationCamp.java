package com.george.blood_bank_app.model;

public class DonationCamp {
    private String campID;
    private String campName;
    private String organizers;
    private String address;
    private String postal_address;
    private String details;
    private String deleted;


    public DonationCamp() {

    }

    public DonationCamp(String campID, String campName, String organizers, String address, String postalAddress, String details) {
        super();
        this.campID = campID;
        this.campName = campName;
        this.organizers = organizers;
        this.address = address;
        this.postal_address = postalAddress;
        this.details = details;
    }

    public String getCampID() {
        return campID;
    }

    public void setCampID(String campID) {
        this.campID = campID;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getOrganizers() {
        return organizers;
    }

    public void setOrganizers(String organizers) {
        this.organizers = organizers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal_address() {
        return postal_address;
    }

    public void setPostal_address(String postal_address) {
        this.postal_address = postal_address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}
