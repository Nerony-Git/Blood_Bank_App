package com.george.blood_bank_app.model;

import java.time.LocalDate;

public class User {
    private String donorID;
    private String firstName;
    private String lastName;
    private String otherName;
    private String username;
    private String gender;
    private LocalDate dob;
    private String contact;
    private String address;
    private String postalAddress;
    private String email;
    private String password;
    private String bloodGroup;
    private String deleted;

    public User(){
        
    }

    public User(String donorID, String firstName, String lastName, String otherName, String gender,
            LocalDate dob, String contact, String email, String address, String postalAddress,
            String bloodGroup) {
                super();
                this.donorID = donorID;
                this.firstName = firstName;
                this.lastName = lastName;
                this.otherName = otherName;
                this.gender = gender;
                this.dob = dob;
                this.contact = contact;
                this.email = email;
                this.address = address;
                this.postalAddress = postalAddress;
                this.bloodGroup = bloodGroup;
    }

    public User(String donorID, String firstName, String lastName, String otherName, String username, String gender, LocalDate dob, String contact, String email, String address, String postalAddress, String bloodGroup) {
        super();
        this.donorID = donorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.username = username;
        this.gender = gender;
        this.dob = dob;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.postalAddress = postalAddress;
        this.bloodGroup = bloodGroup;
    }

    public String getDonorID() {
        return donorID;
    }

    public void setDonorID(String donorID) {
        this.donorID = donorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}
