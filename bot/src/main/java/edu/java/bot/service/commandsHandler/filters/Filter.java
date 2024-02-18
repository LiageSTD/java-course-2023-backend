package edu.java.bot.service.commandsHandler.filters;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Filter {
    public SendMessage handle(Update update);
    public boolean fits(Update update);
}
