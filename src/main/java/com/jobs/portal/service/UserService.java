package com.jobs.portal.service;

import java.util.Set;

//import java.util.Set;
import com.jobs.portal.model.User;
import com.jobs.portal.model.security.UserRole;


public interface UserService {
	
	User findByUsername(String name);
	
	User findByEmail(String email);
	
	User findByPhone(String phoneNo);
	
	boolean checkUserExists(String username, String email, String phoneNo);
	
	boolean checkUsernameExists(String email);
	
	boolean checkEmailExists(String email);
	
	boolean checkPhoneExists(String phone);
	
	//void save(User user);
	
	User createUser(User user, Set<UserRole> userRoles);
	
	void saveUser(User user);

	User updatePassword(User user);

}
