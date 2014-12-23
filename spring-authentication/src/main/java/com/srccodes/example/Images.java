package com.srccodes.example;

import java.util.HashMap;
import java.util.Map;

// Since the free TMS API key does not give access to the movie images, we need to store the 
// images in our file system , ideally it should be stored in the database

public class Images {

	static Map<String, String> movieImages;
	static {
		movieImages = new HashMap<String, String>();
		movieImages.put("MV006424530000","./resources/images/movie/1.jpg");
		movieImages.put("MV001053970000","./resources/images/movie/2.jpg");
		movieImages.put("MV000088910000","./resources/images/movie/3.jpg");
		movieImages.put("MV006694850000","./resources/images/movie/4.jpg");
		movieImages.put("MV006137870000","./resources/images/movie/5.jpg");
		movieImages.put("MV006837970000","./resources/images/movie/6.jpg");
		movieImages.put("MV005522110000","./resources/images/movie/7.jpg");
		movieImages.put("MV005521240000","./resources/images/movie/8.jpg");
		movieImages.put("MV005689500000","./resources/images/movie/9.jpg");
		movieImages.put("EV000002946020","./resources/images/movie/10.jpg");
		movieImages.put("MV000071990000","./resources/images/movie/11.jpg");
		movieImages.put("MV006335870000","./resources/images/movie/12.jpg");
		movieImages.put("MV005341090000","./resources/images/movie/13.jpg");
		movieImages.put("MV005398650000","./resources/images/movie/14.jpg");
		movieImages.put("MV005506520000","./resources/images/movie/15.jpg");
		movieImages.put("MV005492710000","./resources/images/movie/16.jpg");
		movieImages.put("MV006178670000","./resources/images/movie/17.jpg");
		movieImages.put("MV006849750000","./resources/images/movie/18.jpg");
		movieImages.put("MV006634330000","./resources/images/movie/19.jpg");
		movieImages.put("MV000043840000","./resources/images/movie/20.jpg");
		movieImages.put("MV000815330000","./resources/images/movie/21.jpg");
		movieImages.put("MV005440720000","./resources/images/movie/22.jpg");
		movieImages.put("MV005975070000","./resources/images/movie/23.jpg");
	}
	
	public static Map<String, String> getMovieImages()
	{
		return movieImages;
	}
	
}
