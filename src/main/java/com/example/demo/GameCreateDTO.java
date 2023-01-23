package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.le_campus_numerique.square_games.engine.Game;

import java.util.UUID;

public class GameCreateDTO {
    @JsonProperty
    private UUID id;
    @JsonProperty
    private Game game;
    public GameCreateDTO(UUID id, Game game){
        this.id = id;
        this.game = game;
    }
}
