package com.consultingfirm.userdetails.interviews.service;

import com.consultingfirm.userdetails.common.InterviewsInfoMock;
import com.consultingfirm.userdetails.exception.UserNotFoundException;
import com.consultingfirm.userdetails.interviews.dto.Interview;
import com.consultingfirm.userdetails.interviews.model.InterviewInfo;
import com.consultingfirm.userdetails.interviews.repository.InterviewRepository;
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
public class InterviewServiceImplTest {

    @Mock
    private InterviewRepository interviewRepository;

    @InjectMocks
    private InterviewServiceImpl interviewServiceImpl;

    private InterviewInfo profile;
    private InterviewInfo interviewInfo;
    private Interview interview;

    @BeforeEach
    void setUp() {
        interview = InterviewsInfoMock.shouldCreateInterviewDetails();
        profile = InterviewsInfoMock.shouldCreateInterviewProfile();
        interviewInfo = InterviewsInfoMock.shouldSetInterviewInfo(interview);
    }

    @Test
    void shouldCreateInterviewDetails() {
        // Arrange
        when(interviewRepository.save(any(InterviewInfo.class))).thenReturn(interviewInfo);

        // Act
        InterviewInfo result = interviewServiceImpl.createInterviewInfoDetails(interview);

        // Assert
        assertEquals(interview.recruiterName(), result.getRecruiterName());
        assertEquals(interview.consultantName(), result.getConsultantName());
        verify(interviewRepository).save(any(InterviewInfo.class));
    }

    @Test
    void shouldUpdateInterviewDetails() throws UserNotFoundException {
        // Arrange
        Long id = 1L;
        InterviewInfo updatedInfo = new InterviewInfo();
        updatedInfo.setRecruiterName("Mahi");
        when(interviewRepository.findById(id)).thenReturn(Optional.of(interviewInfo));

        // Act
        interviewServiceImpl.updateInterviewDetails(id, updatedInfo);

        // Assert
        verify(interviewRepository).findById(id);
        verify(interviewRepository).save(interviewInfo);
        assertEquals("Mahi", interviewInfo.getRecruiterName());
    }

    @Test
    void shouldThrowExceptionWhenUpdateInterviewDetails() {
        // Arrange
        Long id = 1L;
        InterviewInfo updatedInfo = new InterviewInfo();
        when(interviewRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> interviewServiceImpl.updateInterviewDetails(id, updatedInfo));
    }

    @Test
    void shouldGetInterviewDetails() {
        // Arrange
        List<InterviewInfo> profilesList = Collections.singletonList(profile);
        when(interviewRepository.findAll()).thenReturn(profilesList);

        // Act
        Optional<List<InterviewInfo>> result = interviewServiceImpl.getInterviewDetails();

        // Assert
        assertTrue(result.isPresent());
        assertEquals(profilesList, result.get());
    }

    @Test
    void shouldGetInterviewsByID() {
        // Arrange
        when(interviewRepository.findById(1L)).thenReturn(Optional.of(profile));

        // Act
        Optional<Optional<InterviewInfo>> result = Optional.ofNullable(interviewServiceImpl.getInterviewDetailsByID(1L));

        // Assert
        assertTrue(result.isPresent());
        assertTrue(result.get().isPresent());
        assertEquals("Batreddi Pavan", result.get().get().getConsultantName());
    }

    @Test
    void shouldReturnEmptyWhenIDNotFound() {
        // Arrange
        when(interviewRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<Optional<InterviewInfo>> result = Optional.ofNullable(interviewServiceImpl.getInterviewDetailsByID(1L));

        // Assert
        assertTrue(result.isPresent());
        assertFalse(result.get().isPresent());
    }

    @Test
    void shouldDeleteAllInterviewsInfo() {
        // Act
        interviewServiceImpl.deleteAllInterviewInfo();

        // Assert
        verify(interviewRepository).deleteAll();
    }

    @Test
    void shouldDeleteInterviewInfoById() {
        // Act
        interviewServiceImpl.deleteInterviewInfoById(1L);

        // Assert
        verify(interviewRepository).deleteById(1L);
    }
}
