package com.zosh.job.service;

import com.zosh.job.dto.request.JobRequest;
import com.zosh.job.dto.response.JobResponse;
import com.zosh.job.payload.JobSearchRequest;

import java.util.List;

public interface JobService {

    JobResponse createJob(Long employerId , JobRequest request);

    JobResponse getJobById(Long jobId);

    List<JobResponse> getJobs(JobSearchRequest request);

    List<JobResponse> getJobsByCompanyId(Long companyId);

    JobResponse updateJob(Long jobId ,Long employerId, JobRequest request);

    JobResponse publishJob(Long jobId, Long employerId);

    JobResponse closeJob(Long jobId, Long employerId);

    void deleteJob(Long jobId, Long employerId);

    List<JobResponse> getAllJobsAdmin();



}
