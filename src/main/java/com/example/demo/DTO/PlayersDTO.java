package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "players")
public class PlayersDTO {
    @Id
    @JsonProperty
    private UUID id;
    @Column(name = "game_id")
    @JsonProperty
    private UUID gameId;
    public PlayersDTO(UUID id, UUID gameId) {
        this.id = id;
        this.gameId = gameId;
    }
    public PlayersDTO() {};
    @Override
    public String toString() {
        return String.format(
                "PlayerCreateDTO[id='%s', game_id='%s']",
                id, gameId);
    }

    public UUID getId() {
        return id;
    }

    public UUID getGameId() {
        return gameId;
    }
}
