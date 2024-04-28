package edu.java.bot.service.commandsHandler.commands;

import edu.java.bot.service.BotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@AllArgsConstructor
public class StartHandler implements SupportedCommand {
    BotService botService;

    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "Registration of user & greetings";
    }

    @Override
    public SendMessage handle(Update update) {
        SendMessage reply = new SendMessage();
        Long id = update.getMessage().getChat().getId();
        reply.setChatId(String.valueOf(id));
        if (botService.isChatRegistered(id)) {
            reply.setText("Hi, nice to meet you again");
        } else {
            botService.registerChat(id);
            reply.setText("Hi! Use /help to see what i can!");
        }
        return reply;
    }
}
