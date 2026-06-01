package com.zosh.job.service;

import com.zosh.job.dto.response.WorkExperienceResponse;
import com.zosh.job.modal.WorkExperience;
import com.zosh.job.payload.AddWorkExperienceRequest;

import java.util.List;


public interface WorkExperienceService {

    WorkExperienceResponse addWorkExperience(Long resumeId, Long candidateId, AddWorkExperienceRequest request) throws Exception;

    List<WorkExperienceResponse> getWorkExperience(Long resumeId);

    WorkExperienceResponse updateWorkExperience(Long resumeId, Long workExperienceId, Long candidateId, AddWorkExperienceRequest request) throws Exception;

    WorkExperience getWorkExperienceEntity(Long workExperienceId) throws Exception;

    void deleteWorkExperience(Long resumeId, Long workExperienceId, Long candidateId) throws Exception;
}
