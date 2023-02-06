package com.example.demo.DTO;

import com.example.demo.entities.User;

public class UserDTO {
    private String userName;
    public UserDTO(User user){
        this.userName = user.getUsername();
    }
    public String getUserName() {
        return userName;
    }
}
