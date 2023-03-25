package com.developersstack.medehelpx.entity;

import com.developersstack.medehelpx.enums.GenderType;

public class Doctor {
    private String fName;
    private String lName;
    private String nic;
    private String contact;
    private String email;
    private String specialization;
    private String address;
    private GenderType genderType;

    public Doctor() {
    }

    public Doctor(String fName, String lName, String nic, String contact, String email, String specialization, String address, GenderType genderType) {
        this.fName = fName;
        this.lName = lName;
        this.nic = nic;
        this.contact = contact;
        this.email = email;
        this.specialization = specialization;
        this.address = address;
        this.genderType = genderType;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }
}
