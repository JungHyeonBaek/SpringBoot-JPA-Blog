package com.cos.blog.handler;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value=NoSuchElementException.class)
	public String handleNoSuchElementException(NoSuchElementException e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
}
