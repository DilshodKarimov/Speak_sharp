package com.dd.SpeakSharp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "users")
public class User {
    @Id
    private Long id;
    private String username;
    private LocalDateTime createdAt;

    public User(){};

    public User(Long id, String username){
        this.id = id;
        this.username = username;
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
