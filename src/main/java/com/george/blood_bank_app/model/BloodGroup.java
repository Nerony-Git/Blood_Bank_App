package com.george.blood_bank_app.model;

public class BloodGroup {
    private String bloodGroupID;
    private String bloodGroupName;

    public BloodGroup(){

    }

    public BloodGroup(String bloodGroupName){
        this.bloodGroupName = bloodGroupName;
    }

    public String getBloodGroupID(){
        return bloodGroupID;
    }

    public void setBloodGroupID(String bloodGroupID){
        this.bloodGroupID = bloodGroupID;
    }

    public String getBloodGroupName(){
        return bloodGroupName;
    }

    public void setBloodGroupName(String bloodGroupName){
        this.bloodGroupName = bloodGroupName;
    }

}
