package com.example.desingasplitwise.repositories;

import com.example.desingasplitwise.models.GroupExpence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupExpenceRepository extends JpaRepository<GroupExpence,Long> {
    List<GroupExpence> findByGroupId(Long groupId);

}
