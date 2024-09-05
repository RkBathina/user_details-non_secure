package com.consultingfirm.userdetails.interviews.controller;

import com.consultingfirm.userdetails.common.InterviewsInfoMock;
import com.consultingfirm.userdetails.exception.UserNotFoundException;
import com.consultingfirm.userdetails.interviews.model.InterviewInfo;
import com.consultingfirm.userdetails.interviews.service.InterviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InterviewControllerTest {

    @InjectMocks
    private InterviewController interviewController;

    @Mock
    private InterviewService interviewService;

    @Mock
    UriComponentsBuilder uriComponentsBuilder;

    @Mock
    UriComponents uriComponents;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void shouldReturnExceptionWhenUnexpectedErrorOccurs() {
        // Arrange
        when(interviewService.getInterviewDetails()).thenThrow(new RuntimeException("Unexpected error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> interviewController.fetchInterviewDetails());
    }

    @Test
    void shouldReturnInterviewDetails() {
        // Arrange
        List<InterviewInfo> mockUsers = List.of(new InterviewInfo());
        when(interviewService.getInterviewDetails()).thenReturn(Optional.of(mockUsers));

        // Act
        ResponseEntity<List<InterviewInfo>> response = interviewController.fetchInterviewDetails();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsers, response.getBody());
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenOptionalIsEmpty() {
        // Arrange
        when(interviewService.getInterviewDetails()).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> interviewController.fetchInterviewDetails());
    }

    @Test
    void shouldReturnOkWhenUserFound() {
        // Arrange
        Long userId = 1L;
        var mockUser = InterviewsInfoMock.shouldGetInterviewInfo();
        Optional<InterviewInfo> mockUserOptional = Optional.of(mockUser);
        when(interviewService.getInterviewDetailsByID(userId)).thenReturn(mockUserOptional);

        // Act
        ResponseEntity<Optional<InterviewInfo>> response = interviewController.fetchInterviewDetailsByID(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUserOptional, response.getBody());
    }

    @Test
    void shouldReturnNoContentWhenDeleteAllInterviewDetails() {
        // Arrange
        doNothing().when(interviewService).deleteAllInterviewInfo();

        // Act
        ResponseEntity<HttpStatus> response = interviewController.deleteAllInterviewInfo();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldReturnNoContentWhenDeleteInterviewInfoById() {
        // Arrange
        long userId = 1L;
        doNothing().when(interviewService).deleteInterviewInfoById(userId);

        // Act
        ResponseEntity<HttpStatus> response = interviewController.deleteInterviewInfoById(userId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldUpdateInterviewInfoSuccess() {
        // Arrange
        long id = 1L;
        var interviewInfo = new InterviewInfo();

        // Act
        ResponseEntity<String> response = interviewController.updateInterviewInfo(id, interviewInfo);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Interview details updated successfully.", response.getBody());
        verify(interviewService).updateInterviewDetails(id, interviewInfo);
    }

    @Test
    void shouldThrowExceptionWhenUpdateInterviewInfo() {
        // Arrange
        long id = 1L;
        var interviewInfo = new InterviewInfo();
        //use assertThrow
        doThrow(new RuntimeException("Unexpected error")).when(interviewService).updateInterviewDetails(id, interviewInfo);

        // Act & Assert
        try {
            interviewController.updateInterviewInfo(id, interviewInfo);
        } catch (RuntimeException e) {
            assertEquals("Unexpected error", e.getMessage());
        }
    }

    @Test
    void shouldCreateInterviewInfoSuccess() throws URISyntaxException {
        InterviewInfo savedInterviewInfo = InterviewsInfoMock.shouldCreateInterviewProfile();

        var interviewDto = InterviewsInfoMock.shouldCreateInterviewDetails();

        //define uriComponentsBuilder and add mock annotation and mock in the method
        InterviewInfo interviewInfo = new InterviewInfo();
        interviewInfo.setId(1L);

        when(interviewService.createInterviewInfoDetails(interviewDto)).thenReturn(savedInterviewInfo);

        // Act
        ResponseEntity<InterviewInfo> response = interviewController.createInterviewInfo(interviewDto);

        // Assert
        assertEquals("201 CREATED", response.getStatusCode().toString());
        assertTrue(response.getHeaders().getLocation().toString().contains("/1"));
    }


    @Test
    void shouldThrowExceptionWhenCreateInterviewsInfo() {

        var interviewDto = InterviewsInfoMock.shouldCreateInterviewDetails();

        when(interviewService.createInterviewInfoDetails(interviewDto)).thenThrow(new RuntimeException("Unexpected error"));

        // Act & Assert
        try {
            interviewController.createInterviewInfo(interviewDto);
        } catch (RuntimeException e) {
            assertEquals("Unexpected error", e.getMessage());
        }
    }

}
