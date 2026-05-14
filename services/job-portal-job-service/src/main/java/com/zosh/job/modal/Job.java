package com.zosh.job.modal;

import com.zosh.job.domain.ExperienceLevel;
import com.zosh.job.domain.JobStatus;
import com.zosh.job.domain.JobType;
import com.zosh.job.domain.WorkMode;
import com.zosh.job.modal.embeddable.JobLocation;
import com.zosh.job.modal.embeddable.SalaryRange;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String requirements;

    private String responsibilities;

    private String benefits;

    @Column(nullable = false)
    private Long companyId;

    //private JobCategory category;
    //private Set<JobSkill> skills;
    //private Set<JobTag> tags;

    @Embedded
    private JobLocation location;

    @Embedded
    private SalaryRange salaryRange;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobType jobType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkMode workMode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExperienceLevel experienceLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus status = JobStatus.DRAFT;

    private Integer openings = 1;

    private LocalDate applicationDeadline;

    private LocalDate expiresAt;

    private Boolean active = true;

    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;





}
