package com.zosh.job.controller;

import com.zosh.job.dto.response.ApiResponse;
import com.zosh.job.dto.response.ResumeSkillResponse;
import com.zosh.job.payload.AddResumeSkillRequest;
import com.zosh.job.service.ResumeSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resumeId}/skills")
@RequiredArgsConstructor
public class ResumeSkillController {

    private final ResumeSkillService resumeSkillService;

    @PostMapping("/add")
    public ResponseEntity<ResumeSkillResponse> addResumeSkill(
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long resumeId,
            AddResumeSkillRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(resumeSkillService.addResumeSkill(resumeId, candidateId, request));
    }

    @GetMapping("/skills")
    public ResponseEntity<List<ResumeSkillResponse>> getSkills(
            @PathVariable Long resumeId){
        return ResponseEntity.ok(resumeSkillService.getSkills(resumeId));
    }

    @PutMapping("/update/{skillId}")
    public ResponseEntity<ResumeSkillResponse> updateResumeSkill(
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long resumeId,
            @PathVariable Long skillId,
            AddResumeSkillRequest request) throws Exception {
        return ResponseEntity.ok(resumeSkillService.updateSkill(skillId, resumeId, candidateId, request));
    }

    @DeleteMapping("/delete/{skillId}")
    public ResponseEntity<ApiResponse> deleteResumeSkill(
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long resumeId,
            @PathVariable Long skillId) throws Exception {
        resumeSkillService.deleteSkill(skillId, resumeId, candidateId);
        return ResponseEntity.ok(new ApiResponse("Skill deleted successfully", true));
    }

}
