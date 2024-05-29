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

	public ClientRegisterRequest() {
	}

	public ClientRegisterRequest(String name, String email, String organization, String role) {
		this.name = name;
		this.email = email;
		this.organization = organization;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
