package com.example.demo;

public class GameCreationParams {
    String typeOfGame;
    int playerCount;
    int boardSize;
    public void GameCreationParams(String typeOfGame, int playerCount, int boardSize){
        this.typeOfGame = typeOfGame;
        this.playerCount = playerCount;
        this.boardSize = boardSize;
    }
}
