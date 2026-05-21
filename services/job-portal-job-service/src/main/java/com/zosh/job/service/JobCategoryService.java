package com.zosh.job.service;

import com.zosh.job.dto.JobCategoryResponse;
import com.zosh.job.modal.JobCategory;
import com.zosh.job.payload.JobCategoryRequest;

import java.util.List;

public interface JobCategoryService {

    JobCategoryResponse createCategory(JobCategoryRequest request) throws Exception;

    List<JobCategoryResponse> getAllCategories();

    JobCategoryResponse updateCategory(Long id, JobCategoryRequest request) throws Exception;

    JobCategoryResponse getCategoryById(Long id) throws Exception;

    void deleteCategory(Long id) throws Exception;

    JobCategory getCategoryEntityById(Long id) throws Exception;
}
