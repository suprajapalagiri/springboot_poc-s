package com.ojas.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ojas.employee.response.EmployeeResponse;


@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>exceptionHandler(MethodArgumentNotValidException methodArgument){
		EmployeeResponse response=new EmployeeResponse();
		response.setMessage(methodArgument.getBindingResult().getFieldError().getDefaultMessage());
		response.setStatusCode("400");
		return new ResponseEntity<EmployeeResponse>(response,HttpStatus.BAD_REQUEST);
		
	}
	
}
