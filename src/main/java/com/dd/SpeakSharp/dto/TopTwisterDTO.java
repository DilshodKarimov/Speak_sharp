package com.dd.SpeakSharp.dto;

import com.dd.SpeakSharp.entity.Twister;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopTwisterDTO {
    private Twister twister;
    private Long favoritesCount;

    public TopTwisterDTO(Twister twister, Long favoritesCount){
        this.twister = twister;
        this.favoritesCount = favoritesCount;
    }

    public TopTwisterDTO(){}



}
