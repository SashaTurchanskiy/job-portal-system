package com.zosh.job.modal;

import com.zosh.job.domain.SocialPlatform;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialLink {

    private SocialPlatform platform;
    private String url;
}
