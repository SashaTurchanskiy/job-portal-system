package com.zosh.job.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobCategoryRequest {

    @NotBlank(message = "Category name is required")
    private String name;

    @Size(max = 255, message = "Slug must be less than 255 characters")
    private String description;

    private String iconUrl;
    private Long parentId;


}
