package com.zosh.job.dto.response;

import com.zosh.job.domain.SkillCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobSkillResponse {

    private Long id;
    private String name;
    private String slug;
    private SkillCategory category;
    private Boolean active;

}
