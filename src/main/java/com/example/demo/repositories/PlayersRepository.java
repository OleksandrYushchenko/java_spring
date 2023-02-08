package com.example.demo.repositories;

import com.example.demo.entities.PlayerEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface PlayersRepository extends CrudRepository<PlayerEntity, UUID> {
    @Query("select players from PlayerEntity players where players.gameId = :id")
    List<PlayerEntity> findAllByGameId (@Param("id") UUID id);
    @Transactional
    void removeByGameId(UUID gameId);
}
