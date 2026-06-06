package com.dd.SpeakSharp.service;

import com.dd.SpeakSharp.entity.Twister;
import com.dd.SpeakSharp.entity.UserTwister;
import com.dd.SpeakSharp.repository.TwisterRepository;
import com.dd.SpeakSharp.repository.UserTwisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TwisterService {

    private final TwisterRepository twisterRepository;
    private final UserTwisterRepository userTwisterRepository;

    public Twister getNewTwister(Long userId){
         List<Twister> allTwister = twisterRepository.findAll();

         List<Long> usedIds = userTwisterRepository.findByUserId(userId)
                 .stream()
                 .map(UserTwister::getTwisterId)
                 .toList();

         List<Twister> available = allTwister.stream()
                 .filter(t -> !usedIds.contains(t.getId()))
                 .toList();

         if(available.isEmpty()){
             return null;
         }

         System.out.println(available);

         Twister randomTwister = available.get(new Random().nextInt(available.size()));

        UserTwister userTwister = new UserTwister(userId, randomTwister.getId());

        userTwisterRepository.save(userTwister);

        return  randomTwister;
    }

    public List<Twister> getUserTwisters(Long userId){
        List<Twister> allTwister = twisterRepository.findAll();

        List<Long> usedIds = userTwisterRepository.findByUserId(userId)
                .stream()
                .map(UserTwister::getTwisterId)
                .toList();

        List<Twister> available = allTwister.stream()
                .filter(t -> !usedIds.contains(t.getId()))
                .toList();

        return available;
    }

}
