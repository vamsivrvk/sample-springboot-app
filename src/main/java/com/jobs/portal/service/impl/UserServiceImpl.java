package com.jobs.portal.service.impl;

import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobs.portal.dao.RoleDao;
import com.jobs.portal.dao.UserDao;
import com.jobs.portal.model.User;
import com.jobs.portal.model.security.UserRole;
import com.jobs.portal.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User findByUsername(String name) {
		return userDao.findByUsername(name);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	@Override
	public User findByPhone(String phoneNo) {
		return userDao.findByPhone(phoneNo);
	}

	@Override
	public boolean checkUserExists(String name, String email, String phoneNo) {
		if(checkUsernameExists(name) || checkEmailExists(email) || checkPhoneExists(phoneNo)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean checkUsernameExists(String name) {
		if(null != findByUsername(name)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean checkEmailExists(String email) {
		if(null != findByEmail(email)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean checkPhoneExists(String phone) {
		if(null != findByPhone(phone)) {
			return true;
		}
		
		return false;
	}

		
	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public User createUser(User user, Set<UserRole> userRoles) {
		User localUser = userDao.findByUsername(user.getUsername());
		
		if(localUser != null) {
			LOG.info("User with username {} already exist. Nothing will be done.", user.getUsername());
		} else {
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			
			for(UserRole ur: userRoles) {
				roleDao.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
			localUser = userDao.save(user);
		}
		
		return localUser;
	}

	@Override
	public User updatePassword(User user) {
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		return userDao.save(user);
	}

}
