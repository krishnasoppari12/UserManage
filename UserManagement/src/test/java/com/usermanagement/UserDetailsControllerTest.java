//package com.userManagement;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.userManagement.controller.UserController;
//import com.userManagement.model.UserDetails;
//import com.userManagement.service.UserService;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//
//@WebMvcTest(UserController.class)
//public class UserDetailsControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private UserController userController;
//
//    @InjectMocks
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
///*
//    @Test
//    public void testGetAllUserDetails() throws Exception {
//        when(userService.getAllUserDetails()).thenReturn(Collections.emptyList());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/user/getAll"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
//                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
//
//        verify(userService, times(1)).getAllUserDetails();
//    }*/
//
//    @Test
//    public void testGetUserDetailsById() throws Exception {
//        UserDetails userDetails = new UserDetails();
//        userDetails.setId(1);
//        userDetails.setFullName("John Doe");
//        userDetails.setEmail("john@example.com");
//        userDetails.setAddress("123 Main St");
//        userDetails.setQualification("Bachelor's Degree");
//        userDetails.setPassword("secure123");
//
//        when(userService.getUserDetailsById(1)).thenReturn(Optional.of(userDetails));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("John Doe"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john@example.com"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("123 Main St"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.qualification").value("Bachelor's Degree"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("secure123"));
//
//        verify(userService, times(1)).getUserDetailsById(1);
//    }
//
//
//
//
//
//}
