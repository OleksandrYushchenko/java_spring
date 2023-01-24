package com.example.demo.plugin;

import fr.le_campus_numerique.square_games.engine.*;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TicTacToePlugin implements GamePlugin{
    @Value("${game.tictactoe.default-player-count}")
    private int defaultPlayerNb;
    @Value("${game.tictactoe.default-board-size}")
    private int defaultBoardSize;
    @Autowired
    private MessageSource messageSource;
    @Override
    public String getName(Locale locale) {
        return  messageSource.getMessage("game.tictactoe.name", null, locale);
    }
    @Override
    public GameFactory getGameFactory() {
        return new TicTacToeGameFactory();
    }
    public int getDefaultPlayerNb() {
        return defaultPlayerNb;
    }
    public int getDefaultBoardSize() {
        return defaultBoardSize;
    }
}
