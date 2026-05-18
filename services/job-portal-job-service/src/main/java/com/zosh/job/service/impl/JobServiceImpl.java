package com.zosh.job.service.impl;

import com.zosh.job.domain.JobStatus;
import com.zosh.job.dto.request.JobRequest;
import com.zosh.job.dto.response.CompanyResponse;
import com.zosh.job.dto.response.JobResponse;
import com.zosh.job.mapper.JobMapper;
import com.zosh.job.modal.Job;
import com.zosh.job.modal.embeddable.JobLocation;
import com.zosh.job.modal.embeddable.SalaryRange;
import com.zosh.job.payload.JobSearchRequest;
import com.zosh.job.repository.JobRepo;
import com.zosh.job.repository.JobSpecification;
import com.zosh.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepo jobRepo;

    @Override
    public JobResponse createJob(Long employerId, JobRequest request) {

        Long companyId = 1L; // Placeholder for company ID retrieval logic based on employerId

        Job job = Job.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .requirements(request.getRequirements())
                .responsibilities(request.getResponsibilities())
                .benefits(request.getBenefits())
                .companyId(companyId)
                .employerId(employerId)
                .location(buildLocation(request))
                .salaryRange(buildSalaryRange(request))
                .jobType(request.getJobType())
                .workMode(request.getWorkMode())
                .experienceLevel(request.getExperienceLevel())
                .openings(request.getOpenings() != null ? request.getOpenings() : 1)
                .applicationDeadline(request.getApplicationDeadline())
                .expiresAt(request.getExpiresAt())
                .build();

        Job savedJob = jobRepo.save(job);

        return convertToResponse(savedJob);
    }

    @Override
    public JobResponse getJobById(Long jobId) throws Exception {
        Job job = jobRepo.findById(jobId).orElseThrow(
                ()-> new Exception("Job not found with id: " + jobId)
        );
        return convertToResponse(job);
    }

    @Override
    public List<JobResponse> getJobs(JobSearchRequest request) {
        List<Job> jobs = jobRepo.findAll(JobSpecification.build(request));
        return jobs.stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public List<JobResponse> getJobsByCompanyId(Long companyId) throws Exception {
        List<Job> job = jobRepo.findByCompanyId(companyId);
        if (job == null) {
            throw new Exception("job is not exist");
        }
        return job.stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public JobResponse updateJob(Long jobId, Long employerId, JobRequest request) throws Exception {
        Job job = jobRepo.findById(jobId).orElseThrow(
                ()-> new Exception("Job not found with id: " + jobId));

        assertEmployer(job, employerId);

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setRequirements(request.getRequirements());
        job.setResponsibilities(request.getResponsibilities());
        job.setBenefits(request.getBenefits());
        job.setLocation(buildLocation(request));
        job.setSalaryRange(buildSalaryRange(request));
        job.setJobType(request.getJobType());
        job.setWorkMode(request.getWorkMode());
        job.setExperienceLevel(request.getExperienceLevel());
        job.setOpenings(request.getOpenings() != null ? request.getOpenings() : job.getOpenings());
        job.setApplicationDeadline(request.getApplicationDeadline());
        job.setExpiresAt(request.getExpiresAt());

        return convertToResponse(jobRepo.save(job));
    }

    @Override
    public JobResponse publishJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepo.findById(jobId).orElseThrow(
                ()-> new Exception("Job not found with id: " + jobId));

        assertEmployer(job, employerId);
        if (job.getStatus() == JobStatus.CLOSED || job.getStatus() == JobStatus.EXPIRED){
            throw new Exception("Cannot publish a closed or expired job");
        }
        job.setStatus(JobStatus.OPEN);
        job.setPublishedAt(LocalDateTime.now());
        job.setActive(true);
        return convertToResponse(jobRepo.save(job));
    }

    @Override
    public JobResponse closeJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepo.findById(jobId).orElseThrow(
                ()-> new Exception("Job not found with id: " + jobId));

        assertEmployer(job, employerId);
        job.setStatus(JobStatus.CLOSED);
        job.setClosedAt(LocalDateTime.now());
        job.setActive(false);
        return convertToResponse(jobRepo.save(job));
    }

    @Override
    public void deleteJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepo.findById(jobId).orElseThrow(
                ()-> new Exception("Job not found with id: " + jobId));

        assertEmployer(job, employerId);
        jobRepo.delete(job);
    }

    @Override
    public List<JobResponse> getAllJobsAdmin() {
        return jobRepo.findAll().stream()
                .map(this::convertToResponse)
                .toList();
    }

    private SalaryRange buildSalaryRange(JobRequest request) {
        if (request.getMinSalary() == null && request.getMaxSalary() == null) {
            return null; // No salary range provided
        }

        return SalaryRange.builder()
                .minSalary(request.getMinSalary())
                .maxSalary(request.getMaxSalary())
                .build();
    }

    private JobLocation buildLocation(JobRequest request) {
        if (request.getCity() == null && request.getCountry() == null) {
            return null; // No location provided
        }

        return JobLocation.builder()
                .city(request.getCity())
                .address(request.getAddress())
                .country(request.getCountry())
                .state(request.getState())
                .zipCode(request.getZipCode())
                .build();
    }
    private JobResponse convertToResponse(Job savedJob) {
        CompanyResponse companyResponse = CompanyResponse.builder()
                .id(savedJob.getCompanyId())
                .build();
        return JobMapper.toResponse(savedJob, companyResponse);
    }

    private void assertEmployer(Job job, Long employerId) throws Exception {
        if (!job.getEmployerId().equals(employerId)) {
            throw new Exception("Unauthorized: You do not have permission to modify this job");
        }
    }
}
