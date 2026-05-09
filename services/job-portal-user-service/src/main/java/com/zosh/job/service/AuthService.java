package com.zosh.job.service;

import com.zosh.job.payload.AuthResponse;
import com.zosh.job.payload.LoginRequest;
import com.zosh.job.payload.SignupRequest;

public interface AuthService {

    AuthResponse signup(SignupRequest request) throws Exception;
    AuthResponse login(LoginRequest request) throws Exception;
}
