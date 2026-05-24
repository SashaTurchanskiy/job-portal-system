package com.zosh.job.controller;

import com.zosh.job.dto.ApiResponse;
import com.zosh.job.dto.JobCategoryResponse;
import com.zosh.job.payload.JobCategoryRequest;
import com.zosh.job.service.JobCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-categories")
@RequiredArgsConstructor
public class JobCategoryController {

    private final JobCategoryService jobCategoryService;

    @PostMapping("/create")
    public ResponseEntity<JobCategoryResponse> createJobCategory(@RequestBody @Valid JobCategoryRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobCategoryService.createCategory(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobCategoryResponse>> getAllJobCategories() {
        return ResponseEntity.ok(jobCategoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobCategoryResponse> getJobCategoryById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobCategoryService.getCategoryById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JobCategoryResponse> updateJobCategory(
            @PathVariable Long id,
            @RequestBody @Valid JobCategoryRequest request) throws Exception {
        return ResponseEntity.ok(jobCategoryService.updateCategory(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteJobCategory(
            @PathVariable Long id) throws Exception {
        jobCategoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse("Job category deleted successfully",true));
    }
}
