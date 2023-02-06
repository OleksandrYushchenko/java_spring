package com.example.demo.repositories;

import com.example.demo.entities.GameEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GameRepository extends CrudRepository<GameEntity, UUID> {
    GameEntity findByGameId (UUID gameId);
    @Transactional
    String removeByGameId(UUID gameId);
}
