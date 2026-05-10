package com.zosh.job.service.impl;

import com.zosh.job.domain.UserStatus;
import com.zosh.job.dto.response.UserResponse;
import com.zosh.job.mapper.UserMapper;
import com.zosh.job.modal.User;
import com.zosh.job.payload.UpdateUserRequest;
import com.zosh.job.repository.UserRepo;
import com.zosh.job.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User getUserFromEmail(String email) throws Exception {
        User user = userRepo.findByEmail(email);
        if (user == null){
            throw new Exception("User not found with given email " + email);
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws Exception {
        return userRepo.findById(id)
                .orElseThrow(()-> new Exception("User not found with given Id " + id));
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public UserResponse updateUser(String email, UpdateUserRequest request) throws Exception {
        User user = getUserFromEmail(email);

        if (request.getFullName() != null){
            user.setFullName(request.getFullName());
        }
        if (request.getPhone() != null){
            user.setPhone(request.getPhone());
        }
        if (request.getProfileImage() != null){
            user.setProfileImage(request.getProfileImage());
        }
        return UserMapper.toDTO(userRepo.save(user));
    }

    @Override
    public UserResponse suspendUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.SUSPEND);
        user.setSuspendedAt(LocalDateTime.now());
        return UserMapper.toDTO(userRepo.save(user));
    }

    @Override
    public UserResponse activateUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.ACTIVE);
        user.setSuspendedAt(null);
        return UserMapper.toDTO(userRepo.save(user));
    }

    @Override
    public UserResponse deleteUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.DELETED);
        user.setDeletedAt(LocalDateTime.now());
        return UserMapper.toDTO(userRepo.save(user));
    }
}
