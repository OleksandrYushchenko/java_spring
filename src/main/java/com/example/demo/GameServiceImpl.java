package com.example.demo;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class GameServiceImpl implements GameService{
    private final Map<UUID, GameCreateDTO> listOfGames = new HashMap<>();
    @Override
    public Collection<String> getGameIdentifiers() {
        TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();
        return List.of(ticTacToeGameFactory.getGameId());
    }
    @Override
    public GameCreateDTO createGame(GameCreationParams params) throws Exception {
        GameFactory gameFactory = switch (params.getType()) {
            case "tictactoe" -> new TicTacToeGameFactory();
            case "taquin" -> new TaquinGameFactory();
            case "connectfour" -> new ConnectFourGameFactory();
            default -> throw new Exception("Incorrect choise!!!");
        };
        Game game = gameFactory.createGame(params.getNbPlayers(), params.getSizeBoard());
        UUID id = UUID.randomUUID();
        GameCreateDTO newGame = new GameCreateDTO(id, game);
        listOfGames.put(id, newGame);
        System.out.println(listOfGames);
        return newGame;
    }
    @Override
    public GameCreateDTO getGame(@PathVariable UUID gameId) {
        // TODO - actually get and return game with id 'gameId'
        return listOfGames.get(gameId);
    }
}
