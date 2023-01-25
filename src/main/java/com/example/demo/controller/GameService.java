package com.example.demo.controller;

import com.example.demo.DTO.GameCreateDTO;
import com.example.demo.params.GameCreationParams;
import com.example.demo.plugin.GamePlugin;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface GameService {
    GameCreateDTO createGame(@RequestBody GameCreationParams params) throws Exception;
    GameCreateDTO getGame(@PathVariable UUID gameId);
    String getUserLanguage(HttpServletRequest request);
    List<Map> getListOfGames();
}
