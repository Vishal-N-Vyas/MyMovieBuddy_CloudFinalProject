package com.srccodes.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConnectRequest {
	public long userId1;
	public long userId2;
	public String movieId;
	public String movieName;
	public String movieImage;



	public long zipCode;
	public String date;
	public String ajaxButton;
	public String requestStatus;
	public long requestCount;
	public String userName1	;
	public String userName2	;
	public String userImage1	;
	public String userImage2	;
	public String firstName;
	public String lastName;
	public String emailId;
	
 
	public ConnectRequest()
	{
	}
	
	

	public ConnectRequest(long userId1, long userId2, String movieId, String movieName, long zipCode, String date)
	{
		this.userId1 = userId1;
		this.userId2 = userId2;
		this.movieId = movieId;
		this.movieName = movieName;
		this.zipCode = zipCode; 
		this.date = date;
	}
	@JsonProperty
	public String getAjaxButton() {
		return ajaxButton;
	}

	public void setAjaxButton(String ajaxButton) {
		this.ajaxButton = ajaxButton;
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

	public void setRequestCount(long requestCount) {
		this.requestCount = requestCount;
	}
	
	@JsonProperty
	public long getUserId1() {
		return userId1;
	}
	
	@JsonProperty
	public long getUserId2() {
		return userId2;
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
	public String getUserName1() {
		return userName1;
	}



	public void setUserName1(String userName1) {
		this.userName1 = userName1;
	}



	@JsonProperty
	public String getUserName2() {
		return userName2;
	}



	public void setUserName2(String userName2) {
		this.userName2 = userName2;
	}


	@JsonProperty
	public String getUserImage1() {
		return userImage1;
	}



	public void setUserImage1(String userImage1) {
		this.userImage1 = userImage1;
	}


	@JsonProperty
	public String getUserImage2() {
		return userImage2;
	}



	public void setUserImage2(String userImage2) {
		this.userImage2 = userImage2;
	}


	@JsonProperty
	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@JsonProperty
	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@JsonProperty
	public String getEmailId() {
		return emailId;
	}



	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/*public String toString() {
		return userId + movieId + movieName + zipCode + date ;
	}
		*/
	
	@JsonProperty
	public String getMovieImage() {
		return movieImage;
	}



	public void setMovieImage(String movieImage) {
		this.movieImage = movieImage;
	}

	
		
}
