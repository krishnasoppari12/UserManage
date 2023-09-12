package com.usermanagement.controller;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.model.UserDetails;
import com.usermanagement.service.UserService;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/new")
    public ResponseEntity<String> createUserDetails(@RequestBody UserDetails userDetails) {
        userService.createUserDetails(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
    }

    //@Cacheable(Value="getAll")
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDetails>> getAllUserDetails() {
        List<UserDetails> userDetailsList = userService.getAllUserDetails();
        return ResponseEntity.ok(userDetailsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetails> getUserDetailsById(@PathVariable int id) {
        Optional<UserDetails> userDetails = userService.getUserDetailsById(id);
        if (userDetails.isPresent()) {
            return ResponseEntity.ok(userDetails.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetails> updateUserDetails(@PathVariable int id, @RequestBody UserDetails userDetails) {
        UserDetails updatedUserDetails = userService.updateUserDetails(id, userDetails);
        if (updatedUserDetails != null) {
            return ResponseEntity.ok(updatedUserDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserDetails(@PathVariable int id) {
        boolean deleted = userService.deleteUserDetails(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findByFullName/{fullName}")
    public UserDetails findByFullName(@PathVariable String fullName) {
        return userService.findByFullName(fullName);
    }


}
