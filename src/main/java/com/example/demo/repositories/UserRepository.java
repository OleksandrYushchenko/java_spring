package com.example.demo.repositories;

import com.example.demo.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    UserDetails findByName(String name);
}
