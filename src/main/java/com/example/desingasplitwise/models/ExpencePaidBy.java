package com.example.desingasplitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ExpencePaidBy extends BaseModel{
    @ManyToOne
    private User user;
    @ManyToOne
    private Expence expence;
    private double amount;
}
