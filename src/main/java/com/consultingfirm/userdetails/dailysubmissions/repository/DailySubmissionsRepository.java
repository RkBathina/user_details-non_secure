package com.consultingfirm.userdetails.dailysubmissions.repository;

import com.consultingfirm.userdetails.dailysubmissions.model.DailySubmissionsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailySubmissionsRepository extends JpaRepository<DailySubmissionsInfo, Long> {
}
