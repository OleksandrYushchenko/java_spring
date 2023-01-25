package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.Map;
import java.util.UUID;

public class GameCreateDTO {
    private final UUID id;
    private final String paramsOfGame;
    private final String factoryId;
    @JsonProperty
    private String userLanguage;
    private String gameName;
    @JsonProperty
    private Map<CellPosition, Token> board;
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

    public void setBoard(Game game) {
        this.board = game.getBoard();
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
    public String getGameName() {
        return gameName;
    }
}
