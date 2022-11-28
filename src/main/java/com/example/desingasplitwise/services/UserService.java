package com.example.desingasplitwise.services;

import com.example.desingasplitwise.models.User;
import com.example.desingasplitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public User registerUser(String name,String password){
        User user=new User();
        user.setName(name);
        user.setHashedPassword(password);
        return this.userRepository.save(user);
    }
    public User updateProfile(String name,String password,Long userId){
        Optional<User> user =this.userRepository.findById(userId);
        User user1=user.get();
        user1.setName(name);
        user1.setHashedPassword(password);
        return this.userRepository.save(user1);
    }
}
