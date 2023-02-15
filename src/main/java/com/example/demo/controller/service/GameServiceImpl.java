package com.example.demo.controller.service;

import com.example.demo.dao.AccessingDataJpa;
import com.example.demo.dto.*;
import com.example.demo.entities.GameEntity;
import com.example.demo.entities.PlayerEntity;
import com.example.demo.entities.TokensEntity;
import com.example.demo.params.GameCreationParams;
import com.example.demo.params.MoveParams;
import com.example.demo.plugins.GamePlugin;
import com.example.demo.repositories.GameRepository;
import com.example.demo.repositories.PlayersRepository;
import com.example.demo.repositories.TokensRepository;
import fr.le_campus_numerique.square_games.engine.*;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {
    private static Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);
    @Autowired
    private AccessingDataJpa accessingDataJpaPlayers;
    @Autowired
    private TokensRepository tokensRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayersRepository playersRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private List<GamePlugin> gamePlugins;
    private int count = 0;
    @Override
    public GameDTO getGame(@PathVariable UUID gameId) throws InconsistentGameDefinitionException {
        GamePlugin gamePlugin = gamePlugins.stream()
                .filter(pl -> pl.getGameFactory().getGameId().equals(accessingDataJpaPlayers.getTypeOfGame(gameId)))
                .findFirst()
                .orElse(null);
        GameFactory gameFactory = Objects.requireNonNull(gamePlugin).getGameFactory();
        fr.le_campus_numerique.square_games.engine.Game game = gameFactory.createGameWithPredefinedPlayerIds(
                        accessingDataJpaPlayers.getBoardSize(gameId),
                        accessingDataJpaPlayers.getPlayersList(gameId),
                        accessingDataJpaPlayers.getBoardTokens(gameId) ,
                        List.of()
                );
        GameEntity gameEntity = new GameEntity(gameId, game, "entered by user");
        gameEntity.setBoard(game);
        gameEntity.doStuff();
        gameEntity.setPlayerA(accessingDataJpaPlayers.getPlayersList(gameId).stream().findFirst().get());
        gameEntity.setPlayerB(accessingDataJpaPlayers.getPlayersList(gameId).stream().reduce((first, second) -> second).get());
        GameDTO gameDTO = new GameDTO(gameEntity, game);
        return gameDTO;
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
    public GameDTO createGame(GameCreationParams params){
        // TODO - Choosing game plugin in depending of params.typeOfGame
        GamePlugin gamePlugin = gamePlugins.stream()
                .filter(pl -> pl.getGameFactory().getGameId().equals(params.typeOfGame()))
                .findFirst()
                .orElse(null);
        // TODO - Creating game (with user params OR default)
        fr.le_campus_numerique.square_games.engine.Game game = params.playerCount() == 0 || params.boardSize() == 0
                // TODO - default params
                ? Objects.requireNonNull(gamePlugin).getGameFactory().createGame(gamePlugin.getDefaultPlayerNb(), gamePlugin.getDefaultBoardSize())
                // TODO - user params
                : Objects.requireNonNull(gamePlugin).getGameFactory().createGame(params.playerCount(), params.boardSize());
        UUID id = UUID.randomUUID();
        GameEntity newGame = new GameEntity(id, game, params.playerCount() == 0 || params.boardSize() == 0 ? "default" : "entered by user");
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
        // TODO - create row in table "games"
        gameRepository.save(newGame);
        // TODO - create row's in table "players"
        playersRepository.save(new PlayerEntity(
                newGame.getGame().getPlayerIds().stream().findFirst().get(),
                newGame.getGameId()
        ));
        playersRepository.save(new PlayerEntity(
                newGame.getGame().getPlayerIds().stream().reduce((first, second) -> second).get(),
                newGame.getGameId()
        ));
        GameDTO gameDTO = new GameDTO(newGame, game);
        return gameDTO;
    }
    public GameDTO makeMove(@PathVariable UUID gameId, MoveParams params) throws InvalidPositionException, InconsistentGameDefinitionException {
        UUID owner;
        GameDTO game;
        game = getGame(gameId);
        if (count == 0) {
            owner = game.getGame().getRemainingTokens().stream().findFirst().get().getOwnerId().get();
            count++;
        } else {
            owner = game.getGame().getRemainingTokens().stream().reduce((first, second) -> second).get().getOwnerId().get();
            count = 0;
        }
        game.getGame().getRemainingTokens().stream().findFirst().get().moveTo(params.position());
        String tokenName = game.getGame().getRemainingTokens().stream().findFirst().get().getName();
        // TODO - add row to table "moves"
        // TODO - save token
        tokensRepository.save(new TokensEntity(
                gameId,
                owner,
                tokenName,
                params.position().x(),
                params.position().y()
        ));

        return game;
    }
    @Override
    public String deleteGame(UUID gameId) {
        playersRepository.removeByGameId(gameId);
        gameRepository.removeByGameId(gameId);
        return "Game " + gameId + "where deleted";
    }
    @Override
    public GameStatResponseDTO pushStat(GameDTO game) {
        GameStatDTO gameStatDTO = new GameStatDTO(game);
        String url = "http://localhost:6666/push/" + game.getGameId().toString();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(url, gameStatDTO, GameStatResponseDTO.class).getBody();
    }
}
