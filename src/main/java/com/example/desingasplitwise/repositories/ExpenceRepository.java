package com.example.desingasplitwise.repositories;

import com.example.desingasplitwise.models.Expence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenceRepository extends JpaRepository<Expence,Long> {
    @Override
    Expence save(Expence expence);
}