package com.jobs.portal.dao;

import org.springframework.data.repository.CrudRepository;

import com.jobs.portal.model.User;

public interface UserDao extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	User findByPhone(String phoneNo);

}
