package com.example.demo.DTO;

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
