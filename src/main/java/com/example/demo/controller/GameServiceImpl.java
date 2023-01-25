package com.example.demo.controller;

import com.example.demo.DTO.GameCreateDTO;
import com.example.demo.params.GameCreationParams;
import com.example.demo.plugin.GamePlugin;
import fr.le_campus_numerique.square_games.engine.Game;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class GameServiceImpl implements GameService{
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private List<GamePlugin> gamePlugins;
    private final Map<UUID, GameCreateDTO> listOfGames = new HashMap<>();
    public String getUserLanguage(HttpServletRequest request) {
        return request.getHeader("accept-language");
    }
    @Override
    public GameCreateDTO createGame(GameCreationParams params){
        // Choosing game plugin in depending of params.typeOfGame
        GamePlugin gamePlugin = gamePlugins.stream()
                .filter(pl -> pl.getGameFactory().getGameId().equals(params.typeOfGame()))
                .findFirst()
                .orElse(null);
        // Creating game (with user params OR default)
        Game game = params.playerCount() == 0 || params.boardSize() == 0
                // default params
                ? Objects.requireNonNull(gamePlugin).getGameFactory().createGame(gamePlugin.getDefaultPlayerNb(), gamePlugin.getDefaultBoardSize())
                // user params
                : Objects.requireNonNull(gamePlugin).getGameFactory().createGame(params.playerCount(), params.boardSize());
        UUID id = UUID.randomUUID();
        GameCreateDTO newGame = new GameCreateDTO(id, game, params.playerCount() == 0 || params.boardSize() == 0 ? "default" : "entered by user");
        // Setter Name (translation)
        newGame.setGameName(
                getUserLanguage(request) != null
                    ? gamePlugin.getName(Locale.of(getUserLanguage(request)))
                    : gamePlugin.getName(Locale.getDefault())
        );
        // Setter language (user choose)
        newGame.setUserLanguage(
                getUserLanguage(request) != null
                        ? getUserLanguage(request)
                        : "default"
        );
        newGame.setBoard(game);
        listOfGames.put(id, newGame);
        return newGame;
    }
    @Override
    public GameCreateDTO getGame(@PathVariable UUID gameId) {
        // TODO - actually get and return game with id 'gameId'
        return listOfGames.get(gameId);
    }
    public List<Map> getListOfGames() {
        return gamePlugins
                .stream()
                .map(plugin -> plugin
                        .getDataForGameCatalog(Locale.of(getUserLanguage(request)))).toList();
    }
}
