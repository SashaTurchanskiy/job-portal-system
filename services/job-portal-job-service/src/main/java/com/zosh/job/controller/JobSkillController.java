package com.zosh.job.controller;

import com.zosh.job.dto.response.ApiResponse;
import com.zosh.job.dto.response.JobSkillResponse;
import com.zosh.job.payload.JobSkillRequest;
import com.zosh.job.service.JobSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-skills")
@RequiredArgsConstructor
public class JobSkillController {

    private final JobSkillService jobSkillService;

    @PostMapping("/create")
    public ResponseEntity<JobSkillResponse> createJobSkill(@RequestBody @Valid JobSkillRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobSkillService.createJobSkill(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobSkillResponse>> getAllJobSkills(){
        return ResponseEntity.ok(jobSkillService.getAllJobSkills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSkillResponse> getJobSkillById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobSkillService.getSkillById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JobSkillResponse> updateJobSkill(
            @PathVariable Long id,
            @RequestBody JobSkillRequest request) throws Exception {
        return ResponseEntity.ok(jobSkillService.updateJobSkill(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deactivateJobSkill(@PathVariable Long id) throws Exception {
        jobSkillService.deleteJobSkill(id);
        return ResponseEntity.ok(new ApiResponse("Job skill deactivated successfully", true));
    }

}
