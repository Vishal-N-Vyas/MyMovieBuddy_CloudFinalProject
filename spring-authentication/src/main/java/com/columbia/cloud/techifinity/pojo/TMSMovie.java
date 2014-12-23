package com.columbia.cloud.techifinity.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TMSMovie {
	String tmsId;
	String title;
	String subType;
	List<String> genres;
	ImageProps preferredImage;
	Integer releaseYear;
	String entityType;
	List<ShowTime> showtimes;
	List<String> topCast;
	
	@JsonProperty
	public List<String> getTopCast() {
		return topCast;
	}
	public void setTopCast(List<String> topCast) {
		this.topCast = topCast;
	}
	@JsonProperty
	public List<ShowTime> getShowtimes() {
		return showtimes;
	}
	public void setShowtimes(List<ShowTime> showtimes) {
		this.showtimes = showtimes;
	}
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
	public List<String> getGenres() {
		return genres;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	
	@JsonProperty
	public ImageProps getPreferredImage() {
		return preferredImage;
	}
	public void setPreferredImage(ImageProps preferredImage) {
		this.preferredImage = preferredImage;
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
	
}
