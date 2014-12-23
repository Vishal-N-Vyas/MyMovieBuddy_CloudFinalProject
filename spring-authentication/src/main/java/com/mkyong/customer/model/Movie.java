package com.mkyong.customer.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
	private String title;
	private String release_date;
	
	public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return release_date;
    }

}
