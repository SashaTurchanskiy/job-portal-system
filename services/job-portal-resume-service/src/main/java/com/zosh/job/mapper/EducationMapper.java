package com.zosh.job.mapper;

import com.zosh.job.dto.response.EducationResponse;
import com.zosh.job.modal.Education;

public class EducationMapper {
    public static EducationResponse toEducationResponse(Education education){
        return EducationResponse.builder()
                .id(education.getId())
                .institutionName(education.getInstitutionName())
                .degree(education.getDegree())
                .fieldOfStudy(education.getFieldOfStudy())
                .grade(education.getGrade())
                .startDate(education.getStartDate() != null ? education.getStartDate().toString() : null)
                .endDate(education.getEndDate() != null ? education.getEndDate().toString() : null)
                .isCurrentlyStudying(education.getIsCurrentlyStudying())
                .description(education.getDescription())
                .displayOrder(education.getDisplayOrder())
                .build();
    }
}
