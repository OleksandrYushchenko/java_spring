package com.example.demo.DTO;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UsersDTORepository extends CrudRepository<UsersDTO, UUID> {
    UsersDTO findByUserName(String userName);
}
