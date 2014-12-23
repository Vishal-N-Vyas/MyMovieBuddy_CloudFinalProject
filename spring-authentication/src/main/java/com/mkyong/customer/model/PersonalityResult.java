package com.mkyong.customer.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.mkyong.customer.model.Personality;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalityResult {
	private Personality[] results;
	
	public Personality[] getResults(){
		return results;
	}
}
