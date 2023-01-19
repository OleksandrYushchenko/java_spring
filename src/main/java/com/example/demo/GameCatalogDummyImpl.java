package com.example.demo;

import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class GameCatalogDummyImpl implements GameCatalog{
    fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();
    @Override
    public Collection<String> getGameIdentifiers() {
        return List.of(ticTacToeGameFactory.getId());
    }
}
