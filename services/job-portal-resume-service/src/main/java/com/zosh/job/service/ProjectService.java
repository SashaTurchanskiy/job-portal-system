package com.zosh.job.service;


import com.zosh.job.dto.response.ProjectResponse;
import com.zosh.job.payload.AddProjectRequest;

import java.util.List;

public interface ProjectService {

    ProjectResponse addProject(Long resumeId, Long candidateId, AddProjectRequest request);

    List<ProjectResponse> getAllProjects(Long resumeId);

    ProjectResponse updateProject(Long projectId, Long resumeId, Long candidateId, AddProjectRequest request);

    void deleteProject(Long projectId, Long resumeId, Long candidateId);
}
