package com.zosh.job.mapper;

import com.zosh.job.dto.response.JobTagResponse;
import com.zosh.job.modal.JobTag;

public class JobTagMapper {

    public static JobTagResponse toTagResponse(JobTag tag){
        return JobTagResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .slug(tag.getSlug())
                .build();
    }
}
