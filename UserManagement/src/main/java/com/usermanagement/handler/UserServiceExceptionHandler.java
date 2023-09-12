package com.usermanagement.handler;

import com.usermanagement.dto.APIResponse;
import com.usermanagement.dto.ErrorDTO;
import com.usermanagement.exception.UserAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.usermanagement.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
@ControllerAdvice
public class UserServiceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	    public APIResponse<?> handleMethodArgumentException(MethodArgumentNotValidException ex) {
		 APIResponse<?> serviceResponse=new APIResponse<>();
		 List<ErrorDTO> errors=new ArrayList<>();
		 ex.getBindingResult().getFieldErrors().forEach(error ->{
			// ErrorDTO errorDTO=new ErrorDTO(error.getField(),error.getDefaultMessage());
			 ErrorDTO errorDTO=new ErrorDTO(error.getField(),error.getDefaultMessage());
			 errors.add(errorDTO);
		 });
		 serviceResponse.setStatus("Failed");
		 serviceResponse.setErrors(errors);
		 return serviceResponse;
	    }

	@ExceptionHandler(UserNotFoundException.class)
	public APIResponse<?> handleUserNotFoundException(UserNotFoundException exception) {
		APIResponse<?> serviceResponse = new APIResponse<>();
		serviceResponse.setStatus("FAILED");
		//serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
		serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
		return serviceResponse;
	}

	@ExceptionHandler(UserAlreadyExists.class)
	public APIResponse<?> handleUserAlreayExistException(UserAlreadyExists exception) {
		APIResponse<?> serviceResponse = new APIResponse<>();
		serviceResponse.setStatus("FAILED");
		serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
		return serviceResponse;
	}
	/*@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<APIResponse> handleUserNotFoundException(){
		APIResponse apiResponse=new APIResponse(400,"No User Found With This Name",new Date());
		return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoUserExists.class)
	public ResponseEntity<APIResponse> handleUserNotFoundExceptionById(){
		APIResponse apiResponse=new APIResponse(400,"No User Found With This Id",new Date());
		return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserAlreadyExists.class)
	public ResponseEntity<APIResponse> handleUserAlreayExistException(){
		APIResponse apiResponse=new APIResponse(400,"User with this username already exists",new Date());
		return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoSuchUserExists.class)
	public ResponseEntity<APIResponse> handleNoSuchUserExistException(){
		APIResponse apiResponse=new APIResponse(400,"No Such User exists!! ",new Date());
		return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}*/



}
