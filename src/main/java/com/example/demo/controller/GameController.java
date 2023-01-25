package com.example.demo.controller;

import com.example.demo.DTO.GameCreateDTO;
import com.example.demo.params.GameCreationParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class GameController {
    @Autowired
    private GameService gameService;
    @GetMapping("/identifiers")
    public void getGameIdentifiers(){
        gameService.getGameIdentifiers();
    }
    @PostMapping("/games")
    public GameCreateDTO createGame(@RequestBody GameCreationParams params) throws Exception {
        return gameService.createGame(params);
    }
    @GetMapping("/games/{gameId}")
    public GameCreateDTO getGame(@PathVariable UUID gameId) {
    // TODO - actually get and return game with id 'gameId'
        return gameService.getGame(gameId);
    }
}
