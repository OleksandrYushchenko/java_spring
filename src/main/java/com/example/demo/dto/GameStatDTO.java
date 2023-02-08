package com.example.demo.dto;

import fr.le_campus_numerique.square_games.engine.GameStatus;

import java.util.UUID;

public class GameStatDTO {
    private String factoryId;
    private UUID gameId;
    private GameStatus gameStatus;
    private UUID playerA;
    private UUID playerB;
    public GameStatDTO(GameDTO gameDTO) {
        this.factoryId = gameDTO.getFactoryId();
        this.gameId = gameDTO.getGameId();
        this.gameStatus = gameDTO.getGame().getStatus();
        this.playerA = gameDTO.getPlayerA();
        this.playerB = gameDTO.getPlayerB();
    }
    public UUID getGameId() {
        return gameId;
    }
    public String getFactoryId() {
        return factoryId;
    }
    public UUID getPlayerB() {
        return playerB;
    }
    public UUID getPlayerA() {
        return playerA;
    }
    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
