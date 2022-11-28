package com.example.desingasplitwise.strategies;

import com.example.desingasplitwise.models.ExpenceOwe;
import com.example.desingasplitwise.models.ExpencePaidBy;
import com.example.desingasplitwise.models.Transaction;

import java.util.List;

public interface SettelUpStrategy {
    List<Transaction> settelUp(List<ExpenceOwe> expenceOweList, List<ExpencePaidBy> expencePaidByList);
}
