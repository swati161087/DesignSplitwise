package com.example.desingasplitwise.repositories;

import com.example.desingasplitwise.models.Expence;
import com.example.desingasplitwise.models.ExpenceOwe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenceOweRepository extends JpaRepository<ExpenceOwe,Long> {
    @Override
    ExpenceOwe save(ExpenceOwe expence);
    //List<ExpenceOwe> findExpenceOweByExpenceIn(List<Expence> expenceId);

    List<ExpenceOwe> findAllByExpence(Expence expense);
}
