package com.example.desingasplitwise.dtos;

import com.example.desingasplitwise.models.Currency;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
public class AddExpenceDto {
    private double amount;
    private Long userId;
    private List<Long> participants;
    private Map<Long, Double> whoPaid;
    private Map<Long,Double> whoOwes;
    private Currency currency;
    private String description;
    private Long groupId;
}
