package com.example.demo;

import fr.le_campus_numerique.square_games.engine.Game;

import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class GameServiceImpl implements GameService{
    @Autowired
    private TicTacToePlugin ticTacToePlugin;
    @Autowired
    private TaquinPlugin taquinPlugin;
    @Autowired
    private ConnectFourPlugin connectFourPlugin;
    private final Map<UUID, GameCreateDTO> listOfGames = new HashMap<>();
    @Override
    public Collection<String> getGameIdentifiers() {
        TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();
        return List.of(ticTacToeGameFactory.getGameId());
    }
    @Override
    public GameCreateDTO createGame(GameCreationParams params) throws Exception {
        GamePlugin gamePlugin = null;
        switch (params.typeOfGame()) {
            case "tictactoe" -> gamePlugin = ticTacToePlugin;
            case "15 puzzle" -> gamePlugin = taquinPlugin;
            case "connect4" -> gamePlugin = connectFourPlugin;
            default -> throw new Exception("Incorrect choise!!!");
        };
        Game game = params.playerCount() == 0 || params.boardSize() == 0
                ? gamePlugin.getGameFactory().createGame(gamePlugin.getDefaultPlayerNb(), gamePlugin.getDefaultBoardSize())
                : gamePlugin.getGameFactory().createGame(params.playerCount(), params.boardSize());
        UUID id = UUID.randomUUID();
        GameCreateDTO newGame = new GameCreateDTO(id, game, params.playerCount() == 0 || params.boardSize() == 0 ? "default" : "entered by user");
        if (params.language() != null) {
            newGame.setGameNameInUserLanguage(gamePlugin.getName(Locale.of(params.language())));
        } else {
            newGame.setGameNameInUserLanguage("language not selected in EN " + newGame.getFactoryId());
        }
        listOfGames.put(id, newGame);
        return newGame;
    }
    @Override
    public GameCreateDTO getGame(@PathVariable UUID gameId) {
        // TODO - actually get and return game with id 'gameId'
        return listOfGames.get(gameId);
    }
}
