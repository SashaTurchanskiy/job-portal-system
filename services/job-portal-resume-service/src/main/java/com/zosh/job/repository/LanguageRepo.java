package com.zosh.job.repository;

import com.zosh.job.modal.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepo extends JpaRepository<Language, Long> {

    List<Language> findResumeByIdOrderByDisplayOrderAsc(Long resumeId);
}
