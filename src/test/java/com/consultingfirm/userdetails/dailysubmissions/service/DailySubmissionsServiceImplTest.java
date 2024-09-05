package com.consultingfirm.userdetails.dailysubmissions.service;

import com.consultingfirm.userdetails.common.DailySubmissionsInfoMock;
import com.consultingfirm.userdetails.dailysubmissions.dto.DailySubmissions;
import com.consultingfirm.userdetails.dailysubmissions.model.DailySubmissionsInfo;
import com.consultingfirm.userdetails.dailysubmissions.repository.DailySubmissionsRepository;
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
public class DailySubmissionsServiceImplTest {

    @Mock
    private DailySubmissionsRepository dailySubmissionsRepository;

    @InjectMocks
    private DailySubmissionsServiceImpl dailySubmissionsServiceImpl;

    private DailySubmissionsInfo profile;
    private DailySubmissionsInfo dailySubmissionsInfo;
    private DailySubmissions dailySubmissions;

    @BeforeEach
    void setUp() {
        dailySubmissions = DailySubmissionsInfoMock.shouldCreateDailySubmissionsDetails();
        profile = DailySubmissionsInfoMock.shouldCreateDailySubmissionsProfile();
        dailySubmissionsInfo = DailySubmissionsInfoMock.shouldSetDailySubmissionsInfo(dailySubmissions);
    }


    @Test
    void shouldCreateUserInfoDetails() {
        // Arrange
        when(dailySubmissionsRepository.save(any(DailySubmissionsInfo.class))).thenReturn(dailySubmissionsInfo);

        // Act
        DailySubmissionsInfo result = dailySubmissionsServiceImpl.createSubmissionInfoDetails(dailySubmissions);

        // Assert
        assertEquals(dailySubmissions.recruiterName(), result.getRecruiterName());
        assertEquals(dailySubmissions.consultantName(), result.getConsultantName());
        verify(dailySubmissionsRepository).save(any(DailySubmissionsInfo.class));
    }

    @Test
    void shouldUpdateUserDetails() throws UserNotFoundException {
        // Arrange
        Long id = 1L;
        DailySubmissionsInfo updatedInfo = new DailySubmissionsInfo();
        updatedInfo.setRecruiterName("Mahi");
        when(dailySubmissionsRepository.findById(id)).thenReturn(Optional.of(dailySubmissionsInfo));

        // Act
        dailySubmissionsServiceImpl.updateSubmissionDetails(id, updatedInfo);

        // Assert
        verify(dailySubmissionsRepository).findById(id);
        verify(dailySubmissionsRepository).save(dailySubmissionsInfo);
        assertEquals("Mahi", dailySubmissionsInfo.getRecruiterName());
    }

    @Test
    void shouldThrowExceptionWhenUpdateUserDetails() {
        // Arrange
        Long id = 1L;
        DailySubmissionsInfo updatedInfo = new DailySubmissionsInfo();
        when(dailySubmissionsRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> dailySubmissionsServiceImpl.updateSubmissionDetails(id, updatedInfo));
    }

    @Test
    void shouldGetUserDetails() {
        // Arrange
        List<DailySubmissionsInfo> profilesList = Collections.singletonList(profile);
        when(dailySubmissionsRepository.findAll()).thenReturn(profilesList);

        // Act
        Optional<List<DailySubmissionsInfo>> result = dailySubmissionsServiceImpl.getSubmissionDetails();

        // Assert
        assertTrue(result.isPresent());
        assertEquals(profilesList, result.get());
    }

    @Test
    void shouldGetUserDetailsByID() {
        // Arrange
        when(dailySubmissionsRepository.findById(1L)).thenReturn(Optional.of(profile));

        // Act
        Optional<Optional<DailySubmissionsInfo>> result = dailySubmissionsServiceImpl.getSubmissionDetailsByID(1L);

        // Assert
        assertTrue(result.isPresent());
        assertTrue(result.get().isPresent());
        assertEquals("Batreddi Pavan", result.get().get().getConsultantName());
    }

    @Test
    void shouldReturnEmptyWhenIDNotFound() {
        // Arrange
        when(dailySubmissionsRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<Optional<DailySubmissionsInfo>> result = dailySubmissionsServiceImpl.getSubmissionDetailsByID(1L);

        // Assert
        assertTrue(result.isPresent());
        assertFalse(result.get().isPresent());
    }

    @Test
    void shouldDeleteAllUserInfo() {
        // Act
        dailySubmissionsServiceImpl.deleteAllSubmissionDetails();

        // Assert
        verify(dailySubmissionsRepository).deleteAll();
    }

    @Test
    void shouldDeleteUserInfoById() {
        // Act
        dailySubmissionsServiceImpl.deleteSubmissionInfoById(1L);

        // Assert
        verify(dailySubmissionsRepository).deleteById(1L);
    }
}
