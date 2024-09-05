package com.consultingfirm.userdetails.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomizedResponseEntityExceptionHandlerTest {

    private CustomizedResponseEntityExceptionHandler exceptionHandler;
    private WebRequest request;

    @BeforeEach
    void setUp() {
        exceptionHandler = new CustomizedResponseEntityExceptionHandler();
        request = new ServletWebRequest(new MockHttpServletRequest());
    }

    @Test
    void shouldReturnInternalServerErrorWhenHandlingAllExceptions() throws Exception {
        // Arrange
        Exception ex = new Exception("General exception");

        // Act
        ResponseEntity<ErrorDetails> response = exceptionHandler.handleAllExceptions(ex, request);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("General exception", response.getBody().message());
    }

    @Test
    void shouldReturnNotFoundWhenHandlingUserNotFoundException() throws Exception {
        // Arrange
        UserNotFoundException ex = new UserNotFoundException("User not found");

        // Act
        ResponseEntity<ErrorDetails> response = exceptionHandler.handleUserNotFoundException(ex, request);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody().message());
    }

    @Test
    void shouldReturnBadRequestWhenHandlingMethodArgumentNotValid() {
        // Arrange
        BindException bindException = new BindException(new Object(), "objectName");
        bindException.addError(new FieldError("objectName", "fieldName", "Invalid value"));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindException);

        // Act
        ResponseEntity<Object> response = exceptionHandler.handleMethodArgumentNotValid(ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDetails errorDetails = (ErrorDetails) response.getBody();
        assertEquals("Total Errors:1 First Error:Invalid value", errorDetails.message());
    }
}
