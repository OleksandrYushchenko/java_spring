package com.example.demo.dto;

import com.example.demo.entities.UserEntity;

public class UserDTO {
    private String userName;
    public UserDTO(UserEntity user){
        this.userName = user.getUsername();
    }
    public String getUserName() {
        return userName;
    }
}
