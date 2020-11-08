package com.qwertyfox.thefox.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user_details")
public class FoxUsersRolesAndDetails {

    @Id
    private int userID;
    private String first_name;
    private String last_name;
    private int contact_no;
    private String role_name;

    public int getUserID() {
        return userID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getContact_no() {
        return contact_no;
    }

    public String getRole_name() {
        return role_name;
    }
}
