package com.example.desingasplitwise.services;

import com.example.desingasplitwise.models.*;
import com.example.desingasplitwise.models.Currency;
import com.example.desingasplitwise.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ExpenceService {
    private UserRepository userRepository;
    private ExpenceRepository expenceRepository;
    private ExpenceOweRepository expenceOweRepository;
    private ExpencePaidByRepository expencePaidByRepository;
    private GroupRepository groupRepository;
    private GroupExpenceRepository groupExpenceRepository;
    @Autowired
    ExpenceService(ExpenceRepository expenceRepository,
                   ExpenceOweRepository expenceOweRepository,
                   ExpencePaidByRepository expencePaidByRepository,
                   UserRepository userRepository,
                   GroupRepository groupRepository,
                   GroupExpenceRepository groupExpenceRepository
                                            ){
        this.expenceOweRepository=expenceOweRepository;
        this.expencePaidByRepository=expencePaidByRepository;
        this.expenceRepository=expenceRepository;
        this.userRepository=userRepository;
        this.groupRepository=groupRepository;
        this.groupExpenceRepository=groupExpenceRepository;
    }
    public Expence addExpence(double amount, Long userId, List<Long> participants, Map<Long, Double> whoPaid, Map<Long,Double> whoOwes,
                              Currency currency,
                              String description,
                              Long groupId){
        Expence expence=new Expence();
        Optional<User> user=this.userRepository.findById(userId);
        expence.setAmount(amount);
        expence.setCreatedBy(user.get());
        expence.setCurrency(currency);
        expence.setDescription(description);
        List<ExpencePaidBy> expencePaidByList=new ArrayList<>();
        List<ExpenceOwe> expenceOwesList=new ArrayList<>();
        List<User> participantsList=new ArrayList<>();
        for(Long participantId:participants){
            Optional<User> participant=this.userRepository.findById(participantId);
            participantsList.add(participant.get());
        }
        for(Map.Entry e:whoPaid.entrySet()){
            Optional<User> userPaid=this.userRepository.findById((Long) e.getKey());
            ExpencePaidBy expencePaidBy=new ExpencePaidBy();
            expencePaidBy.setUser(userPaid.get());
            expencePaidBy.setAmount((Double) e.getValue());
            expencePaidBy.setCreatedAt(new Date());

            expencePaidByList.add(this.expencePaidByRepository.save(expencePaidBy));

        }
        for(Map.Entry e:whoOwes.entrySet()){
            Optional<User> userOwe=this.userRepository.findById((Long) e.getKey());
            ExpenceOwe expenceOwe=new ExpenceOwe();
            expenceOwe.setAmount((Double) e.getValue());
            expenceOwe.setUser(userOwe.get());
            expenceOwesList.add(this.expenceOweRepository.save(expenceOwe));
        }
        expence.setExpenceOwes(expenceOwesList);
        expence.setExpencePaidByList(expencePaidByList);
        expence.setParticipants(participantsList);
        Optional<Group> groupOptional=this.groupRepository.findById(groupId);
        Group group=groupOptional.get();
        GroupExpence groupExpence=new GroupExpence();

        Expence savedExpence= this.expenceRepository.save(expence);
        groupExpence.setExpence(savedExpence);
        groupExpence.setGroup(group);
        this.groupExpenceRepository.save(groupExpence);
        return savedExpence;
    }
}
