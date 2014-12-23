package com.columbia.cloud.techifinity.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.columbia.cloud.techifinity.pojo.TMSMovie;
import com.google.gson.Gson;

public class NetClientPost {

	// http://localhost:8080/RESTfulExample/json/product/post
	public static void main(String[] args) {

		try {

			URL url = new URL(
					"http://data.tmsapi.com/v1/movies/showings?startDate=2014-12-20&api_key=XXXXXX&zip=10025");
			//http://localhost:8080/RESTfulExample/json/product/post");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(false);
			conn.setRequestMethod("GET");//POST
			conn.setRequestProperty("Content-Type", "application/json");

			String input = "{\"qty\":100,\"name\":\"iPad 4\"}";

			//OutputStream os = conn.getOutputStream();
			//os.write(input.getBytes());
			//os.flush();

			/*if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode()
						+ conn.getResponseMessage()
						);
			}*/

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
				  // We read the json string now and recreate the AlbumsWithInnerClass class
		        Gson gson3 = new Gson();
         
		        TMSMovie[] movies = gson3.fromJson(output, TMSMovie[].class);
		        System.out.println("Length = " + movies.length);
		        
		        System.out.println(movies[0].getTitle());
		        
	 
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}