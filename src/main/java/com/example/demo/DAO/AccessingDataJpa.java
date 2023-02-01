package com.example.demo.DAO;

import com.example.demo.DTO.GameCreateDTORepository;
import com.example.demo.DTO.TokensDTORepository;
import com.example.demo.DTO.PlayersDTORepository;
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
    private PlayersDTORepository playerCreateDTORepository;
    @Autowired
    private GameCreateDTORepository gameCreateDTORepository;
    @Autowired
    private TokensDTORepository tokensDTORepository;
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
        return tokensDTORepository.findAllByGameId(id).stream().map(el -> new TokenPosition<>(
                el.getOwnerId(),
                el.getTokenName(),
                el.getX(),
                el.getY()
        )).collect(Collectors.toList());
    }
}