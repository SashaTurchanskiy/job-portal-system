package com.zosh.job.dto.response;

import com.zosh.job.domain.ResumeTemplate;
import com.zosh.job.domain.ResumeVisibility;
import com.zosh.job.dto.PersonalInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeResponse {

    private Long id;
    private Long candidateId;
    private String title;
    private ResumeTemplate template;
    private ResumeVisibility visibility;
    private Boolean isDefault;
    private PersonalInfoResponse personalInfo;
    private String summary;
    private Integer completionScore;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //todo:

    /*private List<WorkExperinceResponse> workExperience;
    private List<EducationResponse> education;
    private List<ResumeSkillResponse> skills;
    private List<ProjectResponse> projects;
    private List<CertificationResponse> certification;
    private List<AwardResponse> awards;
    private List<LanguageResponse> langauges;*/


}
