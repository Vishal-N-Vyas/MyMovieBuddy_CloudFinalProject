package com.srccodes.example;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMovieSelection {

	public long userId;
	public String userName;
	public String movieId;
	public String movieName;
	public String movieImage;
	public long zipCode;
	public String date;
	public String ajaxButton;
	public String requestStatus;
	public long requestCount;
	public int rank;

	public UserMovieSelection()
	{
	}
	
	
	public void setRequestCount(long requestCount) {
		this.requestCount = requestCount;
	}

	public UserMovieSelection(long userId, String movieId, String movieName, long zipCode, String date)
	{
		this.userId = userId;
		this.movieId = movieId;
		this.movieName = movieName;
		this.zipCode = zipCode; 
		this.date = date;
	}
	
	
	@JsonProperty
	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	
	@JsonProperty
	public long getRequestCount() {
		return requestCount;
	}

	@JsonProperty
	public String getAjaxButton() {
		return ajaxButton;
	}
	public void setAjaxButton(String ajaxButton) {
		this.ajaxButton = ajaxButton;
	}
	
	@JsonProperty
	public long getUserId() {
		return userId;
	}
	@JsonProperty
	public String getMovieId() {
		return movieId;
	}
	@JsonProperty
	public String getMovieName() {
		return movieName;
	}
	@JsonProperty
	public long getZipCode() {
		return zipCode;
	}
	@JsonProperty
	public String getDate() {
		return date;
	}

	@JsonProperty
	public String getMovieImage() {
		return movieImage;
	}


	public void setMovieImage(String movieImage) {
		this.movieImage = movieImage;
	}

	@JsonProperty
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonProperty
	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}

	/*public String toString() {
		return userId + movieId + movieName + zipCode + date ;
	}
		*/
	
	
		
}
