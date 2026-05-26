package com.zosh.job.service;

import com.zosh.job.dto.request.JobRequest;
import com.zosh.job.dto.response.JobResponse;
import com.zosh.job.payload.JobSearchRequest;

import java.util.List;

public interface JobService {

    JobResponse createJob(Long employerId , JobRequest request) throws Exception;

    JobResponse getJobById(Long jobId) throws Exception;

    List<JobResponse> getJobs(JobSearchRequest request);

    List<JobResponse> getJobsByCompanyId(Long companyId) throws Exception;

    JobResponse updateJob(Long jobId ,Long employerId, JobRequest request) throws Exception;

    JobResponse publishJob(Long jobId, Long employerId) throws Exception;

    JobResponse closeJob(Long jobId, Long employerId) throws Exception;

    void deleteJob(Long jobId, Long employerId) throws Exception;

    List<JobResponse> getAllJobsAdmin();



}
