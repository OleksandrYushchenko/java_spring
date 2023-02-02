package com.example.demo.plugins;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class TaquinPlugin implements GamePlugin{
    @Value("${game.taquin.default-player-count}")
    private int defaultPlayerNb;
    @Value("${game.taquin.default-board-size}")
    private int defaultBoardSize;
    @Autowired
    private MessageSource messageSource;
    @Override
    public String getName(Locale locale) {
        return  messageSource.getMessage("game.taquin.name", null, locale);
    }
    @Override
    public GameFactory getGameFactory() {
        return new TaquinGameFactory();
    }
    @Override
    public int getDefaultPlayerNb() {
        return defaultPlayerNb;
    }
    @Override
    public int getDefaultBoardSize() {
        return defaultBoardSize;
    }

    @Override
    public Map<String, String> getDataForGameCatalog(Locale locale) {
        Map<String, String> res = new HashMap<>();
        res.put("Default board size", this.defaultBoardSize + "");
        res.put("Default number of players", this.defaultPlayerNb + "");
        res.put("Game name(user language)", this.getName(locale));
        return res;
    }
}
