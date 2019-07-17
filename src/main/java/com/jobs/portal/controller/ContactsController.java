package com.jobs.portal.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jobs.portal.model.User;
import com.jobs.portal.model.UserContact;
import com.jobs.portal.service.UserContactService;
import com.jobs.portal.service.UserService;

@Controller
public class ContactsController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserContactService userContactService;
	
	@RequestMapping("/myContacts")
	public String viewMyContacts(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		List<UserContact> myContacts =  userContactService.findMyContacts(user.getUserId());
		
		model.addAttribute("myContacts", myContacts);
		
		return "myContacts";
	}
	
	@RequestMapping("/myContacts/contactDetails/{userId}")
	public String viewSingleContactDetails(@PathVariable("userId") Long userId, Model model) {
		
		UserContact userContact = userContactService.findContactDetails(userId);
		
		model.addAttribute("userContact", userContact);
		
		return "contactDetails";
		
	}
	
	 @RequestMapping("/myContacts/{userId}/chat/")
	 public String startChat(@PathVariable("userId") Long userId, Principal principal, Model model) {
	    String name = principal.getName();
	    UserContact userContact = userContactService.findContactDetails(userId);
	    model.addAttribute("userContact", userContact);
	    return "chat";
	 }
	 
	 @RequestMapping("/myContacts/send/message/")
	 public String startChatMessage(Principal principal, Model model) {
	    String name = principal.getName();
	    //UserContact userContact = userContactService.findContactDetails(userId);
	    //model.addAttribute("userContact", userContact);
	    return "chat";
	 }

}
