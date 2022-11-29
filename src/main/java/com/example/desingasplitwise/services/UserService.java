package com.example.desingasplitwise.services;

import com.example.desingasplitwise.models.User;
import com.example.desingasplitwise.repositories.UserRepository;
import com.example.desingasplitwise.strategies.EncodePasswordStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private EncodePasswordStrategy encodePasswordStrategy;
    @Autowired
    UserService(UserRepository userRepository,EncodePasswordStrategy encodePasswordStrategy){
        this.userRepository=userRepository;
        this.encodePasswordStrategy=encodePasswordStrategy;
    }
    public User registerUser(String name,String password){
        User user=new User();
        user.setName(name);
        user.setCreatedAt(new Date());
        user.setHashedPassword(this.encodePasswordStrategy.encodePassword(password));
        return this.userRepository.save(user);
    }
    public User updateProfile(String name,String password,Long userId){
        Optional<User> user =this.userRepository.findById(userId);
        User user1=user.get();
        user1.setName(name);
        user1.setHashedPassword(password);
        return this.userRepository.save(user1);
    }
    public String login(Long userId,String passowrd){
        Optional<User> user =this.userRepository.findById(userId);
        User user1=user.get();
        if(this.encodePasswordStrategy.matchesPassword(passowrd,user1.getHashedPassword())){
            return "bearer token";
        }
        else {
            throw new RuntimeException("woring creds");
        }
    }
}
