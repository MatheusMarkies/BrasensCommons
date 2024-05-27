package com.brasens.response;

import java.util.ArrayList;
import java.util.List;
public class RoleResponse {
    private String role;
    private String color;
    private List<String> privileges = new ArrayList<>();

    public RoleResponse() {
    }

    public RoleResponse(String role, String color, List<String> privileges) {
        this.role = role;
        this.color = color;
        this.privileges = privileges;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }
}
