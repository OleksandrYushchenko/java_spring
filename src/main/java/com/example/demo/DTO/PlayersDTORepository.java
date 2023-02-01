package com.example.demo.DTO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;
public interface PlayersDTORepository extends CrudRepository<PlayersDTO, UUID> {
    @Query("select players from PlayersDTO players where players.gameId = :id")
    List<PlayersDTO> findAllByGameId (@Param("id") UUID id);
}
