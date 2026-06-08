package com.zosh.job.service;

import com.zosh.job.dto.response.LanguageResponse;
import com.zosh.job.payload.AddLanguageRequest;

import java.util.List;

public interface LanguageService {

    LanguageResponse addLanguage(Long resumeId, Long candidateId, AddLanguageRequest request);

    List<LanguageResponse> getLanguages();

    LanguageResponse updateLanguage(Long languageId, Long resumeId, Long candidateId, AddLanguageRequest request);

    void deleteLanguage(Long languageId, Long resumeId, Long candidateId);
}
