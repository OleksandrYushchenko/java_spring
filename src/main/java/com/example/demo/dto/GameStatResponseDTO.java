package com.example.demo.dto;

public class GameStatResponseDTO {
    private String factoryId;
    private int onGoing;
    private int setup;
    private int terminated;

    public int getTerminated() {
        return terminated;
    }
    public String getFactoryId() {
        return factoryId;
    }

    public int getOnGoing() {
        return onGoing;
    }

    public int getSetup() {
        return setup;
    }
}
