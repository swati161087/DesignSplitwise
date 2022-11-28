package com.example.desingasplitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileDto {
    private String name;
    private String password;
    private Long id;
}
