package edu.java.bot.service;

import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.service.commandsHandler.CommandsHandler;
import edu.java.bot.service.commandsHandler.commands.Command;
import edu.java.bot.service.commandsHandler.commands.SupportedCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.List;

@Slf4j
@Component
public class TeleBot extends TelegramLongPollingBot {

    public TeleBot(
        ApplicationConfig applicationConfig,
        CommandsHandler commandsHandler,
        List<SupportedCommand> commands
    ) {
        this.applicationConfig = applicationConfig;
        this.commandsHandler = commandsHandler;
        this.commands = commands;

        addCommandsToMenu();
    }

    private final ApplicationConfig applicationConfig;
    private final CommandsHandler commandsHandler;
    List<SupportedCommand> commands;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            executeMessage(commandsHandler.handleTheMessage(update));
        }
    }

    private void executeMessage(SendMessage messageToSend) {
        try {
            execute(messageToSend);
            log.info("Message was send to: " + messageToSend.getChatId());
        } catch (TelegramApiException e) {
            log.error("Unable send message to: " + messageToSend.getChatId());
        }
    }

    public void addCommandsToMenu() {
        SetMyCommands setMyCommands = new SetMyCommands();

        setMyCommands.setCommands(commands.stream().map(Command::toApiCommand).toList());
        try {
            execute(setMyCommands);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return applicationConfig.telegramBotName();
    }

    @Override
    public String getBotToken() {
        return applicationConfig.telegramToken();
    }

}


