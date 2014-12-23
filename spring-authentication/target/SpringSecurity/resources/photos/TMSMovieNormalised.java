package com.columbia.cloud.techifinity.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TMSMovieNormalised {
	String tmsId;
	String title;
	String subType;
	Integer releaseYear;
	String entityType;
	String movieImage;
	String theatresList;
	String genresList;
	String castList;
	String ajaxButton;
	
	String youTubeLink;
	
	@JsonProperty
	public String getTmsId() {
		return tmsId;
	}
	public void setTmsId(String tmsId) {
		this.tmsId = tmsId;
	}
	
	@JsonProperty
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@JsonProperty
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	 
	
 
	@JsonProperty
	public String getMovieImage() {
		return movieImage;
	}
	public void setMovieImage(String movieImage) {
		this.movieImage = movieImage;
	}
	
	@JsonProperty
	public String getTheatresList() {
		return theatresList;
	}
	public void setTheatresList(String theatresList) {
		this.theatresList = theatresList;
	}
	
	@JsonProperty
	public String getGenresList() {
		return genresList;
	}
	public void setGenresList(String genresList) {
		this.genresList = genresList;
	}
	
	@JsonProperty
	public String getCastList() {
		return castList;
	}
	public void setCastList(String castList) {
		this.castList = castList;
	}
	
	@JsonProperty
	public String getAjaxButton() {
		return ajaxButton;
	}
	public void setAjaxButton(String ajaxButton) {
		this.ajaxButton = ajaxButton;
	}
	
	@JsonProperty
	public Integer getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	@JsonProperty
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	
	@JsonProperty
	public String getYouTubeLink() {
		return youTubeLink;
	}
	
	
	public void setYouTubeLink(String youTubeLink) {
		this.youTubeLink = youTubeLink;
	}
	
}
