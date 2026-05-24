package com.zosh.job.mapper;

import com.zosh.job.domain.SkillCategory;
import com.zosh.job.dto.response.JobSkillResponse;
import com.zosh.job.modal.JobSkill;

public class JobSkillMapper {
    public static JobSkillResponse toResponse(JobSkill jobSkill){
        return JobSkillResponse.builder()
                .id(jobSkill.getId())
                .name(jobSkill.getName())
                .slug(jobSkill.getSlug())
                .active(jobSkill.getActive())
                .category(jobSkill.getCategory())
                .build();
    }
}
