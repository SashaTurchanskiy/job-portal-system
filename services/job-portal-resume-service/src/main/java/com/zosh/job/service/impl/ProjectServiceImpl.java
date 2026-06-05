package com.zosh.job.service.impl;

import com.zosh.job.dto.response.ProjectResponse;
import com.zosh.job.mapper.ProjectMapper;
import com.zosh.job.modal.Project;
import com.zosh.job.modal.Resume;
import com.zosh.job.payload.AddProjectRequest;
import com.zosh.job.repository.ProjectRepo;
import com.zosh.job.service.ProjectService;
import com.zosh.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;
    private final ResumeService resumeService;

    @Override
    public ProjectResponse addProject(Long resumeId, Long candidateId, AddProjectRequest request) throws Exception {
        Resume resume = resumeService.getResumeEntity(resumeId);

        assertOwner(resume, candidateId);

        Project project = Project.builder()
                .resume(resume)
                .title(request.getTitle())
                .description(request.getDescription())
                .technologies(request.getTechnologies() != null ? request.getTechnologies() : List.of())
                .projectUrl(request.getProjectUrl())
                .sourceCodeUrl(request.getSourceCodeUrl())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isOngoing(Boolean.TRUE.equals(request.getIsOngoing()))
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .build();

        return ProjectMapper.toProjectResponse(projectRepo.save(project));
    }

    @Override
    public List<ProjectResponse> getAllProjects(Long resumeId) {
        return projectRepo.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                .stream()
                .map(ProjectMapper::toProjectResponse)
                .toList();
    }

    @Override
    public ProjectResponse updateProject(Long projectId, Long resumeId, Long candidateId, AddProjectRequest request) throws Exception {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(()-> new Exception("project not found with " + projectId));

        assertOwner(project.getResume(), candidateId);

        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setProjectUrl(request.getProjectUrl());
        project.setSourceCodeUrl(request.getSourceCodeUrl());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setIsOngoing(Boolean.TRUE.equals(request.getIsOngoing()));
        if (request.getTechnologies() != null) project.setTechnologies(request.getTechnologies());
        if (request.getDisplayOrder() != null) project.setDisplayOrder(request.getDisplayOrder());

        return ProjectMapper.toProjectResponse(projectRepo.save(project));
    }

    @Override
    public void deleteProject(Long projectId, Long resumeId, Long candidateId) throws Exception {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(()-> new Exception("project not found with " + projectId));

        assertOwner(project.getResume(), candidateId);
        projectRepo.delete(project);

    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if (!resume.getCandidateId().equals(candidateId)){
            throw new Exception("Unauthorized: Candidate does not own this resume");
        }
    }
}
