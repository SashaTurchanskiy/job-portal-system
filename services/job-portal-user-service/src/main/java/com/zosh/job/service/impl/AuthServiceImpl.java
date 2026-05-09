package com.zosh.job.service.impl;

import com.zosh.job.domain.UserRole;
import com.zosh.job.domain.UserStatus;
import com.zosh.job.mapper.UserMapper;
import com.zosh.job.modal.User;
import com.zosh.job.payload.AuthResponse;
import com.zosh.job.payload.LoginRequest;
import com.zosh.job.payload.SignupRequest;
import com.zosh.job.repository.UserRepo;
import com.zosh.job.security.CustomUserDetailsService;
import com.zosh.job.security.JwtProvider;
import com.zosh.job.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public AuthResponse signup(SignupRequest request) throws Exception {
        if (userRepo.existsByEmail(request.getEmail())){
            throw new Exception("User already exists " + request.getEmail());
        }
        if (request.getRole() == UserRole.ROLE_ADMIN){
            throw new Exception("Cannot self-register as a role admin");
        }
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .phone(request.getPhone())
                .status(UserStatus.ACTIVE)
                .lastLogin(LocalDateTime.now())
                .build();

        User savedUser = userRepo.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication, savedUser.getId());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setTitle("welcome " + savedUser.getFullName());
        authResponse.setMessage("Register successfully");
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(savedUser));

        return authResponse;
    }

    @Override
    public AuthResponse login(LoginRequest request) throws Exception {
        Authentication authentication = authenticate(
                request.getEmail(), request.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepo.findByEmail(request.getEmail());
        String jwt = jwtProvider.generateToken(authentication, user.getId());
        user.setLastLogin(LocalDateTime.now());
        userRepo.save(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setTitle("welcome back " + user.getFullName());
        authResponse.setMessage("Login successfully");
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(user));

        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        if (userDetails == null){
            throw new Exception("User not found with email " + email);
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new Exception("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
    }
}
