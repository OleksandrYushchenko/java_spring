package com.example.demo.controller;

import com.example.demo.DAO.AccessingDataJpa;
import com.example.demo.DTO.*;
import com.example.demo.DTO.GameCreateDTO;
import com.example.demo.params.GameCreationParams;
import com.example.demo.params.MoveParams;
import com.example.demo.plugins.GamePlugin;
import fr.le_campus_numerique.square_games.engine.*;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;


@Service
public class GameServiceImpl implements GameService{
    private static Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);
    @Autowired
    private AccessingDataJpa accessingDataJpaPlayers;
    @Autowired
    private TokensDTORepository tokensDTORepository;
    @Autowired
    private GameCreateDTORepository gameCreateDTORepository;
    @Autowired
    private PlayersDTORepository playerCreateDTORepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private List<GamePlugin> gamePlugins;
//    private final Map<UUID, GameCreateDTO> listOfGames = new HashMap<>();
    @Override
    public GameCreateDTO getGame(@PathVariable UUID gameId) throws InconsistentGameDefinitionException {
        GamePlugin gamePlugin = gamePlugins.stream()
                .filter(pl -> pl.getGameFactory().getGameId().equals(accessingDataJpaPlayers.getTypeOfGame(gameId)))
                .findFirst()
                .orElse(null);
        GameFactory gameFactory = Objects.requireNonNull(gamePlugin).getGameFactory();
        Game game = gameFactory.createGame(
                        accessingDataJpaPlayers.getBoardSize(gameId),
                        accessingDataJpaPlayers.getPlayersList(gameId),
                        accessingDataJpaPlayers.getBoardTokens(gameId) ,
                        List.of()
                );
        GameCreateDTO gameCreateDTO = new GameCreateDTO(gameId, game, "entered by user");
        gameCreateDTO.setBoard(game);
        gameCreateDTO.doStuff();
        gameCreateDTO.setPlayerA(accessingDataJpaPlayers.getPlayersList(gameId).stream().findFirst().get());
        gameCreateDTO.setPlayerA(accessingDataJpaPlayers.getPlayersList(gameId).stream().reduce((first, second) -> second).get());
        System.out.println("OK");
        return gameCreateDTO;
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
        // TODO - create row in table "games"
        gameCreateDTORepository.save(newGame);
        // TODO - create row's in table "players"
        playerCreateDTORepository.save(new PlayersDTO(
                newGame.getGame().getPlayerIds().stream().findFirst().get(),
                newGame.getGameId()
        ));
        playerCreateDTORepository.save(new PlayersDTO(
                newGame.getGame().getPlayerIds().stream().reduce((first, second) -> second).get(),
                newGame.getGameId()
        ));
        LOGGER.debug("String", game);
        LOGGER.info("&&&");
        return newGame;
    }
    public GameCreateDTO makeMove(@PathVariable UUID gameId, MoveParams params) throws InvalidPositionException, InconsistentGameDefinitionException {
        GameCreateDTO game;
        game = getGame(gameId);
        UUID owner = game.getGame().getRemainingTokens().stream().findFirst().get().getOwnerId().get();
        String tokenName = game.getGame().getRemainingTokens().stream().findFirst().get().getName();
        // TODO - add row to table "moves"
        game.getGame().getRemainingTokens().stream().findFirst().get().moveTo(params.position());
        System.out.println(accessingDataJpaPlayers.getPlayersList(gameId));
        // TODO - save token
        tokensDTORepository.save(new TokensDTO(
                gameId,
                owner,
                tokenName,
                params.position().x(),
                params.position().y()
        ));
        return game;
    }
    @Override
    public void deleteGame(UUID gameId) {
        gameCreateDTORepository.deleteByGameId(gameId);
//        return "Game " + gameCreateDTORepository.findByGameId(gameId).getFactoryId() + " id:" + gameId + "deleted";
    }
}
