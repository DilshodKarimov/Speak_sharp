package com.dd.SpeakSharp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_twisters")
public class UserTwister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long twisterId;

    private LocalDateTime createdAt;

    public UserTwister(){};

    public UserTwister(Long userId, Long twisterId){
        this.userId = userId;
        this.twisterId = twisterId;
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
