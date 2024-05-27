package com.brasens.requestbody;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = ClientRequest.class)
public class ClientRequest {
    private String email;
    private String password;
}
