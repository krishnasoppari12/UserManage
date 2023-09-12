package com.usermanagement.service;

import java.util.List;
import java.util.Optional;

import com.usermanagement.exception.NoSuchUserExists;
import com.usermanagement.exception.NoUserExists;
import com.usermanagement.exception.UserAlreadyExists;
import com.usermanagement.repository.UserDesignationInforepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.usermanagement.exception.UserNotFoundException;
import com.usermanagement.model.UserDetails;
import com.usermanagement.repository.UserRepository;
@AllArgsConstructor
@Service
@Slf4j
public class UserServiceImplementation implements UserService{

	//Automatically Created Object Internally
	private UserRepository userRepo;
	private UserDesignationInforepository userInforepo;

	@Override
	@Transactional
	public UserDetails createUserDetails(UserDetails userDetails) throws UserAlreadyExists {
		try {
			// Check if a user with the same full name already exists
			UserDetails existingUser = userRepo.findByFullName(userDetails.getFullName());
			if (existingUser != null) {
				// If a user with the same full name exists, throw an exception
				throw new UserAlreadyExists("User with this username already exists.");
			}

			// If no user with the same full name exists, save the user's designation info
			log.info("UserServiceImplementation: Saving user's designation info in repository");
			userInforepo.save(userDetails.getUserDesignationInfo());

			// Save the user details
			log.info("UserServiceImplementation: Creating New User, Execution Started");
			userRepo.save(userDetails);

			// Return the newly created user details
			log.info("UserServiceImplementation: Returning The repository Object ");
			return userDetails;
		} catch (UserAlreadyExists ex) {
			// Rethrow the custom exception if a user with the same full name already exists
			throw ex;
		} catch (Exception ex) {
			log.error("Exception Occurred While Creating The New User", ex);
			// Handle other exceptions gracefully and throw a custom exception or return an error response if needed
			throw new UserAlreadyExists("Failed to create a new user.");
		}
	}







	public List<UserDetails> getAllUserDetails() {
        return userRepo.findAll();
    }
	@Override
	@Transactional
	public Optional<UserDetails> getUserDetailsById(int id) {

		return Optional.ofNullable(userRepo.findById(id).orElseThrow(
				() -> new NoUserExists("NO CUSTOMER PRESENT WITH ID = " +id)));
	}

	public UserDetails updateUserDetails(int id,UserDetails updatedUserDetails)
	{
		UserDetails existingCustomer
				= userRepo.findById(id)
				.orElse(null);
		if (existingCustomer == null)
			throw new NoSuchUserExists(
					"No Such Customer exists!!");
		else {
			existingCustomer.setFullName(updatedUserDetails.getFullName());
			existingCustomer.setEmail(updatedUserDetails.getEmail());
			existingCustomer.setAddress(updatedUserDetails.getAddress());
			existingCustomer.setQualification(updatedUserDetails.getQualification());

			return userRepo.save(existingCustomer);

		}
	}
	@Override
	@Transactional
	public UserDetails findByFullName(String fullName) {
		UserDetails user= userRepo.findByFullName(fullName);
		if (user==null) {
			throw new UserNotFoundException(fullName);
		}
		return user;
	}
	@Override
	@Transactional
	public boolean deleteUserDetails(int id) {
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
            return true;
        }
        return false;
	}

}
