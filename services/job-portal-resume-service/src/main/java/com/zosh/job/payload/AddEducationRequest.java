package com.zosh.job.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddEducationRequest {

    @NotNull(message = "Institution name is required")
    private String institutionName;

    @NotNull(message = "Degree is required")
    private String degree;
    private String fieldOfStudy;
    private String grade;

    @NotNull(message = "Start date is required")
    private LocalDate startDate; // ISO format: "YYYY-MM-DD"
    private LocalDate endDate;   // ISO format: "YYYY-MM-DD"

    @Builder.Default
    private Boolean isCurrentlyStudying = false;
    private String description;
    private Integer displayOrder;
}
