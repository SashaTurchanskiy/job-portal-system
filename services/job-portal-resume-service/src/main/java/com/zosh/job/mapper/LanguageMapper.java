package com.zosh.job.mapper;

import com.zosh.job.dto.response.LanguageResponse;
import com.zosh.job.modal.Language;

public class LanguageMapper {
    public static LanguageResponse toLanguageResponse(Language language){
        return LanguageResponse.builder()
                .id(language.getId())
                .languageName(language.getLanguageName())
                .proficiency(language.getProficiency())
                .displayOrder(language.getDisplayOrder())
                .build();
    }
}
