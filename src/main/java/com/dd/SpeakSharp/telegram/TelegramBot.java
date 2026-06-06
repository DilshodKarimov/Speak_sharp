package com.dd.SpeakSharp.telegram;

import com.dd.SpeakSharp.entity.Twister;
import com.dd.SpeakSharp.entity.UserState;
import com.dd.SpeakSharp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.dd.SpeakSharp.telegram.Send.sendFavoritesPage;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final TwisterService twisterService;
    private final UserService userService;
    private final FavoriteService favoriteService;
    private final TopService topService;
    private final SuggestionService suggestionService;
    private final Map<Long, UserState> userStates = new ConcurrentHashMap<>();

    @Value("${telegram.bot.username}")
    private String username;

    @Value("${telegram.bot.token}")
    private String token;



    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onRegister() {
        super.onRegister();

        getMainMenu();
    }

    private ReplyKeyboardMarkup getMainMenu() {

        KeyboardRow row1 = new KeyboardRow();

        row1.add("🎲 Новая скороговорка\n");
        row1.add("⭐ Избранное");

        KeyboardRow row2 = new KeyboardRow();

        row2.add("➕ Предложить");
        row2.add("\uD83C\uDFC6 Топ");

        List<KeyboardRow> rows = new ArrayList<>();

        rows.add(row1);
        rows.add(row2);

        ReplyKeyboardMarkup keyboard =
                new ReplyKeyboardMarkup();

        keyboard.setKeyboard(rows);

        keyboard.setResizeKeyboard(true);

        return keyboard;
    }

    private ReplyKeyboardMarkup getCancel(){
        KeyboardRow row = new KeyboardRow();

        row.add("🎲 Отмена\n");

        ReplyKeyboardMarkup keyboard =
                new ReplyKeyboardMarkup();

        keyboard.setKeyboard(List.of(row));

        keyboard.setResizeKeyboard(true);

        return keyboard;
    }

    @Override
    public void onUpdateReceived(Update update) {


        if(update.hasCallbackQuery()){
            Long userId = update.getCallbackQuery().getFrom().getId();

            Long chatId = update.getCallbackQuery()
                    .getMessage().getChatId();

            String data = update.getCallbackQuery().getData();

            if(data.startsWith("fav:")){
                Long twisterId = Long.parseLong(data.split(":")[1]);

                favoriteService.addFavorite(userId, twisterId);

                sendMessage(chatId, "Добавлено в избранное ⭐");
            }

            if (data.startsWith("fav_page:")) {

                int page = Integer.parseInt(
                        data.split(":")[1]
                );

                Integer messageId = update.getCallbackQuery().getMessage().getMessageId();

                sendMessage(Send.sendFavoritesPage(userId, messageId, chatId, page, favoriteService.getFavorites(userId, page)));

            }

            if (data.startsWith("remove_fav:")) {

                Long twisterId = Long.parseLong(
                        data.split(":")[1]
                );

                favoriteService.removeFavorites(
                        userId,
                        twisterId
                );

                sendMessage(
                        chatId,
                        "Удалено из избранного ❌"
                );
            }

        }

        if (update.hasMessage() && update.getMessage().hasText()) {

            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            Long userId = update.getMessage().getFrom().getId();

            UserState userState = userStates.getOrDefault(userId, UserState.NONE);

            if(userState == UserState.WAITING_FOR_SUGGESTION){
                suggestionService.createSuggestion(userId, chatId, text);

                userStates.remove(userId);

                SendMessage sendMessage = new SendMessage();

                sendMessage.setReplyMarkup(getMainMenu());
                sendMessage.setText("Скороговорка отправлена на модерацию ✅");
                sendMessage.setChatId(chatId);

                sendMessage(sendMessage);

                return;
            }

            if (text.equals("/start")) {
                SendMessage sendMessage = new SendMessage();

                sendMessage.setChatId(chatId);
                sendMessage.setText("Привет! Жми /new чтобы получить скороговорку 😄");
                sendMessage.setReplyMarkup(getMainMenu());

                String username = update.getMessage().getFrom().getFirstName();

                if(userService.getUser(userId, username) == null){
                    userService.createUser(userId, username);
                }

                sendMessage(sendMessage);
            }

            if (text.equals("/new") || text.equals("🎲 Новая скороговорка")) {
                Twister twister = twisterService.getNewTwister(userId);
                if(twister == null){
                    sendMessage(chatId, "all is read!");
                    return;
                }
                SendMessage sendMessage = Send.sendTwister(chatId, twister);

                sendMessage(sendMessage);
            }

            if(text.equals("/getFavorites") || text.equals("⭐ Избранное")){
                SendMessage sendMessage = sendFavoritesPage(
                        userId,
                        chatId,
                        0,
                        favoriteService.getFavorites(userId, 0)
                );

                if(sendMessage == null){
                    sendMessage(chatId, "У вас нет избранных ⭐");
                }
                else{
                    sendMessage(sendMessage);
                }
            }

            if(text.equals("/Suggest") || text.equals("➕ Предложить")){
                userStates.put(userId, UserState.WAITING_FOR_SUGGESTION);

                SendMessage sendMessage = new SendMessage();

                sendMessage.setChatId(chatId);
                sendMessage.setText("Отправь свою скороговорку ✍️");
                sendMessage.setReplyMarkup(getCancel());

                sendMessage(sendMessage);
            }

            if(text.equals("/top") || text.equals("\uD83C\uDFC6 Топ")){
                sendMessage(chatId, Send.getTop10(topService.getTop10()));
            }
        }
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);

        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(EditMessageText edit) {
        try {
            execute(edit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void sendMessage(SendMessage sendMessage){
        try {
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}
