package com.zosh.job.mapper;

import com.zosh.job.dto.PersonalInfoResponse;
import com.zosh.job.dto.response.ResumeResponse;

import com.zosh.job.dto.response.ResumeSkillResponse;
import com.zosh.job.modal.PersonalInfo;
import com.zosh.job.modal.Resume;
import com.zosh.job.modal.ResumeSkill;
import com.zosh.job.payload.CreateResumeRequest;

public class ResumeMapper {

    public static PersonalInfoResponse toPersonalInfoResponse(PersonalInfo personalInfo){
        return PersonalInfoResponse.builder()
                .firstName(personalInfo.getFirstName())
                .lastName(personalInfo.getLastName())
                .email(personalInfo.getEmail())
                .phone(personalInfo.getPhone())
                .headline(personalInfo.getHeadline())
                .country(personalInfo.getCountry())
                .city(personalInfo.getCity())
                .githubUrl(personalInfo.getGithubUrl())
                .linkedinUrl(personalInfo.getLinkedinUrl())
                .portfolioUrl(personalInfo.getPortfolioUrl())
                .websiteUrl(personalInfo.getWebsiteUrl())
                .build();
    }

    public static ResumeResponse toResumeResponse(Resume resume){
        return ResumeResponse.builder()
                .id(resume.getId())
                .candidateId(resume.getCandidateId())
                .title(resume.getTitle())
                .template(resume.getTemplate())
                .visibility(resume.getVisibility())
                .isDefault(resume.getIsDefault())
                .personalInfo(ResumeMapper.toPersonalInfoResponse(resume.getPersonalInfo()))
                .summary(resume.getSummary())
                .completionScore(resume.getCompletionScore())
                .createdAt(resume.getCreatedAt())
                .updatedAt(resume.getUpdatedAt())
                .build();
    }

    public static ResumeSkillResponse toSkillResponse(ResumeSkill skill){
        return ResumeSkillResponse.builder()
                .id(skill.getId())
                .skillName(skill.getSkillName())
                .proficiencyLevel(skill.getProficiencyLevel())
                .yearsOfExperience(skill.getYearsOfExperience())
                .displayOrder(skill.getDisplayOrder())
                .build();
    }

  /*  public static Resume toDTO(CreateResumeRequest response){
        return Resume.builder()
                .title(response.getTitle())
                .template(response.getTemplate())
                .visibility(response.getVisibility())
                .isDefault(response.getIsDefault())
                .build();
    }*/
}
