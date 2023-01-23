package com.example.demo;
public record GameCreationParams(String typeOfGame, int playerCount, int boardSize){
    public String getType() {
        return typeOfGame;
    }
    public int getNbPlayers() {
        return playerCount;
    }
    public int getSizeBoard() {
        return boardSize;
    }
}
