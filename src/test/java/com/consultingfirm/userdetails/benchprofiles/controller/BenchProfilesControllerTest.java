package com.consultingfirm.userdetails.benchprofiles.controller;

import com.consultingfirm.userdetails.benchprofiles.model.BenchProfilesInfo;
import com.consultingfirm.userdetails.benchprofiles.service.BenchProfilesService;
import com.consultingfirm.userdetails.common.BenchProfilesInfoMock;
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

//@WebMvcTest(controllers = BenchProfilesController.class)
//@WebMvcTest
//@SpringBootTest
//@AutoConfigureMockMvc
class BenchProfilesControllerTest {

    @InjectMocks
    private BenchProfilesController benchProfilesController;

    @Mock
//    @MockBean
    private BenchProfilesService benchProfilesService;

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
        when(benchProfilesService.getUserDetails()).thenThrow(new RuntimeException("Unexpected error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> benchProfilesController.fetchBenchProfileDetails());
    }

    @Test
    void shouldReturnNotFoundExceptionWhenNoUsers() {
        // Arrange
        when(benchProfilesService.getUserDetails()).thenReturn(Optional.of(List.of()));

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> benchProfilesController.fetchBenchProfileDetails());
    }

    @Test
    void shouldReturnBenchProfilesDetails() {
        // Arrange
        List<BenchProfilesInfo> mockUsers = List.of(new BenchProfilesInfo());
        when(benchProfilesService.getUserDetails()).thenReturn(Optional.of(mockUsers));

        // Act
        ResponseEntity<List<BenchProfilesInfo>> response = benchProfilesController.fetchBenchProfileDetails();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsers, response.getBody());
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenOptionalIsEmpty() {
        // Arrange
        when(benchProfilesService.getUserDetails()).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> benchProfilesController.fetchBenchProfileDetails());
    }

    @Test
    void shouldReturnOkWhenUserFound() {
        // Arrange
        Long userId = 1L;
        var mockUser = BenchProfilesInfoMock.shouldGetBenchProfilesInfo();
        Optional<BenchProfilesInfo> mockUserOptional = Optional.of(mockUser);
        when(benchProfilesService.getUserDetailsByID(userId)).thenReturn(Optional.of(mockUserOptional));

        // Act
        ResponseEntity<Optional<BenchProfilesInfo>> response = benchProfilesController.fetchBenchProfileDetailsByID(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUserOptional, response.getBody());
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenUserNotFound() {
        // Arrange
        Long userId = 1L;
        when(benchProfilesService.getUserDetailsByID(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> benchProfilesController.fetchBenchProfileDetailsByID(userId));
    }

    @Test
    void shouldReturnNoContentWhenDeleteAllUserInfo() {
        // Arrange
        doNothing().when(benchProfilesService).deleteAllUserInfo();

        // Act
        ResponseEntity<HttpStatus> response = benchProfilesController.deleteAllUserInfo();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldReturnNoContentWhenDeleteUserInfoById() {
        // Arrange
        long userId = 1L;
        doNothing().when(benchProfilesService).deleteUserInfoById(userId);

        // Act
        ResponseEntity<HttpStatus> response = benchProfilesController.deleteUserInfoById(userId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldUpdateBenchProfileInfoSuccess() {
        // Arrange
        long id = 1L;
        var benchProfilesInfo = new BenchProfilesInfo();

        // Act
        ResponseEntity<String> response = benchProfilesController.updateBenchProfileInfo(id, benchProfilesInfo);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User details updated successfully.", response.getBody());
        verify(benchProfilesService).updateUserDetails(id, benchProfilesInfo);
    }

    @Test
    void shouldThrowExceptionWhenUpdateBenchProfileInfo() {
        // Arrange
        long id = 1L;
        var benchProfilesInfo = new BenchProfilesInfo();
        //use assertThrow
        doThrow(new RuntimeException("Unexpected error")).when(benchProfilesService).updateUserDetails(id, benchProfilesInfo);

        // Act & Assert
        try {
            benchProfilesController.updateBenchProfileInfo(id, benchProfilesInfo);
        } catch (RuntimeException e) {
            assertEquals("Unexpected error", e.getMessage());
        }
    }

    @Test
    void shouldCreateBenchProfileInfoSuccess() throws URISyntaxException {
        BenchProfilesInfo savedbenchProfilesInfo = BenchProfilesInfoMock.shouldCreateProfile();

        var benchProfilesDto = BenchProfilesInfoMock.shouldCreateBenchProfilesDetails();

        //define uriComponentsBuilder and add mock annotation and mock in the method
        BenchProfilesInfo benchProfilesInfo = new BenchProfilesInfo();
        benchProfilesInfo.setId(1L);

        when(benchProfilesService.createUserInfoDetails(benchProfilesDto)).thenReturn(savedbenchProfilesInfo);

        // Act
        ResponseEntity<BenchProfilesInfo> response = benchProfilesController.createBenchProfileInfo(benchProfilesDto);

        // Assert
        assertEquals("201 CREATED", response.getStatusCode().toString());
        assertTrue(response.getHeaders().getLocation().toString().contains("/1"));
    }


    @Test
    void shouldThrowExceptionWhenCreateBenchProfileInfo() {

        var benchProfilesDto = BenchProfilesInfoMock.shouldCreateBenchProfilesDetails();

        when(benchProfilesService.createUserInfoDetails(benchProfilesDto)).thenThrow(new RuntimeException("Unexpected error"));

        // Act & Assert
        try {
            benchProfilesController.createBenchProfileInfo(benchProfilesDto);
        } catch (RuntimeException e) {
            assertEquals("Unexpected error", e.getMessage());
        }
    }
}
