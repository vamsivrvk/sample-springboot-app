package com.jobs.portal.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jobs.portal.model.Job;
import com.jobs.portal.model.User;
import com.jobs.portal.service.JobService;
import com.jobs.portal.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JobService jobService;
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String userFront(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		
		return "profile";
		
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String profilePost(@ModelAttribute("user") User newUser, Model model) {
        User user = userService.findByUsername(newUser.getUsername());
        user.setUsername(newUser.getUsername());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPhone(newUser.getPhone());

        model.addAttribute("user", user);

        userService.saveUser(user);

        return "profile";
    }
	
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String signup(Model model) {
		User user = new User();
		
		model.addAttribute("user", user);
		
		return "changePassword";
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePwd(@ModelAttribute("newPwd") String newPwd, Principal principal, Model model) {
		
		/* if(userService.checkUserExists(user.getUsername(), user.getEmail(), user.getPhone())) {
			if(userService.checkEmailExists(user.getEmail())) {
				model.addAttribute("emailExists", true);
			}
			
			if(userService.checkUsernameExists(user.getUsername())) {
				model.addAttribute("usernameExists", true);
			} */
			
		User user = userService.findByUsername(principal.getName());
		user.setPassword(newPwd);
		User updatedUser = userService.updatePassword(user);
        //userService.saveUser(user);

        model.addAttribute("user", updatedUser);
			
		return "changePassword";
	}
	
	
	@RequestMapping("/jobsPostedByMe")
	public  String jobsPostedByMe(Principal principal, Model model) {
		//
		User user = userService.findByUsername(principal.getName());
		List<Job> jobList = jobService.findByUserId(user.getUserId());
		
		int noOfJobs = jobService.findNoOfJobsPostedByMe(user.getUserId());
		
		
		model.addAttribute("jobList", jobList);
		model.addAttribute("noOfJobs", noOfJobs);
		//model.addAttribute("primaryTransactionList", primaryTransactionList);
		
		return "jobsPostedByMe";
	}
	
	
	@RequestMapping("/postNewJob")
	public  String postNewJob(Model model) {
		Job job = new Job();
		
		model.addAttribute("job", job);
		
		return "postNewJob";
	}

}
