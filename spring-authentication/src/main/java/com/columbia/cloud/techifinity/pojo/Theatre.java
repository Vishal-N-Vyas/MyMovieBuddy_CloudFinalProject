package com.columbia.cloud.techifinity.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Theatre {
	String id;
	String name;
	//address object
	@JsonProperty
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonProperty
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
