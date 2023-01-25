package com.example.demo.controller;

import com.example.demo.DTO.GameCreateDTO;
import com.example.demo.params.GameCreationParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class GameController {
    @Autowired
    private GameService gameService;
    @PostMapping("/games")
    // TODO - creating a new game(typeOfgame, playerNb, Boarder, size, (header.accept-language))
    public GameCreateDTO createGame(@RequestBody GameCreationParams params) throws Exception {
        return gameService.createGame(params);
    }
    @GetMapping("/games/{gameId}")
    public GameCreateDTO getGame(@PathVariable UUID gameId) {
    // TODO - actually get and return game with id 'gameId'
        return gameService.getGame(gameId);
    }
    @GetMapping("/catalog")
    // TODO - get and return prepared list for /catalog
    public List<Map> getListOfGames() {
        return gameService.getListOfGames();
    }
}
