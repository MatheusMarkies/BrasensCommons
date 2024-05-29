package com.brasens.requestbody;

import java.io.Serializable;

public class RequestBudget implements Serializable {

    String name;
    String organization;
    String email;
    String number;
    String role;

    public RequestBudget() {
    }

    public RequestBudget(String name, String organization, String email, String number, String role) {
        this.name = name;
        this.organization = organization;
        this.email = email;
        this.number = number;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}