package com.dd.SpeakSharp.service;

import com.dd.SpeakSharp.entity.Favorite;
import com.dd.SpeakSharp.entity.Twister;
import com.dd.SpeakSharp.repository.FavoriteRepository;
import com.dd.SpeakSharp.repository.TwisterRepository;
import com.dd.SpeakSharp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final TwisterRepository twisterRepository;
    private final FavoriteRepository favoriteRepository;

//    public FavoriteService(TwisterRepository twisterRepository, FavoriteRepository favoriteRepository){
//        this.twisterRepository = twisterRepository;
//        this.favoriteRepository = favoriteRepository;
//    }

    public Favorite addFavorite(Long userId, Long twisterId){
        Favorite favorite = new Favorite(userId, twisterRepository.findById(twisterId).orElse(null));
        return favoriteRepository.save(favorite);
    }

    public void removeFavorites(Long userId, Long twisterId){
        Favorite favorite = favoriteRepository.findByUserIdAndTwisterId(userId, twisterId).orElse(null);
        favoriteRepository.deleteById(favorite.getId());
    }

    public List<Twister> getFavorites(Long userId){
        List<Favorite> allId = favoriteRepository.findByUserId(userId);

        List<Twister> allTwister = new ArrayList<>();

        for(var x: allId){
            allTwister.add(x.getTwister());
        }

        return allTwister;
    }

    public Page<Favorite> getFavorites(Long userId, int page){
        System.out.println(userId);
        return favoriteRepository.findByUserId(userId, PageRequest.of(page, 1));
    }
}
