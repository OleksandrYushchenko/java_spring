package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "moves")
public class TokensEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "game_id")
    @JsonProperty
    private UUID gameId;
    @Column(name = "player_Id")
    private UUID ownerId;
    @Column(name = "token_name")
    private String tokenName;
    @Column(name = "position_X")
    private int X;
    @Column(name = "position_Y")
    private int Y;
    public TokensEntity(UUID gameId, UUID ownerId, String tokenName, int x, int y) {
        this.gameId = gameId;
        this.ownerId = ownerId;
        this.tokenName = tokenName;
        this.X = x;
        this.Y = y;
    }
    public TokensEntity() {
    }
    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }
    public String getTokenName() {
        return tokenName;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public Long getId() {
        return id;
    }

    public UUID getGameId() {
        return gameId;
    }
}
