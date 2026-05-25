package com.zosh.job.service.impl;

import com.zosh.job.dto.response.JobTagResponse;
import com.zosh.job.mapper.JobTagMapper;
import com.zosh.job.modal.JobTag;
import com.zosh.job.payload.JobSkillRequest;
import com.zosh.job.payload.JobTagRequest;
import com.zosh.job.repository.JobTagRepository;
import com.zosh.job.service.JobTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobTagServiceImpl implements JobTagService {

    private final JobTagRepository jobTagRepository;

    @Override
    public JobTagResponse createTag(JobSkillRequest request) throws Exception {
        if (jobTagRepository.existsByName(request.getName())){
            throw new Exception("Job Tag with name '" + request.getName() + "' already exists.");
        }
        String slug = generateUniqueSlug(request.getName());

        JobTag tag = JobTag.builder()
                .name(request.getName())
                .slug(slug)
                .build();
        return JobTagMapper.toTagResponse(jobTagRepository.save(tag));
    }

    @Override
    public List<JobTagResponse> getAllTags() {
        return List.of();
    }

    @Override
    public JobTagResponse updateTag(Long id, JobTagRequest request) {
        return null;
    }

    @Override
    public JobTagResponse getTagById(Long id) {
        return null;
    }

    @Override
    public void deleteTag(Long id) {

    }

    @Override
    public JobTag getTagEntityById(Long id) {
        return null;
    }

    private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replace("[^a-z0-9\\s-]", "")
                .trim().replaceAll("[\\s-]+", "-");

        if (!jobTagRepository.existsBySlug(base)){
            return base;
        }
        int counter = 1;
        while (jobTagRepository.existsBySlug(base + "-" + counter)){
            counter++;
        }
        return base + "-" + counter;

    }
}
