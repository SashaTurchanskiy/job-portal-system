package com.zosh.job.service;


import com.zosh.job.dto.PersonalInfoResponse;
import com.zosh.job.dto.response.ResumeResponse;
import com.zosh.job.modal.Resume;
import com.zosh.job.payload.CreateResumeRequest;

import java.util.List;

public interface ResumeService {

    ResumeResponse createResume(Long candidateId, CreateResumeRequest request);

    ResumeResponse getResumeById(Long resumeId, Long candidateId) throws Exception;

    List<ResumeResponse> getMyResume(Long candidateId);

    ResumeResponse updatePersonalInfo(Long resumeId, Long candidateId, PersonalInfoResponse req) throws Exception;

    ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary) throws Exception;

    ResumeResponse setDefaultResume(Long resumeId, Long candidateId) throws Exception;

    void deleteResume(Long resumeId, Long candidateId) throws Exception;

    Resume getResumeEntity(Long resumeId) throws Exception;

}
