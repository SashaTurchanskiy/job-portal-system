package com.zosh.job.service;

import com.zosh.job.dto.response.ResumeSkillResponse;
import com.zosh.job.payload.AddResumeSkillRequest;

import java.util.List;

public interface ResumeSkillService {

    ResumeSkillResponse addResumeSkill(Long resumeId, Long candidateId, AddResumeSkillRequest request) throws Exception;

    List<ResumeSkillResponse> getSkills(Long resumeId);

    ResumeSkillResponse updateSkill(Long skillId, Long resumeId, Long candidateId, AddResumeSkillRequest request);

    void deleteSkill(Long skillId, Long resumeId, Long candidateId);
}
