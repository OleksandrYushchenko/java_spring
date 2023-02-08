package com.example.demo.repositories;

import com.example.demo.entities.GameEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface GameRepository extends CrudRepository<GameEntity, UUID> {
    GameEntity findByGameId (UUID gameId);
    @Transactional
    String removeByGameId(UUID gameId);
}
