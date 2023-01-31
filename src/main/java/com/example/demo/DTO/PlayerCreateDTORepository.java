package com.example.demo.DTO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
public interface PlayerCreateDTORepository extends CrudRepository<PlayerCreateDTO, UUID> {
    @Query("select players from PlayerCreateDTO players where players.gameId = :id")
    List<PlayerCreateDTO> findAllByGameId (@Param("id") UUID id);
}
