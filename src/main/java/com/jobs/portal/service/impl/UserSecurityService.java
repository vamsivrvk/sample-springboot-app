package com.jobs.portal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jobs.portal.dao.UserDao;
import com.jobs.portal.model.User;

@Service
public class UserSecurityService  implements UserDetailsService {
	
	public static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

	@Autowired
	private UserDao userDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userDao.findByUsername(name);
		if(null == user) {
			LOG.warn("Username {} not found", name);
			throw new UsernameNotFoundException("UserName: " + name + " not found");
		}
		
		return user;
	}

}
