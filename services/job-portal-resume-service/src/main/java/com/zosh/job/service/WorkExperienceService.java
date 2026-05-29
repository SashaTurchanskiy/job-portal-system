package com.zosh.job.service;

import com.zosh.job.dto.response.WorkExperienceResponse;
import com.zosh.job.payload.AddWorkExperienceRequest;

import java.util.List;


public interface WorkExperienceService {

    WorkExperienceResponse addWorkExperience(Long resumeId, Long candidateId, AddWorkExperienceRequest request);

    List<WorkExperienceResponse> getWorkExperience(Long resumeId);

    WorkExperienceResponse updateWorkExperience(Long resumeId, Long workExperienceId, AddWorkExperienceRequest request);

    void deleteWorkExperience(Long resumeId, Long workExperienceId, Long candidateId);
}
