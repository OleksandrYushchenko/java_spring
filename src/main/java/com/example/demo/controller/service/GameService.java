package com.example.demo.controller.service;

import com.example.demo.dto.GameDTO;
import com.example.demo.params.GameCreationParams;
import com.example.demo.params.MoveParams;
import fr.le_campus_numerique.square_games.engine.InconsistentGameDefinitionException;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface GameService {
    GameDTO createGame(@RequestBody GameCreationParams params) throws Exception;
    GameDTO getGame(@PathVariable UUID gameId) throws InconsistentGameDefinitionException;
    String getUserLanguage(HttpServletRequest request);
    List<Map> getListOfGames();
    GameDTO makeMove(@PathVariable UUID gameId, MoveParams params) throws InvalidPositionException, InconsistentGameDefinitionException;
    String deleteGame(@PathVariable UUID gameId);
    Object pushStat(GameDTO game);
}
