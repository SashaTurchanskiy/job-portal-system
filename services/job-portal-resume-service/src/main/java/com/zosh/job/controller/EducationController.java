package com.zosh.job.controller;

import com.zosh.job.dto.response.EducationResponse;
import com.zosh.job.payload.AddEducationRequest;
import com.zosh.job.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resumeId}/educations")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

     // Endpoints for adding, updating, retrieving, and deleting education entries will go here

    @PostMapping("/add")
    public ResponseEntity<EducationResponse> addEducation(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody AddEducationRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(educationService.addEducation(resumeId, candidateId, request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EducationResponse>> getEducations(
            @PathVariable Long resumeId){
        return ResponseEntity.ok(educationService.getEducations(resumeId));
    }

    @PutMapping("/{educationId}/update")
    public ResponseEntity<EducationResponse> updateEducation(
            @PathVariable Long educationId,
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody AddEducationRequest request) throws Exception {
        return ResponseEntity.ok(educationService.updateEducation(educationId, resumeId, candidateId, request));
     }

     @DeleteMapping("/{educationId}/delete")
     public ResponseEntity<Void> deleteEducation(
             @PathVariable Long educationId,
             @PathVariable Long resumeId,
             @RequestHeader("X-User-Id") Long candidateId) throws Exception {
         educationService.deleteEducation(educationId, resumeId, candidateId);
         return ResponseEntity.noContent().build();
     }

}
