package com.dd.SpeakSharp.service;

import com.dd.SpeakSharp.dto.TopTwisterDTO;
import com.dd.SpeakSharp.repository.FavoriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TopService {
    private final FavoriteRepository favoriteRepository;

    public List<TopTwisterDTO> getTop10(){
        return favoriteRepository.getTopTwisters(PageRequest.of(0, 10));
    }
}
