package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UsersDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    @JsonProperty
    private UUID userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "role")
    @JsonProperty
    private String role;
    @Column(name = "create")
    @JsonProperty
    private boolean create;
    @Column(name = "read")
    @JsonProperty
    private boolean read;
    @Column(name = "update")
    @JsonProperty
    private boolean update;
    @Column(name = "delete")
    @JsonProperty
    private boolean delete;
    public UsersDTO(UUID userId, String userName, String role){
        this.userId = userId;
        this.userName = userName;
        this.role = role;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
    public UsersDTO() {}
}
