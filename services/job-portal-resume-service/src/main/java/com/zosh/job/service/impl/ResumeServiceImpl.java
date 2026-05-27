package com.zosh.job.service.impl;

import com.zosh.job.dto.PersonalInfo;
import com.zosh.job.dto.response.ResumeResponse;
import com.zosh.job.modal.Resume;
import com.zosh.job.payload.CreateResumeRequest;
import com.zosh.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    @Override
    public ResumeResponse createResume(Long candidateId, CreateResumeRequest request) {
        return null;
    }

    @Override
    public ResumeResponse getResumeById(Long resumeId, Long candidateId) {
        return null;
    }

    @Override
    public List<ResumeResponse> getMyResume(Long candidateId) {
        return List.of();
    }

    @Override
    public ResumeResponse updatePersonalInfo(Long resumeId, Long candidateId, PersonalInfo req) {
        return null;
    }

    @Override
    public ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary) {
        return null;
    }

    @Override
    public ResumeResponse setDefaultResume(Long resumeId, Long candidateId) {
        return null;
    }

    @Override
    public void deleteResume(Long resumeId, Long candidateId) {

    }

    @Override
    public Resume getResumeEntity(Long resumeId) {
        return null;
    }
}
