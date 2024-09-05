package com.consultingfirm.userdetails.benchprofiles.service;

import com.consultingfirm.userdetails.benchprofiles.dto.BenchProfiles;
import com.consultingfirm.userdetails.benchprofiles.model.BenchProfilesInfo;
import com.consultingfirm.userdetails.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BenchProfilesService {
    void uploadUserDetails(MultipartFile file) throws Exception;

    BenchProfilesInfo createUserInfoDetails(BenchProfiles benchProfiles);

    void updateUserDetails(Long id, BenchProfilesInfo benchProfilesInfo) throws UserNotFoundException;

    Optional<List<BenchProfilesInfo>> getUserDetails();

    Optional<Optional<BenchProfilesInfo>> getUserDetailsByID(Long id);

    void deleteAllUserInfo();

    void deleteUserInfoById(long id);
}
