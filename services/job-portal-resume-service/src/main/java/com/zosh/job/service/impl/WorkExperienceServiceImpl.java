package com.zosh.job.service.impl;

import com.zosh.job.dto.response.WorkExperienceResponse;
import com.zosh.job.mapper.WorkExperienceMapper;
import com.zosh.job.modal.Resume;
import com.zosh.job.modal.WorkExperience;
import com.zosh.job.payload.AddWorkExperienceRequest;
import com.zosh.job.repository.WorkExperienceRepo;
import com.zosh.job.service.ResumeService;
import com.zosh.job.service.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final WorkExperienceRepo workExperienceRepo;
    private final ResumeService resumeService;

    @Override
    public WorkExperienceResponse addWorkExperience(Long resumeId, Long candidateId, AddWorkExperienceRequest request) throws Exception {
        Resume resume = resumeService.getResumeEntity(resumeId);

        assertOwner(resume, candidateId);
        WorkExperience workExperience = WorkExperience.builder()
                .resume(resume)
                .companyName(request.getCompanyName())
                .companyLogoUrl(request.getCompanyLogoUrl())
                .jobTitle(request.getJobTitle())
                .employmentType(request.getEmploymentType())
                .location(request.getLocation())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isCurrentJob(Boolean.TRUE.equals(request.getIsCurrentJob()))
                .description(request.getDescription())
                .technologies(request.getTechnologies() != null ? request.getTechnologies() : List.of())
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .build();

        return WorkExperienceMapper.toWorkExperienceResponse(workExperienceRepo.save(workExperience));
    }

    @Override
    public List<WorkExperienceResponse> getWorkExperience(Long resumeId) {
        return workExperienceRepo.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                .stream()
                .map(WorkExperienceMapper::toWorkExperienceResponse)
                .toList();
    }

    @Override
    public WorkExperienceResponse updateWorkExperience(Long resumeId, Long workExperienceId, Long candidateId, AddWorkExperienceRequest request) throws Exception {
        WorkExperience workExperience = getWorkExperienceEntity(workExperienceId);

        assertOwner(workExperience.getResume(), candidateId);

        workExperience.setCompanyName(request.getCompanyName());
        workExperience.setCompanyLogoUrl(request.getCompanyLogoUrl());
        workExperience.setJobTitle(request.getJobTitle());
        workExperience.setEmploymentType(request.getEmploymentType());
        workExperience.setLocation(request.getLocation());
        workExperience.setStartDate(request.getStartDate());
        workExperience.setEndDate(request.getEndDate());
        workExperience.setIsCurrentJob(Boolean.TRUE.equals(request.getIsCurrentJob()));
        workExperience.setDescription(request.getDescription());
        if (request.getTechnologies() != null) workExperience.setTechnologies(request.getTechnologies());
        if (request.getDisplayOrder() != null) workExperience.setDisplayOrder(request.getDisplayOrder());

        return WorkExperienceMapper.toWorkExperienceResponse(workExperienceRepo.save(workExperience));
    }

    @Override
    public void deleteWorkExperience(Long resumeId, Long workExperienceId, Long candidateId) throws Exception {
        WorkExperience workExperience = getWorkExperienceEntity(workExperienceId);

        assertOwner(workExperience.getResume(), candidateId);

        workExperienceRepo.delete(workExperience);

    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if (!resume.getCandidateId().equals(candidateId)){
            throw new Exception("Unauthorized: Candidate does not own this resume");
        }
    }

    @Override
    public WorkExperience getWorkExperienceEntity(Long workExperienceId) throws Exception {
        return workExperienceRepo.findById(workExperienceId)
                .orElseThrow(()-> new Exception("Work experience not found with id: " + workExperienceId));
    }
}
