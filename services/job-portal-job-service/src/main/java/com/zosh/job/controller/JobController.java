package com.zosh.job.controller;

import com.zosh.job.dto.ApiResponse;
import com.zosh.job.dto.request.JobRequest;
import com.zosh.job.dto.response.JobResponse;
import com.zosh.job.payload.JobSearchRequest;
import com.zosh.job.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("/create")
    public ResponseEntity<JobResponse> createJob(
            @RequestHeader ("X-User-Id") Long employerId,
            @RequestBody @Valid JobRequest jobRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(jobService.createJob(employerId,  jobRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @GetMapping("/get/jobs")
    public ResponseEntity<List<JobResponse>> getJobs(
            @ModelAttribute JobSearchRequest request){
        return ResponseEntity.ok(jobService.getJobs(request));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<JobResponse>> getJobsByCompanyId(@PathVariable Long companyId) throws Exception {
        return ResponseEntity.ok(jobService.getJobsByCompanyId(companyId));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<JobResponse>> getAllJobsForAdmin() {
        return ResponseEntity.ok(jobService.getAllJobsAdmin());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JobResponse> updateJob(
            @RequestHeader ("X-User-Id") Long employerId,
            @PathVariable Long id,
            @RequestBody @Valid JobRequest jobRequest) throws Exception {
        return ResponseEntity.ok(jobService.updateJob(employerId, id, jobRequest));
    }

    @PatchMapping("{id}/publish")
    public ResponseEntity<JobResponse> publishJob(
            @RequestHeader ("X-User-Id") Long employerId,
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobService.publishJob(employerId, id));
    }

    @PatchMapping("{id}/close")
    public ResponseEntity<JobResponse> closeJob(
            @RequestHeader ("X-User-Id") Long employerId,
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobService.closeJob(employerId, id));
    }

     @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteJob(
            @RequestHeader ("X-User-Id") Long employerId,
            @PathVariable Long id) throws Exception {
        jobService.deleteJob(employerId, id);
        return ResponseEntity.ok(new ApiResponse("Job deleted successfully", true));
     }

}
