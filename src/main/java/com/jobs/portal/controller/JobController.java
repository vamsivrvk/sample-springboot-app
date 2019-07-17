package com.jobs.portal.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jobs.portal.model.Job;
import com.jobs.portal.model.User;
import com.jobs.portal.service.JobService;
import com.jobs.portal.service.UserService;

@Controller
@RequestMapping("/userFront")
public class JobController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JobService jobService;
	
	@RequestMapping("/jobSearch")
	public  String jobsPostedByMe(Principal principal, Model model) {
		//
		User user = userService.findByUsername(principal.getName());
		List<Job> jobList = jobService.findAllJobs();
		
		int noOfJobs = jobList.size();
		
		
		model.addAttribute("jobList", jobList);
		model.addAttribute("noOfJobs", noOfJobs);
		
		return "jobSearch";
	}

}
