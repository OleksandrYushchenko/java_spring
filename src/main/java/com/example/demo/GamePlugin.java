package com.example.demo;

import fr.le_campus_numerique.square_games.engine.GameFactory;

import java.util.Locale;

public interface GamePlugin{
    String getName(Locale locale);
    GameFactory getGameFactory();
    int getDefaultPlayerNb();
    int getDefaultBoardSize();
}
