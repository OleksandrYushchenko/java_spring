package com.example.demo.controller;

import com.example.demo.DAO.AccessingDataJpa;
import com.example.demo.DTO.*;
import com.example.demo.params.GameCreationParams;
import com.example.demo.params.MoveParams;
import com.example.demo.plugin.GamePlugin;
import fr.le_campus_numerique.square_games.engine.Game;

import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;


@Service
public class GameServiceImpl implements GameService{
    @Autowired
    private AccessingDataJpa accessingDataJpaPlayers;
    @Autowired
    private GameMoveDTORepository gameMoveDTORepository;
    @Autowired
    private GameCreateDTORepository gameCreateDTORepository;
    @Autowired
    private PlayerCreateDTORepository playerCreateDTORepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private List<GamePlugin> gamePlugins;
    private final Map<UUID, GameCreateDTO> listOfGames = new HashMap<>();
    @Override
    public GameCreateDTO getGame(@PathVariable UUID gameId) {
        // TODO - actually get and return game with id 'gameId'

        return listOfGames.get(gameId);
    }
    public List<Map> getListOfGames() {
        return gamePlugins
                .stream()
                .map(plugin -> plugin
                        .getDataForGameCatalog(Locale.of(getUserLanguage(request))))
                .toList();
    }
    public String getUserLanguage(HttpServletRequest request) {
        return request.getHeader("accept-language");
    }
    @Override
    public GameCreateDTO createGame(GameCreationParams params){
        // TODO - Choosing game plugin in depending of params.typeOfGame
        GamePlugin gamePlugin = gamePlugins.stream()
                .filter(pl -> pl.getGameFactory().getGameId().equals(params.typeOfGame()))
                .findFirst()
                .orElse(null);
        // TODO - Creating game (with user params OR default)
        Game game = params.playerCount() == 0 || params.boardSize() == 0
                // TODO - default params
                ? Objects.requireNonNull(gamePlugin).getGameFactory().createGame(gamePlugin.getDefaultPlayerNb(), gamePlugin.getDefaultBoardSize())
                // TODO - user params
                : Objects.requireNonNull(gamePlugin).getGameFactory().createGame(params.playerCount(), params.boardSize());
        UUID id = UUID.randomUUID();
        GameCreateDTO newGame = new GameCreateDTO(id, game, params.playerCount() == 0 || params.boardSize() == 0 ? "default" : "entered by user");
        // TODO - Setter Name (translation)
        newGame.setGameName(
                getUserLanguage(request) != null
                    ? gamePlugin.getName(Locale.of(getUserLanguage(request)))
                    : gamePlugin.getName(Locale.getDefault())
        );
        // TODO - Setter language (user choose)
        newGame.setUserLanguage(
                getUserLanguage(request) != null
                        ? getUserLanguage(request)
                        : "default"
        );
        // TODO - setting game board
        newGame.setBoard(game);
        newGame.setGameStatus(newGame.getGame().getStatus().toString());
        listOfGames.put(id, newGame);
        // TODO - create row in table "games"
        gameCreateDTORepository.save(newGame);
        // TODO - create row's in table "players"
        playerCreateDTORepository.save(new PlayerCreateDTO(
                newGame.getGame().getPlayerIds().stream().findFirst().get(),
                newGame.getGameId()
        ));
        playerCreateDTORepository.save(new PlayerCreateDTO(
                newGame.getGame().getPlayerIds().stream().reduce((first, second) -> second).get(),
                newGame.getGameId()
        ));
        accessingDataJpaPlayers.getPlayersList(newGame.getGameId());
        accessingDataJpaPlayers.getBoardSize(newGame.getGameId());
        accessingDataJpaPlayers.getBoardTokens(newGame.getGameId());
        return newGame;
    }
    public GameCreateDTO moveToken(@PathVariable UUID gameId, MoveParams params) throws InvalidPositionException {
        GameCreateDTO game;
        game = listOfGames.get(gameId);
        game.getGame().getRemainingTokens().stream().findFirst().get().moveTo(params.position());
        // TODO - add row to table "moves"
        gameMoveDTORepository.save(new GameMoveDTO(
                gameId,
                game.getGame().getCurrentPlayerId(),
                game.getGameName(),
                params.position().x(),
                params.position().y()
                ));
        accessingDataJpaPlayers.getPlayersList(gameId);
        accessingDataJpaPlayers.getBoardTokens(gameId);
        return game;
    }
}
