package com.example.demo.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<String> authorities;
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column
    private String name;
    @Column
    private String password;
    public UserEntity(String name, Long userId, String password){
        this.userId = userId;
        this.name = name;
        this.password = password;
    }
    public UserEntity() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities.stream().map(s -> new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return s;
            }
        }).toList();
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
