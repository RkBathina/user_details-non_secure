package com.consultingfirm.userdetails.benchprofiles.service;

import com.consultingfirm.userdetails.benchprofiles.dto.BenchProfiles;
import com.consultingfirm.userdetails.benchprofiles.model.BenchProfilesInfo;
import com.consultingfirm.userdetails.benchprofiles.repository.BenchProfilesRepository;
import com.consultingfirm.userdetails.common.BenchProfilesInfoMock;
import com.consultingfirm.userdetails.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BenchProfilesServiceImplTest {

    @Mock
    private BenchProfilesRepository benchProfilesRepository;

    @InjectMocks
    private BenchProfilesServiceImpl benchProfilesServiceImpl;

    private BenchProfilesInfo profile;
    private BenchProfilesInfo benchProfilesInfo;
    private BenchProfiles benchProfilesDto;

    @BeforeEach
    void setUp() {
        benchProfilesDto = BenchProfilesInfoMock.shouldCreateBenchProfilesDetails();
        profile = BenchProfilesInfoMock.shouldCreateProfile();
        benchProfilesInfo = BenchProfilesInfoMock.shouldSetBenchProfilesInfo(benchProfilesDto);
    }


    @Test
    void shouldCreateUserInfoDetails() {
        // Arrange
        when(benchProfilesRepository.save(any(BenchProfilesInfo.class))).thenReturn(benchProfilesInfo);

        // Act
        BenchProfilesInfo result = benchProfilesServiceImpl.createUserInfoDetails(benchProfilesDto);

        // Assert
        assertEquals(benchProfilesDto.recruiterName(), result.getRecruiterName());
        assertEquals(benchProfilesDto.consultantName(), result.getConsultantName());
        verify(benchProfilesRepository).save(any(BenchProfilesInfo.class));
    }

    @Test
    void shouldUpdateUserDetails() throws UserNotFoundException {
        // Arrange
        Long id = 1L;
        BenchProfilesInfo updatedInfo = new BenchProfilesInfo();
        updatedInfo.setRecruiterName("Updated Recruiter");
        when(benchProfilesRepository.findById(id)).thenReturn(Optional.of(benchProfilesInfo));

        // Act
        benchProfilesServiceImpl.updateUserDetails(id, updatedInfo);

        // Assert
        verify(benchProfilesRepository).findById(id);
        verify(benchProfilesRepository).save(benchProfilesInfo);
        assertEquals("Updated Recruiter", benchProfilesInfo.getRecruiterName());
    }

    @Test
    void shouldThrowExceptionWhenUpdateUserDetails() {
        // Arrange
        Long id = 1L;
        BenchProfilesInfo updatedInfo = new BenchProfilesInfo();
        when(benchProfilesRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> benchProfilesServiceImpl.updateUserDetails(id, updatedInfo));
    }

    @Test
    void shouldGetUserDetails() {
        // Arrange
        List<BenchProfilesInfo> profilesList = Collections.singletonList(profile);
        when(benchProfilesRepository.findAll()).thenReturn(profilesList);

        // Act
        Optional<List<BenchProfilesInfo>> result = benchProfilesServiceImpl.getUserDetails();

        // Assert
        assertTrue(result.isPresent());
        assertEquals(profilesList, result.get());
    }

    @Test
    void shouldGetUserDetailsByID() {
        // Arrange
        when(benchProfilesRepository.findById(1L)).thenReturn(Optional.of(profile));

        // Act
        Optional<Optional<BenchProfilesInfo>> result = benchProfilesServiceImpl.getUserDetailsByID(1L);

        // Assert
        assertTrue(result.isPresent());
        assertTrue(result.get().isPresent());
        assertEquals("Muni Dinesh", result.get().get().getConsultantName());
    }

    @Test
    void shouldReturnEmptyWhenIDNotFound() {
        // Arrange
        when(benchProfilesRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<Optional<BenchProfilesInfo>> result = benchProfilesServiceImpl.getUserDetailsByID(1L);

        // Assert
        assertTrue(result.isPresent());
        assertFalse(result.get().isPresent());
    }

    @Test
    void shouldDeleteAllUserInfo() {
        // Act
        benchProfilesServiceImpl.deleteAllUserInfo();

        // Assert
        verify(benchProfilesRepository).deleteAll();
    }

    @Test
    void shouldDeleteUserInfoById() {
        // Act
        benchProfilesServiceImpl.deleteUserInfoById(1L);

        // Assert
        verify(benchProfilesRepository).deleteById(1L);
    }
}
