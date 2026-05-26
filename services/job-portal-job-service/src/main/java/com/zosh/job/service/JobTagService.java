package com.zosh.job.service;

import com.zosh.job.dto.response.JobTagResponse;
import com.zosh.job.modal.JobTag;
import com.zosh.job.payload.JobSkillRequest;
import com.zosh.job.payload.JobTagRequest;

import java.util.List;
import java.util.Set;

public interface JobTagService {

    JobTagResponse createTag(JobTagRequest request) throws Exception;

    List<JobTagResponse> getAllTags();

    JobTagResponse updateTag(Long id, JobTagRequest request) throws Exception;

    JobTagResponse getTagById(Long id) throws Exception;

    void deleteTag(Long id) throws Exception;

    JobTag getTagEntityById(Long id) throws Exception;

    Set<JobTag> getTagsByIds(Set<Long> ids);
}
