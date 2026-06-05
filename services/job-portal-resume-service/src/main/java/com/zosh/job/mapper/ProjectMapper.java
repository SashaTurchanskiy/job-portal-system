package com.zosh.job.mapper;

import com.zosh.job.dto.response.ProjectResponse;
import com.zosh.job.modal.Project;

public class ProjectMapper {
    public static ProjectResponse toProjectResponse(Project project){

        if (project == null) return null;

        return ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .projectUrl(project.getProjectUrl())
                .sourceCodeUrl(project.getSourceCodeUrl())
                .displayOrder(project.getDisplayOrder())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .isOngoing(project.getIsOngoing())
                .technologies(project.getTechnologies())
                .build();
    }
}
