package com.example.demo.controller;

import com.example.demo.dto.GameDTO;
import com.example.demo.controller.service.GameService;
import com.example.demo.dto.GameStatResponseDTO;
import com.example.demo.params.GameCreationParams;
import com.example.demo.params.MoveParams;
import fr.le_campus_numerique.square_games.engine.InconsistentGameDefinitionException;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class GameController {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
    @Autowired
    private GameService gameService;
    @PostMapping("/games")
    // TODO - creating a new game(typeOfgame, playerNb, Boarder, size, (header.accept-language))
    public GameDTO createGame(@RequestBody GameCreationParams params) throws Exception {
        return gameService.createGame(params);
    }
    @GetMapping("/games/{gameId}")
    public GameDTO getGame(@PathVariable UUID gameId) throws InconsistentGameDefinitionException {
    // TODO - actually get and return game with id 'gameId'
        return gameService.getGame(gameId);
    }
    @GetMapping("/catalog")
//    @Secured(Roles.ROlE_USER)
    // TODO - get and return prepared list for /catalog
    public List<Map> getListOfGames() {
        return gameService.getListOfGames();
    }
    @PutMapping("/games/{gameId}/move")
    public GameDTO gameMove(@PathVariable UUID gameId, @RequestBody @Valid MoveParams params) throws InvalidPositionException, InconsistentGameDefinitionException {
        return gameService.makeMove(gameId, params);
    }
    @DeleteMapping("/games/{gameId}/delete")
    public void deleteGame(@PathVariable UUID gameId){
        gameService.deleteGame(gameId);
    }

    @PostMapping("/pushstat/{gameId}")
    public Object pushStat(@PathVariable UUID gameId) throws InconsistentGameDefinitionException {
        return  gameService.pushStat(getGame(gameId));
    }
    @GetMapping("/get/{factoryName}")
    public GameStatResponseDTO getStat(@PathVariable String factoryName){
        String url = "http://localhost:6666/get/" + factoryName;
        return new RestTemplate().getForObject(url, GameStatResponseDTO.class);
    }
}
