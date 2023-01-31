package com.example.demo.DTO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface GameCreateDTORepository extends CrudRepository<GameCreateDTO, UUID> {
//    @Query("select games from GameCreateDTO games where games.gameId = :id")
    GameCreateDTO findByGameId ( UUID id);
}
