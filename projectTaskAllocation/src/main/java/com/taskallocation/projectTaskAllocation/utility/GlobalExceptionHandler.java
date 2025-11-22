package com.taskallocation.projectTaskAllocation.utility;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.taskallocation.projectTaskAllocation.exception.EmailAlreadyExistsException;
import com.taskallocation.projectTaskAllocation.exception.InvalidCredentialsException;
import com.taskallocation.projectTaskAllocation.exception.UserNotFoundException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

	@Autowired
	private Environment environment;

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleUserNotFoundException(UserNotFoundException exception) {
               LOGGER.error("Error occurred with status code {}: {}", HttpStatus.NOT_FOUND.value(), exception.getMessage(),
				exception);
		String message = exception.getMessage();

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorInfo.setErrorMessage(message);
		errorInfo.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
	}
@ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class })
	public ResponseEntity<ErrorInfo> handleValidationExceptions(Exception exception) {
		// LOGGER.error("Validation error: {}", exception.getMessage(), exception);
		LOGGER.error("Error occurred with status code {}: {}", HttpStatus.BAD_REQUEST.value(), exception.getMessage(),
				exception);
		String errorMsg = "";

		if (exception instanceof MethodArgumentNotValidException manvException) {
			errorMsg = manvException.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(", "));
		} else if (exception instanceof ConstraintViolationException cvException) {
			errorMsg = cvException.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
					.collect(Collectors.joining(", "));
		}

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(errorMsg);
		errorInfo.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}
@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorInfo> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
		// LOGGER.error("Database constraint violation: {}", exception.getMessage(),
		// exception);
		LOGGER.error("Error occurred with status code {}: {}", HttpStatus.BAD_REQUEST.value(), exception.getMessage(),
				exception);
		String message = "Database constraint violated: " + extractConstraintMessage(exception);

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(message);
		errorInfo.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}
@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleGeneralException(Exception exception) {
		// LOGGER.error("Unhandled exception: {}", exception.getMessage(), exception);
		LOGGER.error("Error occurred with status code {}: {}", HttpStatus.INTERNAL_SERVER_ERROR.value(),
				exception.getMessage(), exception);
		String message = environment.getProperty("GeneralException.EXCEPTION_MSG",
				"Something went wrong. Please try again later.");

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setErrorMessage(message);
		errorInfo.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}
private String extractConstraintMessage(DataIntegrityViolationException ex) {
		if (ex.getRootCause() != null) {
			String message = ex.getRootCause().getMessage();
			// Custom logic to handle specific constraints (e.g., 'skill_id cannot be null')
			if (message.contains("skill_id cannot be null")) {
				return "Skill ID cannot be null";
			}
			return message;
		}
		return "Unknown database error.";
	}

	@ExceptionHandler(EmailAlreadyExistsException.class) // ---->LOGIN AND REGISTER IT SHOULD WORK
	public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); // 409
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> handleInvalidCredentials(InvalidCredentialsException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage()); // 401
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<String> handleIllegalState(IllegalStateException ex) {
	    return ResponseEntity.badRequest().body(ex.getMessage());
	}
}