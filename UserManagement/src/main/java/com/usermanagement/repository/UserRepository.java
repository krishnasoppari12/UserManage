package com.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.usermanagement.model.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {

	 Object getUserDetailsById(int i);
	 
	 @Query("SELECT u FROM UserDetails u WHERE u.fullName = :fullName")
	 UserDetails findByFullName(@Param("fullName") String fullName);
}
