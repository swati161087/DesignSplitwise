package com.example.desingasplitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group extends BaseModel{
    private String name;
    @ManyToMany
    private List<User> participants;
    @ManyToMany
    private List<User> admins;
    @ManyToOne
    private User createdBy;
    @OneToMany(mappedBy = "group")
    private List<GroupExpence> groupExpence;
}
