package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;
@Entity
@Table(name = "games")
public class GameCreateDTO {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "game_id")
    private UUID gameId;
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
    @Transient
    private UUID ownerId;
    @Transient
    private UUID playerA;
    @Transient
    private UUID playerB;
    public GameCreateDTO(UUID id, Game game, String isDefault){
        this.paramsOfGame = isDefault;
        this.gameId = id;
        this.factoryId = game.getFactoryId();
        this.game = game;
        this.boardSize = game.getBoardSize();
    }

    private static Logger LOGGER = LoggerFactory.getLogger(GameCreateDTO.class);
    public void doStuff() {
        LOGGER.trace("Message de niveau TRACE");
        LOGGER.debug("Message de niveau DEBUG");
        LOGGER.info("Message de niveau INFO");
        LOGGER.warn("Message de niveau WARN");
        LOGGER.error("Message de niveau WARN");
    }
    public GameCreateDTO() {
    }

    public void setPlayerA(UUID playerA) {
        this.playerA = playerA;
    }

    public void setPlayerB(UUID playerB) {
        this.playerB = playerB;
    }

    public UUID getPlayerA() {
        return playerA;
    }

    public UUID getPlayerB() {
        return playerB;
    }

    public int getBoardSize() {
        return boardSize;
    }
    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
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
    public UUID getGameId() {
        return gameId;
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
