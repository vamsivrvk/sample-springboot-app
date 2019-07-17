package com.jobs.portal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jobs.portal.model.UserContact;

public interface UserContactDao extends CrudRepository<UserContact, Long> {
	
	List<UserContact> findByUserUserId(Long id);
	
	Optional<UserContact> findById(Long id);

}
