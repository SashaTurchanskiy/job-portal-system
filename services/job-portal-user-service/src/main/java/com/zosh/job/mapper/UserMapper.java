package com.zosh.job.mapper;

import com.zosh.job.dto.response.UserResponse;
import com.zosh.job.modal.User;

public class UserMapper {

    public static UserResponse toDTO(User user){
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setPhone(user.getPhone());
        dto.setProfileImage(user.getProfileImage());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        dto.setLastLogin(user.getLastLogin());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }
}
