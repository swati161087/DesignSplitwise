package com.example.desingasplitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Expence extends BaseModel{
    private String description;
    @ManyToOne
    private User createdBy;
    @ManyToMany
    private List<User> participants;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private double amount;
    @OneToMany(mappedBy = "expence")
    List<ExpencePaidBy> expencePaidByList;
    @OneToMany(mappedBy = "expence")
    List<ExpenceOwe> expenceOwes;

}
