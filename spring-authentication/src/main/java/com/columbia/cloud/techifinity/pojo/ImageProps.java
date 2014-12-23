package com.columbia.cloud.techifinity.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageProps {
	String uri;

	@JsonProperty
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
}
