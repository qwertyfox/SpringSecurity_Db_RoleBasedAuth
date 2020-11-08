package com.qwertyfox.thefox.model;

import javax.persistence.*;

@Entity(name= "User")
public class FoxUserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userID;
    private String first_name;
    private String last_name;
    private Integer contactNo;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getContactNo() {
        return contactNo;
    }

    public void setContactNo(Integer contactNo) {
        this.contactNo = contactNo;
    }
}
