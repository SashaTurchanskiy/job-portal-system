package com.zosh.job.controller;

import com.zosh.job.domain.UserRole;
import com.zosh.job.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse home(){
        return new ApiResponse("Service for managing job " + UserRole.ROLE_EMPLOYER, true);
    }
}
