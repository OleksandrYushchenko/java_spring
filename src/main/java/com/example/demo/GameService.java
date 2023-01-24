package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.UUID;

public interface GameService {
    Collection<String> getGameIdentifiers();
    GameCreateDTO createGame(@RequestBody GameCreationParams params) throws Exception;
    GameCreateDTO getGame(@PathVariable UUID gameId);
}
