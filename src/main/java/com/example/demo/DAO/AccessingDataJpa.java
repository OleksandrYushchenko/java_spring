package com.example.demo.DAO;

import com.example.demo.DTO.GameCreateDTO;
import com.example.demo.DTO.GameCreateDTORepository;
import com.example.demo.DTO.GameMoveDTORepository;
import com.example.demo.DTO.PlayerCreateDTORepository;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.TokenPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AccessingDataJpa {
    @Autowired
    private PlayerCreateDTORepository playerCreateDTORepository;
    @Autowired
    private GameCreateDTORepository gameCreateDTORepository;
    @Autowired
    private GameMoveDTORepository gameMoveDTORepository;
    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpa.class);
    }
    public String getTypeOfGame(UUID id) {
        return gameCreateDTORepository.findByGameId(id).getFactoryId();
    }
    public int getBoardSize(UUID id){
        return gameCreateDTORepository.findByGameId(id).getBoardSize();
    }
    public List<UUID> getPlayersList(UUID id) {
        return playerCreateDTORepository.findAllByGameId(id).stream().map(el -> el.getId()).toList();
    }
    public Collection<TokenPosition<UUID>> getBoardTokens(UUID id){
        return gameMoveDTORepository.findAllByGameId(id).stream().map(el -> new TokenPosition<>(
                el.getOwnerId(),
                el.getTokenName(),
                el.getX(),
                el.getY()
        )).collect(Collectors.toList());
    }
}