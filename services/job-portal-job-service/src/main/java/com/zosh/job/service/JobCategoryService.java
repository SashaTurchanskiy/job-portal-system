package com.zosh.job.service;

import com.zosh.job.dto.JobCategoryResponse;
import com.zosh.job.modal.JobCategory;
import com.zosh.job.payload.JobCategoryRequest;

import java.util.List;

public interface JobCategoryService {

    JobCategoryResponse createCategory(JobCategoryRequest request);

    List<JobCategoryResponse> getAllCategories();

    JobCategoryResponse updateCategory(Long id, JobCategoryRequest request);

    JobCategoryResponse getCategoryById(Long id);

    void deleteCategory(Long id);

    JobCategory getCategoryEntityById(Long id);
}
