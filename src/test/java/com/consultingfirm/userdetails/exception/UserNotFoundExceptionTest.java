package com.consultingfirm.userdetails.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserNotFoundExceptionTest {

    @Test
    void shouldTestUserNotFoundExceptionMessage() {
        // Arrange
        String expectedMessage = "User not found";

        // Act
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            throw new UserNotFoundException(expectedMessage);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}