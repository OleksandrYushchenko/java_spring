package com.example.demo.plugin;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.lang.NonNull;

import java.util.Locale;
import java.util.Map;

public interface GamePlugin{
    String getName(Locale locale);
    @NonNull GameFactory getGameFactory();
    int getDefaultPlayerNb();
    int getDefaultBoardSize();
    Map getDataForGameCatalog(Locale locale);
}
