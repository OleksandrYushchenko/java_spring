package com.example.demo.repositories;

import com.example.demo.entities.Privilege;
import com.example.demo.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    Privilege findByName(String name);
}
