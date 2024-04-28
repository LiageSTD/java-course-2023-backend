package edu.java.bot.service.commandsHandler.filters;

import edu.java.bot.service.BotService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
@AllArgsConstructor
public class UnregisteredUserFilter implements Filter {
    private final BotService botService;

    @Override
    public SendMessage handle(Update update) {
        SendMessage reply = new SendMessage();
        Long id = update.getMessage().getChat().getId();
        reply.setChatId(String.valueOf(id));
        reply.setText("You have to register firstly. Use /start");
        log.info("Message from unregistered user was filtered: " + id);
        return reply;
    }

    @Override
    public boolean fits(Update update) {
        Long id = update.getMessage().getChat().getId();
        return botService.isChatRegistered(id) || update.getMessage().getText().startsWith("/start");
    }
}
