package com.tamjayz.securityOTPDemo.service.impl;

import com.tamjayz.securityOTPDemo.dto.LoginRequest;
import com.tamjayz.securityOTPDemo.dto.Request;
import com.tamjayz.securityOTPDemo.dto.Response;
import com.tamjayz.securityOTPDemo.dto.UserInfo;
import com.tamjayz.securityOTPDemo.model.Users;
import com.tamjayz.securityOTPDemo.repository.UserRepository;
import com.tamjayz.securityOTPDemo.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<Response> signUp(Request request) {
        // check if the user exist return error.
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body(Response.builder()
                            .statusCode(400)
                            .responseMessage("Attempt to save duplicate user.")
                    .build());
        }

        Users user = Users.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        Users saveUser = userRepository.save(user);
        return ResponseEntity.ok(Response.builder()
                        .statusCode(200)
                        .responseMessage("Success")
                        .userinfo(modelMapper.map(saveUser, UserInfo.class))
                .build());
    }

    @Override
    public ResponseEntity<Response> login(LoginRequest request) {
        return null;
    }

    @Override
    public Response sendOtp() {
        return null;
    }

    @Override
    public Response validateOtp() {
        return null;
    }

    @Override
    public Response resetPassword() {
        return null;
    }

    @Override
    public Response changePassword() {
        return null;
    }
}
