package com.example.desingasplitwise.strategies;

import com.example.desingasplitwise.models.ExpenceOwe;
import com.example.desingasplitwise.models.ExpencePaidBy;
import com.example.desingasplitwise.models.Transaction;
import com.example.desingasplitwise.models.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("heapMethodToSettelup")
public class heapMethodToSettelup implements SettelUpStrategy{
    private class Record {
        User user;
        double amount;
        Record(User user,double amount){
            this.user=user;
            this.amount=amount;
        }
    }
    private class RecordComparatorAscending implements Comparator<Record> {
        
        @Override
        public int compare(Record o1, Record o2) {
            if(o1.amount>o2.amount)return 1;
            return -1;
        }
    }
    private class RecordComparatorDescending implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            if(o1.amount<o2.amount)return 1;
            return -1;
        }
    }
    @Override
    public List<Transaction> settelUp(List<ExpenceOwe> expenceOweList, List<ExpencePaidBy> expencePaidByList) {
        Map<User,Double> extraMoney =new HashMap<>();
        Map<Long,User> idMap=new HashMap<>();
        List<Transaction> transactions=new ArrayList<>();
        for(ExpencePaidBy expencePaidBy:expencePaidByList){
            User User=expencePaidBy.getUser();
            Long userId=User.getId();
            if(idMap.containsKey(userId)){
                Double amount= extraMoney.get(idMap.get(userId))+expencePaidBy.getAmount();
                extraMoney.replace(idMap.get(userId),amount);
            }
            else {
                idMap.put(userId,User);
                extraMoney.put(User,expencePaidBy.getAmount());
            }
        }
        for(ExpenceOwe expenceOwe:expenceOweList){
            User User=expenceOwe.getUser();
            Long userId=User.getId();
            if(idMap.containsKey(userId)){
                Double amount= extraMoney.get(idMap.get(userId))-expenceOwe.getAmount();
                extraMoney.replace(idMap.get(userId),amount);
            }
            else {
                idMap.put(userId,User);
                extraMoney.put(User,-expenceOwe.getAmount());
            }
        }

        PriorityQueue<Record> minHeap=new PriorityQueue<>(new RecordComparatorAscending());
        PriorityQueue<Record> maxHeap=new PriorityQueue<>(new RecordComparatorDescending());
        for(Map.Entry entry:extraMoney.entrySet()){
            double val=(double)entry.getValue();
            User user= (User) entry.getKey();
            if(val>0){
                maxHeap.offer(new Record(user,val));
            } else if (val<0) {
                minHeap.offer(new Record(user,val));
            }
        }
        while (!minHeap.isEmpty() && !maxHeap.isEmpty()){
            Record minRecord=minHeap.poll();
            Record maxRecord=maxHeap.poll();
            Transaction transaction=new Transaction();
            transaction.setFromUser(minRecord.user);
            transaction.setToUser(maxRecord.user);
            if(Math.abs(minRecord.amount)>Math.abs(maxRecord.amount)){
                minRecord.amount=minRecord.amount+maxRecord.amount;
                minHeap.offer(minRecord);
          
                transaction.setAmount(Math.abs(maxRecord.amount));
               
            }
            else if(Math.abs(minRecord.amount)<Math.abs(maxRecord.amount)){
                maxRecord.amount=minRecord.amount+maxRecord.amount;
                maxHeap.offer(maxRecord);
                transaction.setAmount(Math.abs(minRecord.amount));
            }
            else{
                transaction.setAmount(Math.abs(minRecord.amount));
            }
            transactions.add(transaction);
        }
        return transactions;
    }
}
