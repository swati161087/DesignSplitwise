package com.example.desingasplitwise.controllers;

import com.example.desingasplitwise.dtos.AddExpenceDto;
import com.example.desingasplitwise.models.Expence;
import com.example.desingasplitwise.services.ExpenceService;
import org.springframework.stereotype.Controller;

@Controller
public class ExpenceController {
    private ExpenceService expenceService;
    ExpenceController(ExpenceService expenceService){
        this.expenceService=expenceService;
    }
    public Expence addExpenceToGroup(AddExpenceDto addExpenceDto){
        return this.expenceService.addExpence(addExpenceDto.getAmount(),
                addExpenceDto.getUserId(),
                addExpenceDto.getParticipants(),
                addExpenceDto.getWhoPaid(),
                addExpenceDto.getWhoOwes(),
                addExpenceDto.getCurrency(),
                addExpenceDto.getDescription(),
                addExpenceDto.getGroupId()
        );
    }
}
