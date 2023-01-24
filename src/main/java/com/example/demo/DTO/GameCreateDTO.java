package com.example.demo.DTO;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.UUID;

public class GameCreateDTO {
    private UUID id;
    private String paramsOfGame;
    private String factoryId;
    private String userLanguage;
    private String gameName;

    public GameCreateDTO(UUID id, Game game, String isDefault){
        this.paramsOfGame = isDefault;
        this.id = id;
        this.factoryId = game.getFactoryId();
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public void setUserLanguage(String userLanguage){
        this.userLanguage = userLanguage;
    }
    public String getUserLanguage() { return userLanguage; }
    public UUID getId() {
        return id;
    }
    public String getParamsOfGame() {
        return paramsOfGame;
    }
    public String getFactoryId() {
        return factoryId;
    }
    public String getGameName() {
        return gameName;
    }
}
