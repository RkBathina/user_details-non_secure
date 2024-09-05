package com.consultingfirm.userdetails.interviews.repository;

import com.consultingfirm.userdetails.interviews.model.InterviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<InterviewInfo, Long> {
}
