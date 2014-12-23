package com.columbia.cloud.techifinity.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dummy {
String name;
String hobby;
String fav;

@JsonProperty
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@JsonProperty
public String getHobby() {
	return hobby;
}
public void setHobby(String hobby) {
	this.hobby = hobby;
}
@JsonProperty
public String getFav() {
	return fav;
}
public void setFav(String fav) {
	this.fav = fav;
}


}
