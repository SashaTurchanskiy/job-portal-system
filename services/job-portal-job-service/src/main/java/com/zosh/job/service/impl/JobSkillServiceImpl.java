package com.zosh.job.service.impl;

import com.zosh.job.dto.response.JobSkillResponse;
import com.zosh.job.mapper.JobSkillMapper;
import com.zosh.job.modal.JobSkill;
import com.zosh.job.payload.JobSkillRequest;
import com.zosh.job.repository.JobSkillRepository;
import com.zosh.job.service.JobSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobSkillServiceImpl implements JobSkillService {

    private final JobSkillRepository jobSkillRepository;

    @Override
    public JobSkillResponse createJobSkill(JobSkillRequest request) throws Exception {
        if (jobSkillRepository.existsByName(request.getName())){
            throw new Exception("Skill with name " + request.getName() + " already exists");
        }
        String slug = generateUniqueSlug(request.getName());
        JobSkill skill = JobSkill.builder()
                .name(request.getName())
                .slug(slug)
                .category(request.getCategory())
                .build();
        JobSkill saved = jobSkillRepository.save(skill);
        return JobSkillMapper.toResponse(saved);
    }

    @Override
    public List<JobSkillResponse> getAllJobSkills() {
        return jobSkillRepository.findByActiveTrue()
                .stream()
                .map(JobSkillMapper::toResponse)
                .toList();
    }

    @Override
    public JobSkillResponse getSkillById(Long id) throws Exception {
        JobSkill jobSkill = jobSkillRepository.findById(id).orElseThrow(
                ()-> new Exception("Skill with id " + id + " not found"));
        return JobSkillMapper.toResponse(jobSkill);
    }

    @Override
    public JobSkillResponse updateJobSkill(Long id, JobSkillRequest request) {
        return null;
    }

    @Override
    public void deleteJobSkill(Long id) {

    }

    @Override
    public Set<JobSkill> getSkillByIds(Set<Long> ids) {
        return Set.of();
    }

    private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replace("[^a-z0-9\\s-]", "")
                .trim().replaceAll("[\\s-]+", "-");

        if (!jobSkillRepository.existsBySlug(base)){
            return base;
        }
        int counter = 1;
        while (jobSkillRepository.existsBySlug(base + "-" + counter)){
            counter++;
        }
        return base + "-" + counter;

    }
}
