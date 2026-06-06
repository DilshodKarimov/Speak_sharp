package com.dd.SpeakSharp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tongue_twisters")
public class Twister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String text;

    private Difficult difficult;

    private String username;

    private LocalDateTime createdAt;

    public Twister(String text){
        this.text = text;
    }
    public Twister(){}


    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
