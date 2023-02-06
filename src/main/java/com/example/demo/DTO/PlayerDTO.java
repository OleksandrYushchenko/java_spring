package com.example.demo.DTO;

import com.example.demo.entities.PlayerEntity;

import java.util.UUID;

public class PlayerDTO {
    private final UUID id;
    private final UUID gameId;
    public PlayerDTO(PlayerEntity playerEntity) {
        this.id = playerEntity.getId();
        this.gameId = playerEntity.getGameId();
    }
}
