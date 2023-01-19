package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GameController {
    @GetMapping("/uuid")
    public String createGame(@RequestBody GameCreationParams params) {
        // TODO - actually create a new game
        return UUID.randomUUID().toString();
    }
}
