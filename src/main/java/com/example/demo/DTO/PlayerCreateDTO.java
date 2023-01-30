package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;
import jakarta.persistence.*;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "players")
public class PlayerCreateDTO {
    @Id
    private UUID id;
    public PlayerCreateDTO(UUID id) {
        this.id = id;
    }

    public PlayerCreateDTO() {

    }
}
