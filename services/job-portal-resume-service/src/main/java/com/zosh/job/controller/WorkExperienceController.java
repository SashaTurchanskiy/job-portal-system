package com.zosh.job.controller;

import com.zosh.job.dto.response.ApiResponse;
import com.zosh.job.dto.response.WorkExperienceResponse;
import com.zosh.job.payload.AddWorkExperienceRequest;
import com.zosh.job.service.WorkExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume/{resumeId}/worksExp")
@RequiredArgsConstructor
public class WorkExperienceController {

    private final WorkExperienceService workExperienceService;

    @PostMapping("/add")
    public ResponseEntity<WorkExperienceResponse> addWorkExperience(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid AddWorkExperienceRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(workExperienceService.addWorkExperience(resumeId ,candidateId ,request));
    }

    @GetMapping
    public ResponseEntity<List<WorkExperienceResponse>> getWorkExperience(
            @PathVariable Long resumeId){
        return ResponseEntity.ok(workExperienceService.getWorkExperience(resumeId));
    }

    @PutMapping("/update/{workExperienceId}")
    public ResponseEntity<WorkExperienceResponse> updateWorkExperience(
            @PathVariable Long resumeId,
            @PathVariable Long workExperienceId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid AddWorkExperienceRequest request) throws Exception {
        return ResponseEntity.ok(workExperienceService.updateWorkExperience(resumeId, workExperienceId, candidateId, request));
    }

    @DeleteMapping("/delete/{workExperienceId}")
    public ResponseEntity<ApiResponse> deleteWorkExperience(
            @PathVariable Long resumeId,
            @PathVariable Long workExperienceId,
            @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        workExperienceService.deleteWorkExperience(resumeId, workExperienceId, candidateId);
        return ResponseEntity.ok(new ApiResponse("Work experience deleted successfully", true));
    }
}
