package com.example.demo.dto;

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
