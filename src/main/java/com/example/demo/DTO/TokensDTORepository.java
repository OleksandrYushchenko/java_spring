package com.example.demo.DTO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.UUID;

public interface TokensDTORepository extends CrudRepository<TokensDTO, UUID> {
    @Query("select moves from TokensDTO moves where moves.gameId = :id")
    Collection<TokensDTO> findAllByGameId (@Param("id") UUID id);
}
