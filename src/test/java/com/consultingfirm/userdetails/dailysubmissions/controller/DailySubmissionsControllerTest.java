package com.consultingfirm.userdetails.dailysubmissions.controller;

import com.consultingfirm.userdetails.common.DailySubmissionsInfoMock;
import com.consultingfirm.userdetails.dailysubmissions.model.DailySubmissionsInfo;
import com.consultingfirm.userdetails.dailysubmissions.service.DailySubmissionsService;
import com.consultingfirm.userdetails.exception.UserNotFoundException;
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

public class DailySubmissionsControllerTest {
    @InjectMocks
    private DailySubmissionsController dailySubmissionsController;

    @Mock
//    @MockBean
    private DailySubmissionsService dailySubmissionsService;

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
        when(dailySubmissionsService.getSubmissionDetails()).thenThrow(new RuntimeException("Unexpected error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> dailySubmissionsController.fetchSubmissionDetails());
    }

    @Test
    void shouldReturnNotFoundExceptionWhenNoUsers() {
        // Arrange
        when(dailySubmissionsService.getSubmissionDetails()).thenReturn(Optional.of(List.of()));

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> dailySubmissionsController.fetchSubmissionDetails());
    }

    @Test
    void shouldReturnBenchProfilesDetails() {
        // Arrange
        List<DailySubmissionsInfo> mockUsers = List.of(new DailySubmissionsInfo());
        when(dailySubmissionsService.getSubmissionDetails()).thenReturn(Optional.of(mockUsers));

        // Act
        ResponseEntity<List<DailySubmissionsInfo>> response = dailySubmissionsController.fetchSubmissionDetails();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsers, response.getBody());
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenOptionalIsEmpty() {
        // Arrange
        when(dailySubmissionsService.getSubmissionDetails()).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> dailySubmissionsController.fetchSubmissionDetails());
    }

    @Test
    void shouldReturnOkWhenUserFound() {
        // Arrange
        Long userId = 1L;
        var mockUser = DailySubmissionsInfoMock.shouldGetDailySubmissionsInfo();
        Optional<DailySubmissionsInfo> mockUserOptional = Optional.of(mockUser);
        when(dailySubmissionsService.getSubmissionDetailsByID(userId)).thenReturn(Optional.of(mockUserOptional));

        // Act
        ResponseEntity<Optional<DailySubmissionsInfo>> response = dailySubmissionsController.fetchSubmissionDetailsByID(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUserOptional, response.getBody());
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenUserNotFound() {
        // Arrange
        Long userId = 1L;
        when(dailySubmissionsService.getSubmissionDetailsByID(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> dailySubmissionsController.fetchSubmissionDetailsByID(userId));
    }

    @Test
    void shouldReturnNoContentWhenDeleteAllSubmissionDetails() {
        // Arrange
        doNothing().when(dailySubmissionsService).deleteAllSubmissionDetails();

        // Act
        ResponseEntity<HttpStatus> response = dailySubmissionsController.deleteAllUserInfo();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldReturnNoContentWhenDeleteUserInfoById() {
        // Arrange
        long userId = 1L;
        doNothing().when(dailySubmissionsService).deleteSubmissionInfoById(userId);

        // Act
        ResponseEntity<HttpStatus> response = dailySubmissionsController.deleteUserInfoById(userId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldUpdateBenchProfileInfoSuccess() {
        // Arrange
        long id = 1L;
        var dailySubmissionsInfo = new DailySubmissionsInfo();

        // Act
        ResponseEntity<String> response = dailySubmissionsController.updateSubmissionInfo(id, dailySubmissionsInfo);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User details updated successfully.", response.getBody());
        verify(dailySubmissionsService).updateSubmissionDetails(id, dailySubmissionsInfo);
    }

    @Test
    void shouldThrowExceptionWhenUpdateBenchProfileInfo() {
        // Arrange
        long id = 1L;
        var dailySubmissionsInfo = new DailySubmissionsInfo();
        //use assertThrow
        doThrow(new RuntimeException("Unexpected error")).when(dailySubmissionsService).updateSubmissionDetails(id, dailySubmissionsInfo);

        // Act & Assert
        try {
            dailySubmissionsController.updateSubmissionInfo(id, dailySubmissionsInfo);
        } catch (RuntimeException e) {
            assertEquals("Unexpected error", e.getMessage());
        }
    }

    @Test
    void shouldCreateBenchProfileInfoSuccess() throws URISyntaxException {
        DailySubmissionsInfo savedDailySubmissionsInfo = DailySubmissionsInfoMock.shouldCreateDailySubmissionsProfile();

        var dailySubmissionsDto = DailySubmissionsInfoMock.shouldCreateDailySubmissionsDetails();

        //define uriComponentsBuilder and add mock annotation and mock in the method
        DailySubmissionsInfo dailySubmissionsInfo = new DailySubmissionsInfo();
        dailySubmissionsInfo.setId(1L);

        when(dailySubmissionsService.createSubmissionInfoDetails(dailySubmissionsDto)).thenReturn(savedDailySubmissionsInfo);

        // Act
        ResponseEntity<DailySubmissionsInfo> response = dailySubmissionsController.createSubmissionInfo(dailySubmissionsDto);

        // Assert
        assertEquals("201 CREATED", response.getStatusCode().toString());
        assertTrue(response.getHeaders().getLocation().toString().contains("/1"));
    }


    @Test
    void shouldThrowExceptionWhenCreateDailySubmissionsInfo() {

        var dailySubmissionsDto = DailySubmissionsInfoMock.shouldCreateDailySubmissionsDetails();

        when(dailySubmissionsService.createSubmissionInfoDetails(dailySubmissionsDto)).thenThrow(new RuntimeException("Unexpected error"));

        // Act & Assert
        try {
            dailySubmissionsController.createSubmissionInfo(dailySubmissionsDto);
        } catch (RuntimeException e) {
            assertEquals("Unexpected error", e.getMessage());
        }
    }

}
