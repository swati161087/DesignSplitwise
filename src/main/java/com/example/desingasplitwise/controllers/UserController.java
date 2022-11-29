package com.example.desingasplitwise.controllers;

import com.example.desingasplitwise.dtos.RegisterUserDto;
import com.example.desingasplitwise.dtos.UpdateProfileDto;
import com.example.desingasplitwise.models.User;
import com.example.desingasplitwise.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;
    UserController(UserService userService){
        this.userService=userService;
    }
    public User registerUser(RegisterUserDto registerUserDto){
        return this.userService.registerUser(registerUserDto.getName(),registerUserDto.getPassword());
    }
    public User updateProfile(UpdateProfileDto updateProfileDto){
        return this.userService.updateProfile(updateProfileDto.getName(), updateProfileDto.getPassword(), updateProfileDto.getId());
    }
    public String login(Long userId,String password){
        return this.userService.login(userId, password);
    }
}
