package com.zosh.job.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationResponse {

    private Long id;
    private String institutionName;
    private String degree;
    private String fieldOfStudy;
    private String grade;
    private String startDate; // ISO format: YYYY-MM-DD
    private String endDate;   // ISO format: YYYY-MM-DD
    private Boolean isCurrentlyStudying;
    private String description;
    private Integer displayOrder;


}
