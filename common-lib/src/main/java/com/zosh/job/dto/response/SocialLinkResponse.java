package com.zosh.job.dto.response;

import com.zosh.job.domain.SocialPlatform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialLinkResponse {

    private SocialPlatform platform;
    private String url;
}
