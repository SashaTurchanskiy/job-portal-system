package com.zosh.job.dto.request;

import com.zosh.job.domain.CompanySize;
import com.zosh.job.domain.CompanyType;
import com.zosh.job.domain.IndustryType;
import com.zosh.job.dto.response.SocialLinkResponse;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyRequest {

    @NotBlank(message = "Company name is required")
    private String name;

    private String tagline;

    private String description;

    private String logoUrl;
    private String coveImageUrl;

    @Pattern(regexp = "^(http?://).*", message = "Website must be a valid URL")
    private String website;

    @Email(message = "Company email must be valid")
    private String email;

    private String phone;

    @Min(value = 1800, message = "Founded year seems too old")
    @Max(value = 2100, message = "Founded year is invalid")
    private Integer foundedYear;

    @NotNull(message = "Company size is required")
    private CompanySize companySize;

    @NotNull(message = "Company type is required")
    private CompanyType companyType;

    @NotNull(message = "Industry type is required")
    private IndustryType industryType;

    private String registrationNumber;

    private List<SocialLinkResponse> socialLinks;

}
