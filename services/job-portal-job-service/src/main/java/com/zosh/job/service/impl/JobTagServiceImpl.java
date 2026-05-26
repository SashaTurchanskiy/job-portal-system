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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobTagServiceImpl implements JobTagService {

    private final JobTagRepository jobTagRepository;

    @Override
    public JobTagResponse createTag(JobTagRequest request) throws Exception {
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
        return jobTagRepository.findAll()
                .stream()
                .map(JobTagMapper::toTagResponse)
                .toList();
    }

    @Override
    public JobTagResponse updateTag(Long id, JobTagRequest request) throws Exception {
        JobTag jobTag = getTagEntityById(id);

        if (!jobTag.getName().equals(request.getName())
        && jobTagRepository.existsByName(request.getName())){
            throw new Exception("tag with name " + request.getName() + "already exists");
        }

        jobTag.setName(request.getName());
        return JobTagMapper.toTagResponse(jobTagRepository.save(jobTag));
    }

    @Override
    public JobTagResponse getTagById(Long id) throws Exception {
        JobTag jobTag = getTagEntityById(id);
        return JobTagMapper.toTagResponse(jobTagRepository.save(jobTag));
    }

    @Override
    public void deleteTag(Long id) throws Exception {
        JobTag jobTag = getTagEntityById(id);
        jobTagRepository.delete(jobTag);
    }

    @Override
    public JobTag getTagEntityById(Long id) throws Exception {
        return jobTagRepository.findById(id)
                .orElseThrow(()-> new Exception("Job tag not found with : " + id));
    }

    @Override
    public Set<JobTag> getTagsByIds(Set<Long> ids) {
        List<JobTag> tags = jobTagRepository.findAllById(ids);
        return new HashSet<>();
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
