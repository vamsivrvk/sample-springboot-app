package com.jobs.portal.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobs.portal.dao.JobDao;
import com.jobs.portal.model.Job;
import com.jobs.portal.service.JobService;


@Service
@Transactional
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobDao jobDao;

	@Override
	public List<Job> findByUserId(Long id) {
		return jobDao.findByUserUserId(id);
	}

	@Override
	public int findNoOfJobsPostedByMe(Long id) {
		List<Job> jobs = jobDao.findByUserUserId(id);
		return jobs.size();
	}
	
	@Override
	public List<Job> findAllJobs() {
		return (List<Job>) jobDao.findAll();
	}

}
