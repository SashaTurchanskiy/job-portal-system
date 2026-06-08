package com.zosh.job.service.impl;

import com.zosh.job.dto.response.LanguageResponse;
import com.zosh.job.payload.AddLanguageRequest;
import com.zosh.job.repository.LanguageRepo;
import com.zosh.job.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepo languageRepo;

    @Override
    public LanguageResponse addLanguage(Long resumeId, Long candidateId, AddLanguageRequest request) {
        return null;
    }

    @Override
    public List<LanguageResponse> getLanguages() {
        return List.of();
    }

    @Override
    public LanguageResponse updateLanguage(Long languageId, Long resumeId, Long candidateId, AddLanguageRequest request) {
        return null;
    }

    @Override
    public void deleteLanguage(Long languageId, Long resumeId, Long candidateId) {

    }
}
