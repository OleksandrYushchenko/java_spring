package com.example.demo.repositories;

import com.example.demo.entities.TokensEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.UUID;

public interface TokensRepository extends CrudRepository<TokensEntity, UUID> {
    @Query("select moves from TokensEntity moves where moves.gameId = :id")
    Collection<TokensEntity> findAllByGameId (@Param("id") UUID id);
}
