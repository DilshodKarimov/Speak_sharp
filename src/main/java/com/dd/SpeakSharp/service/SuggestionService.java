package com.dd.SpeakSharp.service;

import com.dd.SpeakSharp.entity.Status;
import com.dd.SpeakSharp.entity.TwisterSuggestion;
import com.dd.SpeakSharp.entity.UserState;
import com.dd.SpeakSharp.repository.TwisterRepository;
import com.dd.SpeakSharp.repository.TwisterSuggestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class SuggestionService {

    private final TwisterSuggestionRepository suggestionRepository;

    public void createSuggestion(Long userId, Long chatId, String text){
        TwisterSuggestion twisterSuggestion = new TwisterSuggestion(userId, chatId, text, Status.PENDING);

        suggestionRepository.save(twisterSuggestion);
    }
}
