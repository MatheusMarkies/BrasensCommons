package com.brasens.response;

import java.time.ZonedDateTime;
import java.util.UUID;

public class ClientResponse {
	private String name;
	private String email;

	private String organization;
	private String role;

	public ClientResponse() {
	}

	public ClientResponse(String name, String email, String organization, String role) {
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
