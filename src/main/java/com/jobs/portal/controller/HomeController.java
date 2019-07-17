package com.jobs.portal.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jobs.portal.dao.RoleDao;
import com.jobs.portal.model.User;
import com.jobs.portal.model.security.Role;
import com.jobs.portal.model.security.UserRole;
import com.jobs.portal.service.JobService;
import com.jobs.portal.service.UserContactService;
import com.jobs.portal.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserContactService userContactService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private RoleDao roleDao;
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		User user = new User();
		
		model.addAttribute("user", user);
		
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(@ModelAttribute("user") User user, Model model) {
		
		if(userService.checkUserExists(user.getUsername(), user.getEmail(), user.getPhone())) {
			if(userService.checkEmailExists(user.getEmail())) {
				model.addAttribute("emailExists", true);
			}
			
			if(userService.checkUsernameExists(user.getUsername())) {
				model.addAttribute("usernameExists", true);
			}
			
			return "signUp";
			
		} else {
			Set<UserRole> userRoles = new HashSet<>();
			if(roleDao.findByName("ROLE_USER") == null) {
				Role newRole = new Role(1, "ROLE_USER");
				roleDao.save(newRole);
			}
			userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));
			
			userService.createUser(user, userRoles);
			return "redirect:/";
		}
	}
	
	@RequestMapping("/userFront")
	public String userFront(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		int noOfJobs = jobService.findNoOfJobsPostedByMe(user.getUserId());
		int noOfContacts = userContactService.findNoOfMyContacts(user.getUserId());
		
		model.addAttribute("user", user);
		model.addAttribute("noOfJobs", noOfJobs);
		model.addAttribute("noOfContacts", noOfContacts);
		
		return "userFront";
	}

}
