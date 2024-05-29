package com.brasens.requestbody;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = KeyRequest.class)
public class KeyRequest {
	private String key;

	public KeyRequest() {
	}

	public KeyRequest(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
