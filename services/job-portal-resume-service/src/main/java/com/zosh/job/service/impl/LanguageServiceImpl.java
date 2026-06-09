package com.zosh.job.service.impl;

import com.zosh.job.dto.response.LanguageResponse;
import com.zosh.job.mapper.LanguageMapper;
import com.zosh.job.modal.Language;
import com.zosh.job.modal.Resume;
import com.zosh.job.payload.AddLanguageRequest;
import com.zosh.job.repository.LanguageRepo;
import com.zosh.job.service.LanguageService;
import com.zosh.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepo languageRepo;
    private final ResumeService resumeService;

    @Override
    public LanguageResponse addLanguage(Long resumeId, Long candidateId, AddLanguageRequest request) throws Exception {
        Resume resume = resumeService.getResumeEntity(resumeId);

        assertOwner(resume, candidateId);

        Language language = Language.builder()
                .resume(resume)
                .languageName(request.getLanguageName())
                .proficiency(request.getProficiency())
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .build();

        return LanguageMapper.toLanguageResponse(languageRepo.save(language));
    }

    @Override
    public List<LanguageResponse> getLanguages(Long resumeId) {
        return languageRepo.findResumeByIdOrderByDisplayOrderAsc(resumeId)
                .stream()
                .map(LanguageMapper::toLanguageResponse)
                .toList();
    }

    @Override
    public LanguageResponse updateLanguage(Long languageId, Long resumeId, Long candidateId, AddLanguageRequest request) throws Exception {
        Language language = languageRepo.findById(languageId)
                .orElseThrow(()-> new Exception("Language not found with id" + languageId));

        assertOwner(language.getResume(), candidateId);

        language.setLanguageName(request.getLanguageName());
        if (request.getDisplayOrder() != null) language.setDisplayOrder(request.getDisplayOrder());
        language.setProficiency(request.getProficiency());
        //language.setCreatedAt(LocalDateTime.now());
        return LanguageMapper.toLanguageResponse(languageRepo.save(language));
    }

    @Override
    public void deleteLanguage(Long languageId, Long resumeId, Long candidateId) throws Exception {
        Language language = languageRepo.findById(languageId)
                .orElseThrow(()-> new Exception("Language not found with id" + languageId));

        assertOwner(language.getResume(), candidateId);

        languageRepo.delete(language);

    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if (!resume.getCandidateId().equals(candidateId)){
            throw new Exception("Unauthorized: Candidate does not own this resume");
        }
    }
}
