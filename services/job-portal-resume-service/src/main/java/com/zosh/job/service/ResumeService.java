package com.zosh.job.service;

import com.zosh.job.dto.PersonalInfo;
import com.zosh.job.dto.response.ResumeResponse;
import com.zosh.job.modal.Resume;
import com.zosh.job.payload.CreateResumeRequest;

import java.util.List;

public interface ResumeService {

    ResumeResponse createResume(Long candidateId, CreateResumeRequest request);

    ResumeResponse getResumeById(Long resumeId, Long candidateId);

    List<ResumeResponse> getMyResumes(Long candidateId);

    ResumeResponse updatePersonalInfo(Long resumeId, Long candidateId, PersonalInfo request);

    ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary);

    ResumeResponse setDefaultResume(Long resumeId, Long candidateId);

    void deleteResume(Long resumeId, Long candidateId);

    Resume getResumeEntity(Long resumeId);
}
