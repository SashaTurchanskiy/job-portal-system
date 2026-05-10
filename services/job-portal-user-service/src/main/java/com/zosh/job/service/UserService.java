package com.zosh.job.service;

import com.zosh.job.dto.response.UserResponse;
import com.zosh.job.modal.User;
import com.zosh.job.payload.UpdateUserRequest;

import java.util.List;

public interface UserService {

    User getUserFromEmail(String email) throws Exception;

    User getUserById(Long id) throws Exception;

    List<User> getAllUser();

    UserResponse updateUser(String email, UpdateUserRequest request) throws Exception;

    //admin action
    UserResponse suspendUser(Long id) throws Exception;
    UserResponse activateUser(Long id) throws Exception;
    UserResponse deleteUser(Long id) throws Exception;


}
