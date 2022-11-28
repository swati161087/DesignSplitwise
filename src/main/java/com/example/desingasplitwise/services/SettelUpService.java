package com.example.desingasplitwise.services;

import com.example.desingasplitwise.models.*;
import com.example.desingasplitwise.repositories.ExpenceOweRepository;
import com.example.desingasplitwise.repositories.ExpencePaidByRepository;
import com.example.desingasplitwise.repositories.GroupExpenceRepository;
import com.example.desingasplitwise.repositories.GroupRepository;
import com.example.desingasplitwise.strategies.SettelUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SettelUpService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupExpenceRepository groupExpenceRepository;
    @Autowired
    private ExpenceOweRepository expenceOweRepository;
    @Autowired
    private ExpencePaidByRepository expencePaidByRepository;
    private SettelUpStrategy settelUpStrategy;
    @Autowired
    SettelUpService(@Qualifier("heapMethodToSettelup") SettelUpStrategy settelUpStrategy){
        this.settelUpStrategy=settelUpStrategy;
    }

    public List<Transaction> settelUpGroup(Long groupId){
       List<GroupExpence> groupExpenceList=this.groupExpenceRepository.findByGroupId(groupId);
        List<ExpenceOwe> expenceOweList=new ArrayList<>();
        List<ExpencePaidBy> expencePaidByList=new ArrayList<>();
      for(GroupExpence groupExpence:groupExpenceList){
          expenceOweList.addAll(this.expenceOweRepository.findAllByExpence(groupExpence.getExpence()));
          expencePaidByList.addAll(this.expencePaidByRepository.findAllByExpence(groupExpence.getExpence()));
      }

        return this.settelUpStrategy.settelUp(expenceOweList,expencePaidByList);
    }
}
