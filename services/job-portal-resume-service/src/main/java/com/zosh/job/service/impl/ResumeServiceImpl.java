package com.zosh.job.service.impl;


import com.zosh.job.dto.PersonalInfoResponse;
import com.zosh.job.dto.response.ResumeResponse;
import com.zosh.job.mapper.ResumeMapper;
import com.zosh.job.modal.PersonalInfo;
import com.zosh.job.modal.Resume;
import com.zosh.job.payload.CreateResumeRequest;
import com.zosh.job.repository.ResumeRepo;
import com.zosh.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepo resumeRepo;

    @Override
    public ResumeResponse createResume(Long candidateId, CreateResumeRequest request) {

        if (Boolean.TRUE.equals(request.getIsDefault())) {
            resumeRepo.findByCandidateIdAndIsDefaultTrue(candidateId)
                    .ifPresent(existing -> {
                        existing.setIsDefault(false);
                        resumeRepo.save(existing);
                    });
        }
            Resume resume = Resume.builder()
                    .candidateId(candidateId)
                    .title(request.getTitle())
                    .template(request.getTemplate())
                    .visibility(request.getVisibility())
                    .isDefault(Boolean.TRUE.equals(request.getIsDefault()))
                    .isActive(true)
                    .build();

            return buildFullResponse(resumeRepo.save(resume));

    }

    @Override
    public ResumeResponse getResumeById(Long resumeId, Long candidateId) throws Exception {
        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume, candidateId);
        return buildFullResponse(resumeRepo.save(resume));
    }

    @Override
    public List<ResumeResponse> getMyResume(Long candidateId) {
        return resumeRepo.findByCandidateIdAndIsActiveTrue(candidateId)
                .stream()
                .map(this::buildFullResponse)
                .toList();
    }

    @Override
    public ResumeResponse updatePersonalInfo(Long resumeId, Long candidateId, PersonalInfoResponse req) throws Exception {

        Resume resume = getResumeEntity(resumeId);

        assertOwner(resume, candidateId);

        PersonalInfo info = resume.getPersonalInfo();
        if (info == null) info = new PersonalInfo();
/*
        if (req.getFirstName() != null)
            info.setFirstName(req.getFirstName());
        if (req.getLastName() != null)
            info.setLastName(req.getLastName());
        if (req.getHeadline() != null)
            info.setHeadline(req.getHeadline());
        if (req.getEmail() != null)
            info.setEmail(req.getEmail());
        if (req.getPhone() != null)
            info.setPhone(req.getPhone());
        if (req.getCity() != null)
            info.setCity(req.getCity());
        if (req.getCountry() != null)
            info.setCountry(req.getCountry());
        if (req.getLinkedinUrl() != null)
            info.setLinkedinUrl(req.getLinkedinUrl());
        if (req.getGithubUrl() != null)
            info.setGithubUrl(req.getGithubUrl());
        if (req.getPortfolioUrl() != null)
            info.setPortfolioUrl(req.getPortfolioUrl());
        if (req.getWebsiteUrl() != null)
            info.setWebsiteUrl(req.getWebsiteUrl());*/
        ifNotNull(req.getFirstName(), info::setFirstName);
        ifNotNull(req.getLastName(), info::setLastName);
        ifNotNull(req.getHeadline(), info::setHeadline);
        ifNotNull(req.getEmail(), info::setEmail);
        ifNotNull(req.getPhone(), info::setPhone);
        ifNotNull(req.getCity(), info::setCity);
        ifNotNull(req.getCountry(), info::setCountry);
        ifNotNull(req.getLinkedinUrl(), info::setLinkedinUrl);
        ifNotNull(req.getGithubUrl(), info::setGithubUrl);
        ifNotNull(req.getPortfolioUrl(), info::setPortfolioUrl);
        ifNotNull(req.getWebsiteUrl(), info::setWebsiteUrl);

        resume.setPersonalInfo(info);
        return buildFullResponse(resumeRepo.save(resume));
    }

    @Override
    public ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary) throws Exception {
        Resume resume = getResumeEntity(resumeId);

        assertOwner(resume, candidateId);

        resume.setSummary(summary);

        return buildFullResponse(resumeRepo.save(resume));
    }

    @Override
    public ResumeResponse setDefaultResume(Long resumeId, Long candidateId) throws Exception {
        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume, candidateId);

            resumeRepo.findByCandidateIdAndIsDefaultTrue(candidateId)
                    .ifPresent(existing -> {
                        existing.setIsDefault(false);
                        resumeRepo.save(existing);
                    });

        resume.setIsDefault(true);
        return buildFullResponse(resumeRepo.save(resume));
    }

    @Override
    public void deleteResume(Long resumeId, Long candidateId) throws Exception {
        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume, candidateId);
        resume.setIsActive(false);
        resume.setIsDefault(false);
        resumeRepo.save(resume);
    }

    @Override
    public Resume getResumeEntity(Long resumeId) throws Exception {
        return resumeRepo.findById(resumeId)
                .orElseThrow(()-> new Exception("resume not found with " + resumeId));
    }

    private ResumeResponse buildFullResponse(Resume resume){
        return ResumeMapper.toResumeResponse(resume);
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if (!resume.getCandidateId().equals(candidateId)){
            throw new Exception("resume not found with id");
        }
    }
    private static <T> void ifNotNull(T value, Consumer<T> setter){
        if (value != null){
            setter.accept(value);
        }
    }
}
