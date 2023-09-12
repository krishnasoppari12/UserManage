package com.usermanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.usermanagement.controller.UserController;
import com.usermanagement.model.UserDetails;
import com.usermanagement.service.UserService;

@WebMvcTest(UserController.class)
public class HomeControllerTest {

//	@Autowired
//	private MockMvc mockMvc;
	@MockBean
	private UserService userService;
	
	
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
	} 
	*/
	/*  getAllSuccess  */
	
	@Test
	void testGetAllUserDetails() throws Exception {
		when(userService.getAllUserDetails())
		.thenReturn(userDetailsList);
		List<UserDetails> obj=userService.getAllUserDetails();
		assertEquals(2, obj.size());
		verify(userService,times(1)).getAllUserDetails();
	}    
	
	/*  getById */
	
	/*@Test
	void getUserDetailsById() throws Exception {
		when(userService.getUserDetailsById(1))
		.thenReturn(userDetailsById);
		Optional<UserDetails> us=userService.getUserDetailsById(1);
		assertEquals("ram",us.get().getFullName());
		verify(userService, times(1)).getUserDetailsById(1);
	}  */
	
	// Delete
    @Test
    public void testDeleteUserDetails() throws Exception {
        //when(userService.deleteUserDetails(userDetailsOne.getId())).thenReturn(true);      
        when(userService.deleteUserDetails(userDetailsOne.getId())).thenReturn(true); 
        userService.deleteUserDetails(userDetailsOne.getId());
         verify(userService, times(1)).deleteUserDetails(userDetailsOne.getId());
    } 
    
    //Add New
    UserDetails createdUserDetails=userDetailsOne;
    @Test
	public void createUserDetails()
	{
    	userService.createUserDetails(createdUserDetails);
		verify(userService, times(1)).createUserDetails(createdUserDetails);
	}
    
    
  
}

	
	
	
	
	

