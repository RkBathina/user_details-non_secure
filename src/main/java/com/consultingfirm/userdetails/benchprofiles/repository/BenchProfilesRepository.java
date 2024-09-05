package com.consultingfirm.userdetails.benchprofiles.repository;

import com.consultingfirm.userdetails.benchprofiles.model.BenchProfilesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenchProfilesRepository extends JpaRepository<BenchProfilesInfo, Long> {
}
