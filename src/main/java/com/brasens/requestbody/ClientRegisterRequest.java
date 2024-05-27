package com.brasens.requestbody;

import com.brasens.dtos.ClientModel;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = ClientRegisterRequest.class)
public class ClientRegisterRequest {
	private String name;
	private String email;
	private String organization;
	private String role;

	@Override
	public String toString() {
		return "ClientRequest [name=" + name + ", email=" + email + "]";
	}

	public ClientModel requestToClient(String password) {
		ClientModel client = new ClientModel();
		
		client.setLogin(this.name);
		client.setEmail(this.email);

		client.setPassword(password);

		return client;
	}
	
}
