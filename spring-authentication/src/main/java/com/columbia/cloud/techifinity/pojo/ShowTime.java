package com.columbia.cloud.techifinity.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShowTime {
	String dateTime;
	Theatre theatre;
	
	@JsonProperty
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	@JsonProperty
	public Theatre getTheatre() {
		return theatre;
	}
	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}
	
}
