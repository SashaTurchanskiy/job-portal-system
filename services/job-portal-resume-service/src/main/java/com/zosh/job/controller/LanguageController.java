package com.zosh.job.controller;

import com.zosh.job.dto.response.ApiResponse;
import com.zosh.job.dto.response.LanguageResponse;
import com.zosh.job.payload.AddLanguageRequest;
import com.zosh.job.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume/{resumeId}/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping("/add")
    public ResponseEntity<LanguageResponse> addLanguage(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody AddLanguageRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(languageService.addLanguage(resumeId, candidateId, request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LanguageResponse>> getAllLanguages(@PathVariable Long resumeId){
        return ResponseEntity.ok(languageService.getLanguages(resumeId));
    }

    @PutMapping("/update/{languageId}")
    public ResponseEntity<LanguageResponse> updateLanguage(
            @PathVariable Long resumeId,
            @PathVariable Long languageId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody AddLanguageRequest request) throws Exception {
        return ResponseEntity.ok(languageService.updateLanguage(resumeId, languageId, candidateId, request));
    }

    @DeleteMapping("/delete/{languageId}")
    public ResponseEntity<ApiResponse> deleteLanguage(
            @PathVariable Long resumeId,
            @PathVariable Long languageId,
            @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        languageService.deleteLanguage(resumeId, languageId, candidateId);
        return ResponseEntity.ok(new ApiResponse("Language deleted successfully", true));
    }



}
