package com.example.demo.DTO;

import fr.le_campus_numerique.square_games.engine.TokenPosition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface GameMoveDTORepository extends CrudRepository<GameMoveDTO, UUID> {
    @Query("select moves from GameMoveDTO moves where moves.gameId = :id")
    Collection<GameMoveDTO> findAllByGameId (@Param("id") UUID id);
}
