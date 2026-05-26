package com.zosh.job.mapper;

import com.zosh.job.dto.response.CompanyResponse;
import com.zosh.job.dto.response.JobResponse;
import com.zosh.job.dto.response.JobSkillResponse;
import com.zosh.job.dto.response.JobTagResponse;
import com.zosh.job.modal.Job;
import com.zosh.job.modal.JobSkill;
import com.zosh.job.modal.embeddable.JobLocation;
import com.zosh.job.modal.embeddable.SalaryRange;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class JobMapper {

    public static JobResponse toResponse(Job job, CompanyResponse companyResponse){

        JobLocation loc = job.getLocation();
        SalaryRange sal = job.getSalaryRange();

        Set<JobSkillResponse> skills = job.getSkills()==null ?
                Collections.emptySet()
                :job.getSkills().stream().map(JobSkillMapper::toResponse)
                .collect(Collectors.toSet());

        Set<JobTagResponse> tags = job.getTags()==null ?
                Collections.emptySet()
                :job.getTags().stream().map(JobTagMapper::toTagResponse)
                .collect(Collectors.toSet());


        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .requirements(job.getRequirements())
                .responsibilities(job.getResponsibilities())
                .benefits(job.getBenefits())
                .company(companyResponse)
                .category(JobCategoryMapper.toJobCategoryResponse(job.getCategory(), false))
                .skills(skills)
                .tags(tags)
                //location
                .city(loc.getCity())
                .address(loc.getAddress())
                .country(loc.getCountry())
                .state(loc.getState())
                .zipCode(loc.getZipCode())
                //salary
                .minSalary(sal.getMinSalary() != null ? sal.getMinSalary().toString() : null)
                .maxSalary(sal.getMaxSalary() != null ? sal.getMaxSalary().toString() : null)
                //classification
                .jobType(job.getJobType())
                .workMode(job.getWorkMode())
                .experienceLevel(job.getExperienceLevel())
                .status(job.getStatus())
                //posting details
                .openings(job.getOpenings())
                .applicationDeadline(job.getApplicationDeadline())
                .expiresAt(job.getExpiresAt())
                .active(job.getActive())
                //timestamps
                .createdAt(job.getCreatedAt())
                .updatedAt(job.getUpdatedAt())
                .publishedAt(job.getPublishedAt())
                .closedAt(job.getClosedAt())
                .build();

    }
}
