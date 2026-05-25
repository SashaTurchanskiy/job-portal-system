package com.zosh.job.service;

import com.zosh.job.dto.response.JobSkillResponse;
import com.zosh.job.modal.JobSkill;
import com.zosh.job.payload.JobSkillRequest;

import java.util.List;
import java.util.Set;

public interface JobSkillService {

    JobSkillResponse createJobSkill(JobSkillRequest request) throws Exception;

    List<JobSkillResponse> getAllJobSkills();

    JobSkillResponse getSkillById(Long id) throws Exception;


    JobSkillResponse updateJobSkill(Long id, JobSkillRequest request) throws Exception;

    void deleteJobSkill(Long id) throws Exception;

    Set<JobSkill> getSkillByIds(Set<Long> ids);
}
