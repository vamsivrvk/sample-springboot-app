package com.jobs.portal.service;

import java.util.List;

import com.jobs.portal.model.Job;

public interface JobService {

	List<Job> findByUserId(Long id);
	
	int findNoOfJobsPostedByMe(Long id);
	
	List<Job> findAllJobs();
}
