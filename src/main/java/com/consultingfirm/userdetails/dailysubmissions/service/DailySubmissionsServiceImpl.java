package com.consultingfirm.userdetails.dailysubmissions.service;

import com.consultingfirm.userdetails.common.CommonExcelReaderService;
import com.consultingfirm.userdetails.dailysubmissions.dto.DailySubmissions;
import com.consultingfirm.userdetails.dailysubmissions.model.DailySubmissionsInfo;
import com.consultingfirm.userdetails.dailysubmissions.repository.DailySubmissionsRepository;
import com.consultingfirm.userdetails.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class DailySubmissionsServiceImpl implements DailySubmissionsService {

    private final DailySubmissionsRepository repository;
    private final CommonExcelReaderService commonExcelReaderService;

    public DailySubmissionsServiceImpl(DailySubmissionsRepository repository,
                                       CommonExcelReaderService commonExcelReaderService) {
        this.repository = repository;
        this.commonExcelReaderService = commonExcelReaderService;
    }

    @Override
    public void uploadDailySubmissionDetails(MultipartFile file) throws Exception {
        List<DailySubmissionsInfo> dailySubmissionsInfo = commonExcelReaderService.readDailySubmissionsExcelFile(file);

        repository.saveAll(dailySubmissionsInfo);
    }

    @Override
    public DailySubmissionsInfo createSubmissionInfoDetails(DailySubmissions dailySubmissions) {
        var dailySubmissionsInfo = new DailySubmissionsInfo();
        dailySubmissionsInfo.setDateOfEntry(dailySubmissions.dateOfEntry());
        dailySubmissionsInfo.setRecruiterName(dailySubmissions.recruiterName());
        dailySubmissionsInfo.setConsultantName(dailySubmissions.consultantName());
        dailySubmissionsInfo.setTechnology(dailySubmissions.technology());
        dailySubmissionsInfo.setPriority(dailySubmissions.priority());
        dailySubmissionsInfo.setSkill(dailySubmissions.skill());
        dailySubmissionsInfo.setAllocatedStatus(dailySubmissions.allocatedStatus());
        dailySubmissionsInfo.setClientType(dailySubmissions.clientType());
        dailySubmissionsInfo.setClientName(dailySubmissions.clientName());
        dailySubmissionsInfo.setRequirementSource(dailySubmissions.requirementSource());
        dailySubmissionsInfo.setFeedback(dailySubmissions.feedback());
        dailySubmissionsInfo.setComments(dailySubmissions.comments());
        dailySubmissionsInfo.setDateCreated(dailySubmissions.dateCreated());
        dailySubmissionsInfo.setLastUpdated(dailySubmissions.lastUpdated());

        return repository.save(dailySubmissionsInfo);
    }

    public void updateSubmissionDetails(Long id, DailySubmissionsInfo dailySubmissionsInfo) throws UserNotFoundException {
        DailySubmissionsInfo existingUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        existingUser.setDateOfEntry(dailySubmissionsInfo.getDateOfEntry());
        existingUser.setRecruiterName(dailySubmissionsInfo.getRecruiterName());
        existingUser.setConsultantName(dailySubmissionsInfo.getConsultantName());
        existingUser.setTechnology(dailySubmissionsInfo.getTechnology());
        existingUser.setPriority(dailySubmissionsInfo.getPriority());
        existingUser.setSkill(dailySubmissionsInfo.getSkill());
        existingUser.setAllocatedStatus(dailySubmissionsInfo.getAllocatedStatus());
        existingUser.setClientType(dailySubmissionsInfo.getClientType());
        existingUser.setClientName(dailySubmissionsInfo.getClientName());
        existingUser.setRequirementSource(dailySubmissionsInfo.getRequirementSource());
        existingUser.setFeedback(dailySubmissionsInfo.getFeedback());
        existingUser.setComments(dailySubmissionsInfo.getComments());

        repository.save(existingUser);
    }

    @Override
    public Optional<List<DailySubmissionsInfo>> getSubmissionDetails() {
        return Optional.of(repository.findAll());
    }

    @Override
    public Optional<Optional<DailySubmissionsInfo>> getSubmissionDetailsByID(Long id) {
        return Optional.of(repository.findById(id));
    }

    @Override
    public void deleteAllSubmissionDetails() {
        repository.deleteAll();
    }

    @Override
    public void deleteSubmissionInfoById(long id) {
        repository.deleteById(id);
    }
}