package com.zosh.job.repository;

import com.zosh.job.modal.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepo extends JpaRepository<Education, Long> {

    List<Education> findByResumeIdOrderByDisplayOrderAsc(Long resumeId);
}
