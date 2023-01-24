package com.example.demo;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.UUID;

public class GameCreateDTO {
    private UUID id;
    private String paramsOfGame;
    private String factoryId;
    private String gameNameInUserLanguage;

    public GameCreateDTO(UUID id, Game game, String isDefault){
        this.paramsOfGame = isDefault;
        this.id = id;
        this.factoryId = game.getFactoryId();
    }
    public void setGameNameInUserLanguage(String gameNameInUserLanguage) {
        this.gameNameInUserLanguage = gameNameInUserLanguage;
    }
    public UUID getId() {
        return id;
    }
    public String getParamsOfGame() {
        return paramsOfGame;
    }
    public String getFactoryId() {
        return factoryId;
    }
    public String getGameNameInUserLanguage() {
        return gameNameInUserLanguage;
    }
}
