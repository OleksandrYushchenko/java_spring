package com.example.demo.DTO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface GameCreateDTORepository extends CrudRepository<GameCreateDTO, UUID> {
    GameCreateDTO findByGameId ( UUID gameId);
    void deleteByGameId(UUID gameID);
}
