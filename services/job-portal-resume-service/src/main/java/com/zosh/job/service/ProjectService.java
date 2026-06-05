package com.zosh.job.service;


import com.zosh.job.dto.response.ProjectResponse;
import com.zosh.job.payload.AddProjectRequest;

import java.util.List;

public interface ProjectService {

    ProjectResponse addProject(Long resumeId, Long candidateId, AddProjectRequest request) throws Exception;

    List<ProjectResponse> getAllProjects(Long resumeId);

    ProjectResponse updateProject(Long projectId, Long resumeId, Long candidateId, AddProjectRequest request) throws Exception;

    void deleteProject(Long projectId, Long resumeId, Long candidateId) throws Exception;
}
