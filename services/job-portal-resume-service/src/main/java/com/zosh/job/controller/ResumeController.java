package com.zosh.job.controller;

import com.zosh.job.dto.PersonalInfoResponse;
import com.zosh.job.dto.response.ApiResponse;
import com.zosh.job.dto.response.ResumeResponse;
import com.zosh.job.payload.CreateResumeRequest;
import com.zosh.job.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/create")
    public ResponseEntity<ResumeResponse> createResume(
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid CreateResumeRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(resumeService.createResume(candidateId, request));
    }

    @GetMapping("/id")
    public ResponseEntity<ResumeResponse> getById(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        return ResponseEntity.ok(resumeService.getResumeById(resumeId, candidateId));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ResumeResponse>> getMyResume(
            @RequestHeader("X-User-Id") Long candidateId){
        return ResponseEntity.ok(resumeService.getMyResume(candidateId));
    }

    @PutMapping("/update/{resumeId}")
    public ResponseEntity<ResumeResponse> updateInfo(
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long resumeId,
            @RequestBody PersonalInfoResponse req) throws Exception {
        return ResponseEntity.ok(resumeService.updatePersonalInfo(candidateId, resumeId, req));
    }

    @PatchMapping("/update/{resumeId}")
    public ResponseEntity<ResumeResponse> updateSummary(
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long resumeId,
            @RequestParam String summary) throws Exception {
        return ResponseEntity.ok(resumeService.updateSummary(candidateId, resumeId, summary));
    }

    @GetMapping("/setDefault/{resumeId}")
    public ResponseEntity<ResumeResponse> setDefault(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        return ResponseEntity.ok(resumeService.setDefaultResume(resumeId, candidateId));
    }

    @DeleteMapping("/delete/{resumeId}")
    public ResponseEntity<ApiResponse> deleteResume(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        resumeService.deleteResume(resumeId, candidateId);
        return ResponseEntity.ok(new ApiResponse("Resume delete successfully", true));
    }

}
