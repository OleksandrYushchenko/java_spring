package com.example.demo.controller;

import com.example.demo.DTO.GameCreateDTO;
import com.example.demo.params.GameCreationParams;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface GameService {
    GameCreateDTO createGame(@RequestBody GameCreationParams params) throws Exception;
    GameCreateDTO getGame(@PathVariable UUID gameId);
}
