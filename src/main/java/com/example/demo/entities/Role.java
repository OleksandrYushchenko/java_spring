package com.example.demo.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;
    public Role (String name){
        this.name = name;
    }
    public Role() {
    }
    public Collection<Privilege> getPrivileges() {
        return privileges;
    }
    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }
    public Collection<User> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }
}
