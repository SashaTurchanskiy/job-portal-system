package com.zosh.job.service.impl;

import com.zosh.job.dto.response.ResumeSkillResponse;
import com.zosh.job.mapper.ResumeMapper;
import com.zosh.job.modal.Resume;
import com.zosh.job.modal.ResumeSkill;
import com.zosh.job.payload.AddResumeSkillRequest;
import com.zosh.job.repository.ResumeSkillRepository;
import com.zosh.job.service.ResumeService;
import com.zosh.job.service.ResumeSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeSkillServiceImpl implements ResumeSkillService {

    private final ResumeSkillRepository resumeSkillRepository;
    private final ResumeService resumeService;

    @Override
    public ResumeSkillResponse addResumeSkill(Long resumeId, Long candidateId, AddResumeSkillRequest request) throws Exception {
        Resume resume = resumeService.getResumeEntity(resumeId);

        assertOwner(resume, candidateId);

        ResumeSkill resumeSkill = ResumeSkill.builder()
                .resume(resume)
                .skillName(request.getSkillName())
                .proficiencyLevel(request.getProficiencyLevel())
                .yearsOfExperience(request.getYearsOfExperience())
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .build();

        return ResumeMapper.toSkillResponse(resumeSkillRepository.save(resumeSkill));
    }

    @Override
    public List<ResumeSkillResponse> getSkills(Long resumeId) {
        return resumeSkillRepository.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                .stream()
                .map(ResumeMapper::toSkillResponse)
                .toList();
    }

    @Override
    public ResumeSkillResponse updateSkill(Long skillId, Long resumeId, Long candidateId, AddResumeSkillRequest request) throws Exception {
        ResumeSkill resumeSkill = getResumeSkillEntity(skillId);

        assertOwner(resumeSkill.getResume(), candidateId);

        resumeSkill.setSkillName(resumeSkill.getSkillName());
        resumeSkill.setProficiencyLevel(resumeSkill.getProficiencyLevel());
        resumeSkill.setYearsOfExperience(resumeSkill.getYearsOfExperience());
        resumeSkill.setDisplayOrder(resumeSkill.getDisplayOrder());

        return ResumeMapper.toSkillResponse(resumeSkillRepository.save(resumeSkill));
    }

    @Override
    public void deleteSkill(Long skillId, Long resumeId, Long candidateId) throws Exception {
        ResumeSkill resumeSkill = getResumeSkillEntity(skillId);

        assertOwner(resumeSkill.getResume(), candidateId);

        resumeSkillRepository.delete(resumeSkill);
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if (!resume.getCandidateId().equals(candidateId)){
            throw new Exception("Unauthorized: Candidate does not own this resume");
        }

    }

    @Override
    public ResumeSkill getResumeSkillEntity(Long skillId) throws Exception {
        return resumeSkillRepository.findById(skillId)
                .orElseThrow(()-> new Exception("Resume skill not found with id: " + skillId));
    }
}
