package com.zosh.job.repository;

import com.zosh.job.modal.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, Long> {

    List<Project> findByResumeIdOrderByDisplayOrderAsc(Long resumeId);

}
