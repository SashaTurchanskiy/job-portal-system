package com.zosh.job.repository;

import com.zosh.job.modal.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceRepo extends JpaRepository<WorkExperience, Long> {

    List<WorkExperience> findByResumeIdOrderByDisplayOrderAsc(Long resumeId);
}
