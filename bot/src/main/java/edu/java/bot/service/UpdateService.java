package edu.java.bot.service;

import edu.java.dto.bot.request.LinkUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateService {
    private final TeleBot teleBot;

    public void sendUpdate(LinkUpdateRequest update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(update.getUrl() + "-> " + update.getDescription());
        for (long id : update.getTgChatIds()) {
            sendMessage.setChatId(String.valueOf(id));
            try {
                teleBot.execute(sendMessage);
            } catch (TelegramApiException e) {
                log.error("Unable to send message to {} {} due to {}", id, sendMessage.getText(), e.getMessage());
            }
        }
    }
}
