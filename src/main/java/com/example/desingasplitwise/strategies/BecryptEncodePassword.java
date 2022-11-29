package com.example.desingasplitwise.strategies;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BecryptEncodePassword implements EncodePasswordStrategy{
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Override
    public String encodePassword(String password) {

       return  passwordEncoder.encode(password);
    }

    @Override
    public boolean matchesPassword(String realPassword, String hashedPassword) {
       return passwordEncoder.matches(realPassword,hashedPassword);
    }
}
