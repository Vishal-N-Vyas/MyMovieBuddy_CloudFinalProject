package com.mkyong.customer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.mkyong.customer.model.Movie;
import com.mkyong.customer.model.MovieResult;
import com.mkyong.customer.model.Personality;
import com.mkyong.customer.model.PersonalityResult;
import com.mkyong.customer.model.SaveResult;
import com.mkyong.customer.model.SelectItem;
import com.mkyong.db.DatabaseWrapper;
import java.util.StringTokenizer; 
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/buildProfile", method = RequestMethod.GET)
	public String buildProfile(@RequestParam(value="name", required=false, defaultValue="") String name, @RequestParam(value="person", required=false, defaultValue="") String person, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		if(name.equalsIgnoreCase("") != true){
			MovieResult movieResult = restTemplate.getForObject("https://api.themoviedb.org/3/search/movie?query="+ name +"&api_key=XXXX&page=1", MovieResult.class);
			
			model.addAttribute("name", name);
	
			Movie[] allData = movieResult.getResults();
			model.addAttribute("results", allData);
		}
		
		if(person.equalsIgnoreCase("") != true){
			PersonalityResult personResult = restTemplate.getForObject("https://api.themoviedb.org/3/search/person?query="+ person +"&api_key=XXXX&page=1", PersonalityResult.class);
			
			model.addAttribute("person", person);
	
			Personality[] allPeople = personResult.getResults();
			model.addAttribute("personalities", allPeople);
		}
		
		return "buildProfile";
	}
	
	@RequestMapping(value = "/doneBuildProfile", method = RequestMethod.POST)
	public String doneBuildProfile(@RequestParam(value="name", required=false, defaultValue="") String name, @RequestParam(value="person", required=false, defaultValue="") String person, Model model) {		
		return "welcome";
	}
	
	@RequestMapping(value = "/searchMovie", method = RequestMethod.GET)
	public @ResponseBody List<SelectItem> searchMovie(@RequestParam(value="q", required=false, defaultValue="") String q, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		
		List<SelectItem> itemList = new ArrayList<SelectItem>();

		if(q.equalsIgnoreCase("") != true){
			MovieResult movieResult = restTemplate.getForObject("https://api.themoviedb.org/3/search/movie?query="+ q +"&api_key=XXXX&page=1", MovieResult.class);
	
			Movie[] allData = movieResult.getResults();
			
			for(int i=0; i < allData.length; i++){
				SelectItem item = new SelectItem();
				//item.setId(i+1);
				item.setId(allData[i].getTitle());
				item.setText(allData[i].getTitle());
				itemList.add(item);
			}
		}
		
		return itemList;
	}
	
	@RequestMapping(value = "/searchPersonality", method = RequestMethod.GET)
	public @ResponseBody List<SelectItem> searchPersonality(@RequestParam(value="q", required=false, defaultValue="") String q, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		
		List<SelectItem> itemList = new ArrayList<SelectItem>();

		if(q.equalsIgnoreCase("") != true){
			PersonalityResult personResult = restTemplate.getForObject("https://api.themoviedb.org/3/search/person?query="+ q +"&api_key=XXXX&page=1", PersonalityResult.class);
			
			Personality[] allPeople = personResult.getResults();
			
			for(int i=0; i < allPeople.length; i++){
				SelectItem item = new SelectItem();
				item.setId(allPeople[i].getName());
				item.setText(allPeople[i].getName());
				itemList.add(item);
			}
		}
		
		return itemList;
	}
	
	@RequestMapping(value = "/saveFavMovies", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	public @ResponseBody SaveResult saveFavMovies(@RequestBody String favMovies, Model model, HttpSession session) {
		SaveResult saveResult = new SaveResult();
		System.out.println("Save Fav movies = " + favMovies);
		saveResult.setSuccessMsg("Favorite movies saved");
		//String[] favP = favMovies.split("^^*^^");
		StringTokenizer st = new StringTokenizer(favMovies,"^^*^^");
		List<String> l = new ArrayList<String>();
		while(st.hasMoreTokens()) { 
			String key = st.nextToken(); 
			l.add(key);
		}
		String[] favP = l.toArray(new String[0]);
		/*for(int i=0;i<favP.length;i++) {
			System.out.println(favP[i]);
		}*/
		DatabaseWrapper.insertCustomerProfile((String)session.getAttribute("userName"), "movie", favP);
		//saveResult.setListSaved(favMovies);
		
		return saveResult;
	}
	
	@RequestMapping(value = "/saveFavPersonalities", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	public @ResponseBody SaveResult saveFavPersonalities(@RequestBody String favPersonalities, Model model, HttpSession session) {
		SaveResult saveResult = new SaveResult();
		System.out.println("Save Fav Personalities = " + favPersonalities);
		//String[] favP = favPersonalities.split("^^*^^");
		StringTokenizer st = new StringTokenizer(favPersonalities,"^^*^^");
		List<String> l = new ArrayList<String>();
		while(st.hasMoreTokens()) { 
			String key = st.nextToken(); 
			l.add(key);
		}
		String[] favP = l.toArray(new String[0]);
		DatabaseWrapper.insertCustomerProfile((String)session.getAttribute("userName"), "people", favP);
		saveResult.setSuccessMsg("Favorite movies saved");
		
		//saveResult.setListSaved(favMovies);
		
		return saveResult;
	}
	
	@RequestMapping(value = "/saveFavGenres", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	public @ResponseBody SaveResult saveFavGenres(@RequestBody String favGenres, Model model, HttpSession session) {
		SaveResult saveResult = new SaveResult();
		System.out.println("Save Fav movies = " + favGenres);
		saveResult.setSuccessMsg("Favorite movies saved");
		//String[] favP = favGenres.split("^^*^^");
		StringTokenizer st = new StringTokenizer(favGenres,"^^*^^");
		List<String> l = new ArrayList<String>();
		while(st.hasMoreTokens()) { 
			String key = st.nextToken(); 
			l.add(key);
		}
		String[] favP = l.toArray(new String[0]);
		
		DatabaseWrapper.insertCustomerProfile((String)session.getAttribute("userName"), "genre", favP);
		//saveResult.setListSaved(favMovies);
		
		return saveResult;
	}

	@RequestMapping(value = "/favMovie", method = RequestMethod.POST)
	public String favMovie(@RequestParam(value="name", required=false, defaultValue="Interstellar") String name, @RequestParam(value="favMovie", required=false, defaultValue="") List<String> favMovie, Model model) {
		model.addAttribute("favMovie", favMovie);
		System.out.println("Fav movies = " + favMovie);
		RestTemplate restTemplate = new RestTemplate();
		MovieResult movieResult = restTemplate.getForObject("https://api.themoviedb.org/3/search/movie?query="+ name +"&api_key=XXXXX&page=1", MovieResult.class);
		
		model.addAttribute("name", name);
		
		Movie[] allData = movieResult.getResults();
		model.addAttribute("results", allData);
		
		return "movie";
	}
	
}
