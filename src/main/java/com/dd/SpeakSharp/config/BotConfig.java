package com.dd.SpeakSharp.config;

import com.dd.SpeakSharp.telegram.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@RequiredArgsConstructor
public class BotConfig {
    private final TelegramBot telegramBot;

    @Bean
    public TelegramBotsApi telegramBotsApi() throws Exception {

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);

        api.registerBot(telegramBot);

        return api;
    }
}
