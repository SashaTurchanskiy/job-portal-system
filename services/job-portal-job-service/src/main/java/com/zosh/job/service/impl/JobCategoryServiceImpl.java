package com.zosh.job.service.impl;

import com.zosh.job.dto.response.JobCategoryResponse;
import com.zosh.job.mapper.JobCategoryMapper;
import com.zosh.job.modal.JobCategory;
import com.zosh.job.payload.JobCategoryRequest;
import com.zosh.job.repository.JobCategoryRepo;
import com.zosh.job.service.JobCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobCategoryServiceImpl implements JobCategoryService {

    private final JobCategoryRepo jobCategoryRepo;

    @Override
    public JobCategoryResponse createCategory(JobCategoryRequest request) throws Exception {

        if (jobCategoryRepo.existsByName(request.getName())){
            throw new Exception("Job Category with name '" + request.getName() + "' already exists.");
        }
        JobCategory parent = null;
        if (request.getParentId() != null){
            parent = getCategoryEntityById(request.getParentId());
        }

        String slug = generateUniqueSlug(request.getName());

        JobCategory category = JobCategory.builder()
                .name(request.getName())
                .description(request.getDescription())
                .iconUrl(request.getIconUrl())
                .parent(parent)
                .slug(slug)
                .build();

        JobCategory saved = jobCategoryRepo.save(category);

        return JobCategoryMapper.toJobCategoryResponse(saved, true);
    }

    @Override
    public List<JobCategoryResponse> getAllCategories() {
        return jobCategoryRepo.findByActiveTrue().stream()
                .filter(cat -> cat.getParent() == null)
                .map(cat -> JobCategoryMapper.toJobCategoryResponse(cat, false))
                .toList();
    }

    @Override
    public JobCategoryResponse updateCategory(Long id, JobCategoryRequest request) throws Exception {
        JobCategory category = getCategoryEntityById(id);

        if (!category.getName().equals(request.getName()) &&
            jobCategoryRepo.existsByName(request.getName())){
            throw new Exception("Job Category with name '" + request.getName() + "' already exists.");
        }
        JobCategory parent = null;
        if (request.getParentId() != null){
            if (request.getParentId().equals(id)){
                throw new Exception("A category cannot be its own parent.");
            }
            parent = getCategoryEntityById(request.getParentId());
        }
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setIconUrl(request.getIconUrl());
        category.setParent(parent);

        return JobCategoryMapper.toJobCategoryResponse(jobCategoryRepo.save(category), false);
    }

    @Override
    public JobCategoryResponse getCategoryById(Long id) throws Exception {
        JobCategory category = getCategoryEntityById(id);
        return JobCategoryMapper.toJobCategoryResponse(category, false);
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        JobCategory category = getCategoryEntityById(id);
        category.setActive(false);
        jobCategoryRepo.delete(category);

    }

    @Override
    public JobCategory getCategoryEntityById(Long id) throws Exception {
        return jobCategoryRepo.findById(id).orElseThrow(
                ()-> new Exception("Job Category not found with id: " + id)
        );
    }

    private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replace("[^a-z0-9\\s-]", "")
                .trim().replaceAll("[\\s-]+", "-");

        if (!jobCategoryRepo.existsBySlug(base)){
            return base;
        }
        int counter = 1;
        while (jobCategoryRepo.existsBySlug(base + "-" + counter)){
            counter++;
        }
        return base + "-" + counter;

    }
}
