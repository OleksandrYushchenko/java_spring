package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "moves")
public class TokensDTO {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
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
    public TokensDTO(UUID gameId, UUID ownerId, String tokenName, int x, int y) {
        this.gameId = gameId;
        this.ownerId = ownerId;
        this.tokenName = tokenName;
        this.X = x;
        this.Y = y;
    }
    public TokensDTO() {
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
}
