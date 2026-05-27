package com.zosh.job.controller;

import com.zosh.job.domain.JobStatus;
import com.zosh.job.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resume")
public class HomeController {

    @GetMapping("/get")
    public ApiResponse Home(){
        return new ApiResponse("Created new service " + JobStatus.CLOSED, true);
    }
}
