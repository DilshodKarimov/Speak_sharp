package com.dd.SpeakSharp.service;


import com.dd.SpeakSharp.entity.User;
import com.dd.SpeakSharp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(Long id, String name){
        User user = new User(id, name);

        return userRepository.save(user);
    }

    public User getUser(Long id, String name){
        return userRepository.findById(id).orElse(null);
    }

}
