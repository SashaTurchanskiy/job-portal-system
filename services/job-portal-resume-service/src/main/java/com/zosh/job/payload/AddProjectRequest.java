package com.zosh.job.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddProjectRequest {

    @NotBlank(message = "Project title is required")
    private String title;

    private String description;
    private List<String> technologies;

    @Pattern(regexp = "^(http?://)*", message = "Project URL must be valid")
    private String projectUrl;

    @Pattern(regexp = "^(http?://)*", message = "Source code URL must be valid")
    private String sourceCodeUrl;

    private LocalDate startDate;
    private LocalDate endDate;


    private Boolean isOngoing = false;

    private Integer displayOrder;

}
