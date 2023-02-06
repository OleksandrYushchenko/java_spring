package com.example.demo.controller;

import com.example.demo.DTO.GameCreateDTO;
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
    GameCreateDTO createGame(@RequestBody GameCreationParams params) throws Exception;
    GameCreateDTO getGame(@PathVariable UUID gameId) throws InconsistentGameDefinitionException;
    String getUserLanguage(HttpServletRequest request);
    List<Map> getListOfGames();
    GameCreateDTO makeMove(@PathVariable UUID gameId, MoveParams params) throws InvalidPositionException, InconsistentGameDefinitionException;
    void deleteGame(@PathVariable UUID gameId);
}
