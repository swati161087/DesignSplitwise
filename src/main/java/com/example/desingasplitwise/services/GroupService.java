package com.example.desingasplitwise.services;

import com.example.desingasplitwise.models.Group;
import com.example.desingasplitwise.models.User;
import com.example.desingasplitwise.repositories.GroupRepository;
import com.example.desingasplitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    GroupRepository groupRepository;
    UserRepository userRepository;
    @Autowired
    GroupService(GroupRepository groupRepository,UserRepository userRepository){
        this.groupRepository=groupRepository;
        this.userRepository=userRepository;
    }
    public Group addGroup(String name, Long userId, List<Long> participants){
        Optional<User> fetchedUser=this.userRepository.findById(userId);
        List<User> fetchedParticipants=this.userRepository.findAllById(participants);
        User user=fetchedUser.get();
        Group group=new Group();
        group.setName(name);
        group.setCreatedBy(user);
        group.setAdmins(List.of(user));
        group.setParticipants(fetchedParticipants);
        group.setCreatedAt(new Date());
        return this.groupRepository.save(group);
    }
}
