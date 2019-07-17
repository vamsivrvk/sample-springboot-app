package com.jobs.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobs.portal.dao.UserContactDao;
import com.jobs.portal.model.UserContact;
import com.jobs.portal.service.UserContactService;

@Service
public class UserContactServiceImpl implements UserContactService {

	@Autowired
	private UserContactDao userContactDao;
	@Override
	public int findNoOfMyContacts(Long id) {
		List<UserContact> myContacts = userContactDao.findByUserUserId(id);
		return myContacts.size();
	}
	
	
	@Override
	public List<UserContact> findMyContacts(Long userId) {
		return userContactDao.findByUserUserId(userId);
	}


	@Override
	public UserContact findContactDetails(Long id) {
		return userContactDao.findById(id).orElse(null);
	}

}
