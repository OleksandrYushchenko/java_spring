package com.example.demo.DTO;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GameCreateDTORepository extends CrudRepository<GameCreateDTO, UUID> {

}
