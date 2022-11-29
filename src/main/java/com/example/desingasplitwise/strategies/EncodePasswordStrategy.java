package com.example.desingasplitwise.strategies;

public interface EncodePasswordStrategy {
    public String encodePassword(String password);
    public boolean matchesPassword(String realPassword,String hashedPassword);
}
