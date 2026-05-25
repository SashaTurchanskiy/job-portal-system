package com.zosh.job.service;

import com.zosh.job.dto.response.JobTagResponse;
import com.zosh.job.modal.JobTag;
import com.zosh.job.payload.JobSkillRequest;
import com.zosh.job.payload.JobTagRequest;

import java.util.List;

public interface JobTagService {

    JobTagResponse createTag(JobSkillRequest request) throws Exception;

    List<JobTagResponse> getAllTags();

    JobTagResponse updateTag(Long id, JobTagRequest request);

    JobTagResponse getTagById(Long id);

    void deleteTag(Long id);

    JobTag getTagEntityById(Long id);
}
