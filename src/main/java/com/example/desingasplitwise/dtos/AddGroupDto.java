package com.example.desingasplitwise.dtos;

import com.example.desingasplitwise.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class AddGroupDto {
    private String groupName;
    private Long userId;
    List<Long> participants;
}
