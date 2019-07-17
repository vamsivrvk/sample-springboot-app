package com.jobs.portal.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jobs.portal.model.Job;

public interface JobDao extends CrudRepository<Job, Long> {

	List<Job> findByUserUserId(Long id);
	
	List<Job> findAll();
}
