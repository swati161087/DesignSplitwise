package com.example.desingasplitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GroupExpence extends BaseModel{
    @ManyToOne
    private Group group;
    @ManyToOne
    private Expence expence;
}
