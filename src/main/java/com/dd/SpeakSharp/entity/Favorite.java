package com.dd.SpeakSharp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "favorites",
    uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "twister_id"}))
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "twister_id")
    private Twister twister;

    private LocalDateTime createdAt;


    public Favorite(){};

    public Favorite(Long userId, Twister twister){
        this.userId = userId;
        this.twister = twister;
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}
