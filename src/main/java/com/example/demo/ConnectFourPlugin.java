package com.example.demo;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Objects;

@Component
public class ConnectFourPlugin implements GamePlugin{
    @Value("${game.connectFour.default-player-count}")
    private int defaultPlayerNb;
    @Value("${game.connectFour.default-board-size}")
    private int defaultBoardSize;
    @Autowired
    MessageSource messageSource;
    @Override
    public String getName(Locale locale) {
        return  messageSource.getMessage("game.connectFour.name", null, locale);
    }
    @Override
    public GameFactory getGameFactory() {
        return new ConnectFourGameFactory();
    }

    @Override
    public int getDefaultPlayerNb() {
        return defaultPlayerNb;
    }

    @Override
    public int getDefaultBoardSize() {
        return defaultBoardSize;
    }
}
