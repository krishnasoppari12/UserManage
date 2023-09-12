package com.usermanagement;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermanagement.controller.UserController;
import com.usermanagement.model.UserDetails;
import com.usermanagement.service.UserService;

@SpringBootTest
public class ControllerTest {

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;
  
   
    private MockMvc mockMvc;
    
 
     
    UserDetails userDetailsOne;
	UserDetails userDetailsTwo;
	List<UserDetails> userDetailsList=new ArrayList<>();
	/*Optional<UserDetails> userDetailsById=Optional.of(new UserDetails( 1, "ram", "a@gmail.com", "hyd", "mca","ram"));
	
    @BeforeEach
	void setUp() {
		userDetailsOne=new UserDetails( 1, "ram", "a@gmail.com", "hyd", "mca","ram"); 
		userDetailsTwo=new UserDetails( 2,"ram", "a@gmail.com", "hyd", "mca","ram"); 
		userDetailsList.add(userDetailsOne);
		userDetailsList.add(userDetailsTwo);
		MockitoAnnotations.openMocks(this);
	} */
	
    //  Create User 
    @Test
    public void testCreateUserDetails() throws Exception {
        UserDetails userDetails = new UserDetails();
        when(userService.createUserDetails(any(UserDetails.class))).thenReturn(userDetails);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        mockMvc.perform(post("/user/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value(userDetails.getFullName()));
    }

    // Delete Success
    @Test
    public void testDeleteUserDetailsSuccess() throws Exception {
        int userId = 1;

        when(userService.deleteUserDetails(userId)).thenReturn(true);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/" + userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(userService, times(1)).deleteUserDetails(userId);
    }

    // Delete If Not Having Any Value
    @Test
    public void testDeleteUserDetailsNotFound() throws Exception {
        int userId = 1;

        when(userService.deleteUserDetails(userId)).thenReturn(false);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/" + userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(userService, times(1)).deleteUserDetails(userId);
    }
    
     
    // GetUserById      
    /*@Test
    public void testGetUserDetailsByIdExists() throws Exception {
 
    	int id=1;
        when(userService.getUserDetailsById(id)).thenReturn(userDetailsById);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/user/" + id)
                .contentType(MediaType.APPLICATION_JSON));   
        assertEquals("ram", userDetailsById.get().getFullName());
       
    }*/
    
   //  If User Not Found While Delete
    @Test
    public void testGetUserDetailsByIdNotFound() throws Exception {
        int userId = 2;
        when(userService.getUserDetailsById(userId)).thenReturn(Optional.empty());
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        mockMvc.perform(get("/user/" + userId))
                .andExpect(status().isNotFound());
    }
    
  // Get All Users  
    @Test
    public void testGetAllUserDetails() throws Exception {
        when(userService.getAllUserDetails()).thenReturn(userDetailsList);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        mockMvc.perform(get("/user/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(userDetailsList.size()));
    }
   
  // Update The value
    @Test
    public void testUpdateUserDetails() throws Exception {
        int userId = 1;
        UserDetails userDetails = new UserDetails();
        when(userService.updateUserDetails(eq(userId), any(UserDetails.class))).thenReturn(userDetails);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        mockMvc.perform(put("/user/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value(userDetails.getFullName()));
    }
  // Update if user not found
    @Test
    public void testUpdateUserNotFound() throws Exception {
            int userId = 3;
            when(userService.updateUserDetails(userId, userDetailsTwo)).thenReturn(null);
            mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
            mockMvc.perform(MockMvcRequestBuilders.put("/user/" + userId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"id\": 2, \"name\": \"Updated Name\"}"))
                      .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
} 
