package com.mkyong.customer.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResult {
	private Movie[] results;
	
	public Movie[] getResults(){
		return results;
	}
}
