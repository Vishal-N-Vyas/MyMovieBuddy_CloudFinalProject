package com.mkyong.customer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mkyong.customer.model.Customer;
import com.mkyong.customer.model.User;
import com.mkyong.db.DatabaseWrapper;

@Controller
public class MovieController {

	@RequestMapping(value = { "/", "/welcome**", "/index**" }, method = RequestMethod.GET)
	public String defaultPage(ModelMap model) {
		model.addAttribute("movie", "Casino Royale");
		System.out.println("New call executed");
		return "index";

	}
	
	@RequestMapping(value = { "/login**"},method = RequestMethod.POST)
	public ModelAndView processSubmit(
			@ModelAttribute("user") User user,
			BindingResult result, SessionStatus status, HttpSession session) {
		System.out.println("Login called");
		ModelAndView model = new ModelAndView();
		session.setAttribute("userName", user.getUserName());
		 long userId=com.srccodes.example.DatabaseWrapper.getUserNameToId( user.getUserName());
		 session.setAttribute("userId", userId);
		if (!DatabaseWrapper.authenticateUser(user)) {
			//if validator failed
			model.addObject("error", "Invalid username or password!");
			model.setViewName("CustomerFail");
			System.out.println("Not authenticated");
			return model;
		}
		
		//store the info in the database
		System.out.println("Reached here");
		status.setComplete();
		model.addObject("userName", user.getUserName());
		model.addObject("imgURL", "resources/photos/" + user.getUserName()+".jpg");
		model.setViewName("welcome");
		return model;
	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String getMovie( ModelMap model) {
		return "welcome";
	}
	
	@RequestMapping(value="/logoutuser**", method = RequestMethod.GET)
	public String logoutUser(ModelMap model) {
		model.addAttribute("movie", "Test");
		System.out.println("Log out called");
		return "logout";
	}
	/*
	@RequestMapping(value="/{name}", method = RequestMethod.GET)
	public String getMovie(@PathVariable String name, ModelMap model) {

		model.addAttribute("movie", name);
		return "list";

	}
	*/
	
}