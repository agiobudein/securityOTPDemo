package com.tamjayz.securityOTPDemo.repository;

import com.tamjayz.securityOTPDemo.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    Otp findByEmail(String email);
}
