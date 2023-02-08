package com.example.demo.dto;

import com.example.demo.entities.GameEntity;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.Map;
import java.util.UUID;

public class GameDTO {
    private final UUID gameId;
    private final String paramsOfGame;
    private final String factoryId;
    private final String userLanguage;
    private final int boardSize;
    private final Map<CellPosition, Token> board;
    private final UUID playerA;
    private final UUID playerB;
    private final String name;
//    @JsonIgnore
    private fr.le_campus_numerique.square_games.engine.Game game;

    public GameDTO(GameEntity game, fr.le_campus_numerique.square_games.engine.Game gameEng){
        this.gameId = game.getGameId();
        this.paramsOfGame = game.getParamsOfGame();
        this.factoryId = game.getFactoryId();
        this.userLanguage = game.getUserLanguage();
        this.boardSize = game.getBoardSize();
        this.board = game.getBoard();
        this.playerA = game.getPlayerA();
        this.playerB = game.getPlayerB();
        this.game = gameEng;
        this.name = game.getGameName();
    }
    public UUID getGameId() {
        return gameId;
    }
    public String getParamsOfGame() {
        return paramsOfGame;
    }
    public String getFactoryId() {
        return factoryId;
    }
    public String getUserLanguage() {
        return userLanguage;
    }
    public int getBoardSize() {
        return boardSize;
    }
    public Map<CellPosition, Token> getBoard() {
        return board;
    }
    public UUID getPlayerA() {
        return playerA;
    }
    public UUID getPlayerB() {
        return playerB;
    }
    public fr.le_campus_numerique.square_games.engine.Game getGame() {
        return game;
    }

    public String getName() {
        return name;
    }
}
