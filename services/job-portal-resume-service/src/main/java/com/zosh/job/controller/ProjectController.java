package com.zosh.job.controller;

import com.zosh.job.dto.response.ApiResponse;
import com.zosh.job.dto.response.ProjectResponse;
import com.zosh.job.payload.AddProjectRequest;
import com.zosh.job.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/api/resume/{resumeId}/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/add")
    public ResponseEntity<ProjectResponse> addProject(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody AddProjectRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectService.addProject(resumeId, candidateId, request));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects(
            @PathVariable Long resumeId
    ){
        return ResponseEntity.ok(projectService.getAllProjects(resumeId));
    }

    @PutMapping("/update/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long resumeId,
            @PathVariable Long projectId,
            @RequestHeader ("X-User-Id") Long candidateId,
            @RequestBody AddProjectRequest request) throws Exception {

        return ResponseEntity.ok(projectService.updateProject(resumeId, projectId, candidateId, request));
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<ApiResponse> deleteProject(
            @PathVariable Long resumeId,
            @PathVariable Long projectId,
            @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        projectService.deleteProject(resumeId, projectId, candidateId);
        return ResponseEntity.ok(new ApiResponse("Project deleted successfully", true));
    }
}
