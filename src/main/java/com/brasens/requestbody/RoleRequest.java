package com.brasens.requestbody;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(as = RoleRequest.class)
public class RoleRequest implements Serializable {
    public String role;
    public List<String> privileges;

    public RoleRequest(String role){
        this.role = role;
    }

    public RoleRequest(String role, List<String> privileges) {
        this.role = role;
        this.privileges = privileges;
    }

    public static RoleRequest getDefaultValue(){
        List<String> privileges = new ArrayList<>();
        privileges.add("");
        return new RoleRequest("Colaborador", privileges);
    }
}
