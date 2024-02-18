package edu.java.bot.service.commandsHandler.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class UnsupportedCommand implements Command {

    @Override
    public String command() {
        return "/???";
    }

    @Override
    public String description() {
        return "Sends an error message to a user if he used unsupported message";
    }

    @Override
    public SendMessage handle(Update update) {
        SendMessage reply = new SendMessage();
        reply.setChatId(String.valueOf(update.getMessage().getChat().getId()));
        reply.setText("Unsupported command");
        return reply;
    }
}
