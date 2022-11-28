package com.example.desingasplitwise.repositories;

import com.example.desingasplitwise.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    @Override
    Group save(Group group);
}
