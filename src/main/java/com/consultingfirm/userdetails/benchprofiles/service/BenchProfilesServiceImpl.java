package com.consultingfirm.userdetails.benchprofiles.service;

import com.consultingfirm.userdetails.benchprofiles.dto.BenchProfiles;
import com.consultingfirm.userdetails.benchprofiles.model.BenchProfilesInfo;
import com.consultingfirm.userdetails.benchprofiles.repository.BenchProfilesRepository;
import com.consultingfirm.userdetails.common.CommonExcelReaderService;
import com.consultingfirm.userdetails.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class BenchProfilesServiceImpl implements BenchProfilesService {

    private final BenchProfilesRepository repository;
    private final CommonExcelReaderService excelReaderService;

    public BenchProfilesServiceImpl(BenchProfilesRepository repository,
                                    CommonExcelReaderService excelReaderService) {
        this.repository = repository;
        this.excelReaderService = excelReaderService;
    }

    @Override
    public void uploadUserDetails(MultipartFile file) throws Exception {
        List<BenchProfilesInfo> benchProfilesInfo = excelReaderService.readBenchProfilesExcelFile(file);
        repository.saveAll(benchProfilesInfo);
    }

    @Override
    public BenchProfilesInfo createUserInfoDetails(BenchProfiles benchProfiles) {
        var benchProfilesInfo = BenchProfilesInfo.builder()
                .recruiterName(benchProfiles.recruiterName())
                .consultantName(benchProfiles.consultantName())
                .allocatedStatus(benchProfiles.allocatedStatus())
                .status(benchProfiles.status())
                .turboCheck(benchProfiles.turboCheck())
                .priority(benchProfiles.priority())
                .technology(benchProfiles.technology())
                .organization(benchProfiles.organization())
                .experience(benchProfiles.experience())
                .location(benchProfiles.location())
                .relocation(benchProfiles.relocation())
                .modeOfStaying(benchProfiles.modeOfStaying())
                .newOrExisting(benchProfiles.newOrExisting())
                .sourcedBy(benchProfiles.sourcedBy())
                .visaStatus(benchProfiles.visaStatus())
                .marketingVisaStatus(benchProfiles.marketingVisaStatus())
                .contactNumber(benchProfiles.contactNumber())
                .emailId(benchProfiles.emailId())
                .originalDob(benchProfiles.originalDob())
                .marketingDob(benchProfiles.marketingDob())
                .whatsappNumber(benchProfiles.whatsappNumber())
                .marketingStartDate(benchProfiles.marketingStartDate())
                .marketingEndDate(benchProfiles.marketingEndDate())
                .comments(benchProfiles.comments())
                .build();

        return repository.save(benchProfilesInfo);
    }

    @Override
    public void updateUserDetails(Long id, BenchProfilesInfo benchProfilesInfo) throws UserNotFoundException {
        var existingUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        // Update fields
        existingUser.setRecruiterName(benchProfilesInfo.getRecruiterName());
        existingUser.setConsultantName(benchProfilesInfo.getConsultantName());
        existingUser.setAllocatedStatus(benchProfilesInfo.getAllocatedStatus());
        existingUser.setStatus(benchProfilesInfo.getStatus());
        existingUser.setTurboCheck(benchProfilesInfo.getTurboCheck());
        existingUser.setPriority(benchProfilesInfo.getPriority());
        existingUser.setTechnology(benchProfilesInfo.getTechnology());
        existingUser.setOrganization(benchProfilesInfo.getOrganization());
        existingUser.setExperience(benchProfilesInfo.getExperience());
        existingUser.setLocation(benchProfilesInfo.getLocation());
        existingUser.setRelocation(benchProfilesInfo.getRelocation());
        existingUser.setModeOfStaying(benchProfilesInfo.getModeOfStaying());
        existingUser.setNewOrExisting(benchProfilesInfo.getNewOrExisting());
        existingUser.setSourcedBy(benchProfilesInfo.getSourcedBy());
        existingUser.setVisaStatus(benchProfilesInfo.getVisaStatus());
        existingUser.setMarketingVisaStatus(benchProfilesInfo.getMarketingVisaStatus());
        existingUser.setContactNumber(benchProfilesInfo.getContactNumber());
        existingUser.setEmailId(benchProfilesInfo.getEmailId());
        existingUser.setOriginalDob(benchProfilesInfo.getOriginalDob());
        existingUser.setMarketingDob(benchProfilesInfo.getMarketingDob());
        existingUser.setWhatsappNumber(benchProfilesInfo.getWhatsappNumber());
        existingUser.setMarketingStartDate(benchProfilesInfo.getMarketingStartDate());
        existingUser.setMarketingEndDate(benchProfilesInfo.getMarketingEndDate());
        existingUser.setComments(benchProfilesInfo.getComments());

        repository.save(existingUser);
    }

    @Override
    public Optional<List<BenchProfilesInfo>> getUserDetails() {
        return Optional.of(repository.findAll());
    }

    @Override
    public Optional<Optional<BenchProfilesInfo>> getUserDetailsByID(Long id) {
        return Optional.of(repository.findById(id));
    }

    @Override
    public void deleteAllUserInfo() {
        repository.deleteAll();
    }

    @Override
    public void deleteUserInfoById(long id) {
        repository.deleteById(id);
    }
}
