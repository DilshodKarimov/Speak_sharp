package com.dd.SpeakSharp.telegram;

import com.dd.SpeakSharp.dto.TopTwisterDTO;
import com.dd.SpeakSharp.entity.Favorite;
import com.dd.SpeakSharp.entity.Twister;
import com.dd.SpeakSharp.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Send {

    public static SendMessage sendTwister(Long chatId, Twister twister){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());

        sendMessage.setText("#" + twister.getId() + "\n\n" + twister.getText());

        InlineKeyboardButton favoriteButton = new InlineKeyboardButton();

        favoriteButton.setText("⭐ Favorite");

        favoriteButton.setCallbackData("fav:" + twister.getId());

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        markup.setKeyboard(List.of(
                List.of(favoriteButton)
        ));

        sendMessage.setReplyMarkup(markup);

        return sendMessage;
    }

    public static SendMessage sendFavoritesPage(Long userId, Long chatId, int page, Page<Favorite> favorites){
        StringBuilder text =
                new StringBuilder("⭐ Favorites\n\n");
        List<List<InlineKeyboardButton>> rows =
                new ArrayList<>();

        if(favorites.getContent().isEmpty()){
            return null;
        }


        for (Favorite favorite : favorites.getContent()) {

            Twister twister =  favorite.getTwister();


            text.append("#")
                    .append(favorite.getTwister().getId())
                    .append("\n")
                    .append(favorite.getTwister().getText())
                    .append("\n\n");

            InlineKeyboardButton removeButton =
                    new InlineKeyboardButton();

            removeButton.setText(
                    "❌ Remove #" + twister.getId()
            );

            removeButton.setCallbackData(
                    "remove_fav:" + twister.getId()
            );

            rows.add(List.of(removeButton));
        }


        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> buttons = new ArrayList<>();

        if (page > 0) {
            InlineKeyboardButton prev =
                    new InlineKeyboardButton();

            prev.setText("⬅️");
            prev.setCallbackData(
                    "fav_page:" + (page - 1)
            );

            buttons.add(prev);
        }

        if (favorites.hasNext()) {
            InlineKeyboardButton next =
                    new InlineKeyboardButton();

            next.setText("➡️");
            next.setCallbackData(
                    "fav_page:" + (page + 1)
            );

            buttons.add(next);
        }
        if (!buttons.isEmpty()) {
            rows.add(List.copyOf(buttons));
        }

        markup.setKeyboard(rows);

        SendMessage message =
                new SendMessage();

        message.setChatId(chatId.toString());
        message.setText(text.toString());
        message.setReplyMarkup(markup);


        return message;
    }

    public static EditMessageText sendFavoritesPage(Long userId, Integer messageId,
                                                Long chatId, int page, Page<Favorite> favorites){
        StringBuilder text =
                new StringBuilder("⭐ Избранные\n\n");
        List<List<InlineKeyboardButton>> rows =
                new ArrayList<>();

        System.out.println(favorites.getContent().size());

        for (Favorite favorite : favorites.getContent()) {

            Twister twister =  favorite.getTwister();


            text.append("#")
                    .append(favorite.getTwister().getId())
                    .append("\n")
                    .append(favorite.getTwister().getText())
                    .append("\n\n");

            InlineKeyboardButton removeButton =
                    new InlineKeyboardButton();

            removeButton.setText(
                    "❌ Удалить #" + twister.getId()
            );

            removeButton.setCallbackData(
                    "remove_fav:" + twister.getId()
            );

            rows.add(List.of(removeButton));
        }


        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> buttons = new ArrayList<>();

        if (page > 0) {
            InlineKeyboardButton prev =
                    new InlineKeyboardButton();

            prev.setText("⬅️");
            prev.setCallbackData(
                    "fav_page:" + (page - 1)
            );

            buttons.add(prev);
        }

        if (favorites.hasNext()) {
            InlineKeyboardButton next =
                    new InlineKeyboardButton();

            next.setText("➡️");
            next.setCallbackData(
                    "fav_page:" + (page + 1)
            );

            buttons.add(next);
        }
        if (!buttons.isEmpty()) {
            rows.add(List.copyOf(buttons));
        }

        markup.setKeyboard(rows);

        EditMessageText edit = new EditMessageText();

        edit.setChatId(chatId);

        edit.setReplyMarkup(markup);

        edit.setMessageId(messageId);

        edit.setText(text.toString());

        return edit;
    }

    public static String getTop10(List<TopTwisterDTO> top){
        StringBuilder builder =
                new StringBuilder(
                        "🏆 Топ скороговорок\n\n"
                );

        int place = 1;

        for (TopTwisterDTO dto : top) {

            builder.append(place)
                    .append(". ")
                    .append(dto.getTwister().getText())
                    .append("\n");

            builder.append("⭐ ")
                    .append(dto.getFavoritesCount())
                    .append("\n\n");

            place++;
        }

        return builder.toString();
    }

}
