package com.example.desingasplitwise.controllers;

import com.example.desingasplitwise.models.Transaction;
import com.example.desingasplitwise.services.SettelUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class SettelUpController {
    @Autowired
    private SettelUpService settelUpService;

    public List<Transaction> settelGroup(Long groupId){
        return this.settelUpService.settelUpGroup(groupId);
    }
}
