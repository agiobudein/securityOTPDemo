package com.tamjayz.securityOTPDemo.controller;

import com.tamjayz.securityOTPDemo.dto.OtpRequest;
import com.tamjayz.securityOTPDemo.dto.OtpValidationRequest;
import com.tamjayz.securityOTPDemo.dto.Request;
import com.tamjayz.securityOTPDemo.dto.Response;
import com.tamjayz.securityOTPDemo.service.impl.OtpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/otp")
@AllArgsConstructor
public class OtpController {

    private OtpService otpService;

    @PostMapping("sendOtp")
    public Response sendOtp(@RequestBody OtpRequest request){
        return otpService.sendOtp(request);
    }

    @PostMapping("validateOtp")
    public Response validateOtp(@RequestBody OtpValidationRequest request){
        return otpService.validateOtp(request);
    }
}
