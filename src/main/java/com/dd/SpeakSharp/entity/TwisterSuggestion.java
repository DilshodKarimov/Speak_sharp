package com.dd.SpeakSharp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "twister_suggestions")
public class TwisterSuggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long chatId;

    @Column(nullable = false, length = 1000)
    private String text;

    private Status status;

    public TwisterSuggestion(){};

    public TwisterSuggestion(Long userId, Long chatId, String text, Status status){
        this.userId = userId;
        this.chatId = chatId;
        this.text = text;
        this.status = status;
    }

}
