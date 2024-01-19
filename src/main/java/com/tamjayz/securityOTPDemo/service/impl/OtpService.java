package com.tamjayz.securityOTPDemo.service.impl;

import com.tamjayz.securityOTPDemo.dto.*;
import com.tamjayz.securityOTPDemo.model.Otp;
import com.tamjayz.securityOTPDemo.repository.OtpRepository;
import com.tamjayz.securityOTPDemo.utils.AppUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class OtpService {

    private final OtpRepository otpRepository;
    private  final EmailService emailService;


    public Response sendOtp(OtpRequest request){

        String otp = AppUtils.generateOtp();
        otpRepository.save(Otp.builder()
                        .email(request.getEmail())
                        .otp(otp)
                        .expiredAt(LocalDateTime.now().plusMinutes(2))
                .build());
        emailService.sendMail(EmailDetails.builder()
                        .subject("Do Not Disclose!")
                        .recipient(request.getEmail())
                        .messageBody("This organization has sent you an otp, This otp expires in 2minutes " + otp)
                .build());
        return Response.builder()
                .statusCode(200)
                .responseMessage("Successfully sent")
                .build();
    }

    public Response validateOtp(OtpValidationRequest request){
        /**
         * check if the user actually send an otp
         * check if the otp hasn't expires
         * check if the otp is correct
         * check if he has otp in the system
         * */

        Otp existOtp = otpRepository.findByEmail(request.getEmail());
        if (existOtp != null){
            otpRepository.delete(existOtp);
        }
        
        Otp otp = otpRepository.findByEmail(request.getEmail());
        if (otp == null) {
            return Response.builder()
                    .statusCode(400)
                    .responseMessage("You have not sent an OTP!")
                    .build();
        }

        if (otp.getExpiredAt().isBefore(LocalDateTime.now())){
            return Response.builder()
                    .statusCode(400)
                    .responseMessage("Expired OTP")
                    .build();
        }

        if (!otp.getOtp().equals(request.getOtp())){
            return Response.builder()
                    .statusCode(400)
                    .responseMessage("Invalid Otp")
                    .build();
        }

        return Response.builder()
                .statusCode(200)
                .responseMessage("Success")
                .otpResponse(OtpResponse.builder()
                        .isOtpValid(true)
                        .build())
                .build();
    }
}
