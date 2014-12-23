package com.mkyong.customer.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Personality {
	private String name;
	private String popularity;
	
	public String getName(){
		return name;
	}
	
	public String getPopularity(){
		return popularity;
	}
}
