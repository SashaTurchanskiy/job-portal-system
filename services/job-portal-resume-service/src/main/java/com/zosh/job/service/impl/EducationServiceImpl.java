package com.zosh.job.service.impl;

import com.zosh.job.dto.response.EducationResponse;
import com.zosh.job.mapper.EducationMapper;
import com.zosh.job.modal.Education;
import com.zosh.job.modal.Resume;
import com.zosh.job.payload.AddEducationRequest;
import com.zosh.job.repository.EducationRepo;
import com.zosh.job.service.EducationService;
import com.zosh.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepo educationRepo;
    private final ResumeService resumeService;

    @Override
    public EducationResponse addEducation(Long resumeId, Long candidateId, AddEducationRequest request) throws Exception {
        Resume resume = resumeService.getResumeEntity(resumeId);

        assertOwner(resume, candidateId);

        Education education = Education.builder()
                .resume(resume)
                .grade(request.getGrade())
                .fieldOfStudy(request.getFieldOfStudy())
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .institutionName(request.getInstitutionName())
                .degree(request.getDegree())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isCurrentlyStudying(Boolean.TRUE.equals(request.getIsCurrentlyStudying()))
                .description(request.getDescription())
                .build();

        return EducationMapper.toEducationResponse(educationRepo.save(education));
    }

    @Override
    public List<EducationResponse> getEducations(Long resumeId) {
        return List.of();
    }

    @Override
    public EducationResponse updateEducation(Long educationId, Long resumeId, Long candidateId, AddEducationRequest request) {
        return null;
    }

    @Override
    public void deleteEducation(Long educationId, Long resumeId, Long candidateId) {

    }

    @Override
    public Education getEducationEntity(Long educationId) throws Exception {
        return educationRepo.findById(educationId)
                .orElseThrow(()-> new Exception("Education not found with id: " + educationId));
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if (!resume.getCandidateId().equals(candidateId)){
            throw new Exception("Unauthorized: Candidate does not own this resume");
        }
    }
}
