package com.jobs.portal.service;

import java.util.List;

import com.jobs.portal.model.UserContact;

public interface UserContactService {
	
	int findNoOfMyContacts(Long id);

	List<UserContact> findMyContacts(Long userId);
	
	UserContact findContactDetails(Long id);

}
