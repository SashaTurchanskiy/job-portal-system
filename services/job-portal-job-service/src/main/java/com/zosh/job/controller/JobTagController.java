package com.zosh.job.controller;

import com.zosh.job.dto.response.ApiResponse;
import com.zosh.job.dto.response.JobTagResponse;
import com.zosh.job.payload.JobTagRequest;
import com.zosh.job.service.JobTagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class JobTagController {

    private final JobTagService jobTagService;

    @PostMapping("/create")
    public ResponseEntity<JobTagResponse> createTag(@RequestBody @Valid JobTagRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobTagService.createTag(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobTagResponse>> getAllTags(){
        return ResponseEntity.ok(jobTagService.getAllTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobTagResponse> getById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobTagService.getTagById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JobTagResponse> updateTag(
            @PathVariable Long id,
            @RequestBody @Valid JobTagRequest request) throws Exception {
        return ResponseEntity.ok(jobTagService.updateTag(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteTag(@PathVariable Long id) throws Exception {
        jobTagService.deleteTag(id);
        return ResponseEntity.ok(new ApiResponse("Tag deleted successfully " ,true));
    }
}
