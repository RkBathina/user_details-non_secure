package com.consultingfirm.userdetails.interviews.service;


import com.consultingfirm.userdetails.exception.UserNotFoundException;
import com.consultingfirm.userdetails.interviews.dto.Interview;
import com.consultingfirm.userdetails.interviews.model.InterviewInfo;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface InterviewService {
    void createInterviewDetails(MultipartFile file) throws Exception;

    InterviewInfo createInterviewInfoDetails(@Valid Interview interview);

    void updateInterviewDetails(Long id, InterviewInfo interviewInfo) throws UserNotFoundException;

    Optional<List<InterviewInfo>> getInterviewDetails();

    Optional<InterviewInfo> getInterviewDetailsByID(Long id);

    void deleteInterviewInfoById(long id);

    void deleteAllInterviewInfo();
}
