package com.zosh.job.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponse {

    private Long id;
    private String title;
    private List<String> technologies;
    private String projectUrl;
    private String sourceCodeUrl;
    private String startDate;
    private String endDate;
    private String isOngoing;
    private Integer displayOrder;
}
