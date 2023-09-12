package com.usermanagement.service;

import java.util.List;
import java.util.Optional;
import com.usermanagement.model.UserDetails;

public interface UserService {

	//public UserDetails createUserDetails(UserDetails userDetails);

	public List<UserDetails> getAllUserDetails();

	public Optional<UserDetails> getUserDetailsById(int id);

	//public UserDetails updateUserDetails(int id, UserDetails userDetails);
	public UserDetails updateUserDetails(int id,UserDetails userDetails);

	//public void createUserDetails(UserDetails userDetails);
	public UserDetails createUserDetails(UserDetails userDetails);

	public boolean deleteUserDetails(int id);
	
	public UserDetails findByFullName(String fullName);

}
