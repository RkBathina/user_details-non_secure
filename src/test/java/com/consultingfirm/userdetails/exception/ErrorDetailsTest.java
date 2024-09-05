package com.consultingfirm.userdetails.exception;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorDetailsTest {

    @Test
    void shouldTestErrorDetailsInitialization() {
        // Arrange
        LocalDateTime expectedTimestamp = LocalDateTime.now();
        String expectedMessage = "Error occurred";
        String expectedDetails = "Some error details";

        // Act
        ErrorDetails errorDetails = new ErrorDetails(expectedTimestamp, expectedMessage, expectedDetails);

        // Assert
        assertEquals(expectedTimestamp, errorDetails.timestamp());
        assertEquals(expectedMessage, errorDetails.message());
        assertEquals(expectedDetails, errorDetails.details());
    }
}