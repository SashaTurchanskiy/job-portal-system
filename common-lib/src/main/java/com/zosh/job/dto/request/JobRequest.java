package com.zosh.job.dto.request;

import com.zosh.job.domain.ExperienceLevel;
import com.zosh.job.domain.JobType;
import com.zosh.job.domain.WorkMode;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobRequest {

    @NotBlank(message = "Job title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    private String requirements;
    private String responsibilities;
    private String benefits;

    @NotNull(message = "Company ID is required")
    private Long categoryId;
    private Set<Long> skillIds;
    private Set<Long> tagIds;

    private String city;
    private String address;
    private String country;
    private String state;
    private String zipCode;

    @DecimalMin(value = "0.0", message = "Minimum salary must be non-negative")
    private BigDecimal minSalary;

    @DecimalMin(value = "0.0", message = "Maximum salary must be non-negative")
    private BigDecimal maxSalary;

    @NotNull(message = "Job type is required")
    private JobType jobType;

    @NotNull(message = "Work mode is required")
    private WorkMode workMode;

    @NotNull(message = "Experience level is required")
    private ExperienceLevel experienceLevel;

    @Min(value = 1, message = "There must be at least one opening")
    @Builder.Default
    private Integer openings = 1;

    private LocalDate applicationDeadline;
    private LocalDate expiresAt;

}
