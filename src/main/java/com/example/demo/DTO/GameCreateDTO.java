package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.UUID;
@Entity
@Table(name = "games")
public class GameCreateDTO {
    @Id
    private UUID id;
    @Transient
    private String paramsOfGame;
    @Column(name = "typeOfGame")
    private String factoryId;
    @Column(name = "gameStatus")
    private String gameStatus;
    @JsonProperty
    @Transient
    private String userLanguage;
    @Transient
    private String gameName;
    @JsonIgnore
    @Transient
    private Game game;
    @JsonProperty
    @Column(name = "board_size")
    private int boardSize;
    @JsonProperty
    @Transient
    private Map<CellPosition, Token> board;
    public GameCreateDTO(UUID id, Game game, String isDefault){
        this.paramsOfGame = isDefault;
        this.id = id;
        this.factoryId = game.getFactoryId();
        this.game = game;
        this.boardSize = game.getBoardSize();
    }
    public GameCreateDTO() {
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
    public Game getGame() {
        return game;
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
    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }
}
