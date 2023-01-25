package com.example.demo.DTO;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.UUID;

public class GameCreateDTO {
    private UUID id;
    private String paramsOfGame;
    private String factoryId;
    private String userLanguage;
    private String gameName;
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
    public Map<CellPosition, Token> getBoard() {
        return board;
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
