package com.zosh.job.service;

import com.zosh.job.dto.response.EducationResponse;
import com.zosh.job.modal.Education;
import com.zosh.job.payload.AddEducationRequest;

import java.util.List;

public interface EducationService {

    EducationResponse addEducation(Long resumeId, Long candidateId, AddEducationRequest request) throws Exception;

    List<EducationResponse> getEducations(Long resumeId);

    EducationResponse updateEducation(Long educationId, Long resumeId, Long candidateId, AddEducationRequest request) throws Exception;

    Education getEducationEntity(Long educationId) throws Exception;

    void deleteEducation(Long educationId, Long resumeId, Long candidateId) throws Exception;
}
