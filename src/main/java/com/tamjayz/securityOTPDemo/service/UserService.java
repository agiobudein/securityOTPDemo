package com.tamjayz.securityOTPDemo.service;

import com.tamjayz.securityOTPDemo.dto.LoginRequest;
import com.tamjayz.securityOTPDemo.dto.Request;
import com.tamjayz.securityOTPDemo.dto.Response;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Response> signUp(Request request);
    ResponseEntity<Response> login(LoginRequest request);
    Response sendOtp();

    Response validateOtp();
    Response resetPassword();
    Response changePassword();
}
