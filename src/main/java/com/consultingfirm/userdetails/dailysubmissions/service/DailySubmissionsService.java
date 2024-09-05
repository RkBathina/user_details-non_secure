package com.consultingfirm.userdetails.dailysubmissions.service;

import com.consultingfirm.userdetails.dailysubmissions.dto.DailySubmissions;
import com.consultingfirm.userdetails.dailysubmissions.model.DailySubmissionsInfo;
import com.consultingfirm.userdetails.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DailySubmissionsService {
    void uploadDailySubmissionDetails(MultipartFile file) throws Exception;

    DailySubmissionsInfo createSubmissionInfoDetails(DailySubmissions dailySubmissionsDto);

    void updateSubmissionDetails(Long id, DailySubmissionsInfo dailySubmissionsInfo) throws UserNotFoundException;

    Optional<List<DailySubmissionsInfo>> getSubmissionDetails();

    Optional<Optional<DailySubmissionsInfo>> getSubmissionDetailsByID(Long id);

    void deleteAllSubmissionDetails();

    void deleteSubmissionInfoById(long id);
}
