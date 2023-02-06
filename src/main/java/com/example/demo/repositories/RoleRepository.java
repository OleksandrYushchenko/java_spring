package com.example.demo.repositories;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
