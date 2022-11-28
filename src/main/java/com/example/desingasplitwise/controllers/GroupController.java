package com.example.desingasplitwise.controllers;

import com.example.desingasplitwise.dtos.AddGroupDto;
import com.example.desingasplitwise.models.Group;
import com.example.desingasplitwise.services.GroupService;
import com.example.desingasplitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {
    private GroupService groupService;
    @Autowired
    GroupController(GroupService groupService){
        this.groupService=groupService;
    }
    public Group addGroup(AddGroupDto addGroupDto){
        return this.groupService.addGroup(addGroupDto.getGroupName(), addGroupDto.getUserId(), addGroupDto.getParticipants());
    }
}
