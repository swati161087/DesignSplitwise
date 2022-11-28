package com.example.desingasplitwise.repositories;
import com.example.desingasplitwise.models.Expence;
import com.example.desingasplitwise.models.ExpenceOwe;
import com.example.desingasplitwise.models.ExpencePaidBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpencePaidByRepository extends JpaRepository<ExpencePaidBy,Long> {
    @Override
    ExpencePaidBy save(ExpencePaidBy expence);
   // List<ExpencePaidBy> findExpencePaidByExpenceIn(List<Long> expenceId);
    List<ExpencePaidBy> findAllByExpence(Expence expense);

}