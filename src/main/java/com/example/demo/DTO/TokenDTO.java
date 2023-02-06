package com.example.demo.DTO;

import com.example.demo.entities.TokensEntity;

import java.util.UUID;

public class TokenDTO {
    private final Long id;
    private final UUID gameId;
    private final UUID ownerId;
    private final String tokeName;
    private final int positionX;
    private final int positionY;

    public TokenDTO(TokensEntity tokensEntity) {
        this.id = tokensEntity.getId();
        this.gameId = tokensEntity.getGameId();
        this.ownerId = tokensEntity.getOwnerId();
        this.tokeName = tokensEntity.getTokenName();
        this.positionX = tokensEntity.getX();
        this.positionY = tokensEntity.getY();
    }
}
