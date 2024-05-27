package com.brasens.requestbody;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = KeyRequest.class)
public class KeyRequest {
	private String key;
}
