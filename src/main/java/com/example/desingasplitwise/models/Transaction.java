package com.example.desingasplitwise.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private User fromUser;
    private User toUser;
    private double amount;
}
