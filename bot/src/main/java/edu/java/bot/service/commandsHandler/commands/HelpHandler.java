package edu.java.bot.service.commandsHandler.commands;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.List;

@Component
@AllArgsConstructor
public class HelpHandler implements SupportedCommand {
    @Autowired
    private List<? extends SupportedCommand> commandList;

    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "List of bot`s commands";
    }

    @Override
    public SendMessage handle(Update update) {
        SendMessage reply = new SendMessage();

        StringBuilder stringOfCommands = new StringBuilder();
        stringOfCommands.append("List of available commands:\n");

        for (SupportedCommand cmd : commandList) {
            stringOfCommands.append(cmd.command()).append(": ").append(cmd.description()).append("\n");
        }

        reply.setChatId(String.valueOf(update.getMessage().getChat().getId()));
        reply.setText(stringOfCommands.toString());
        return reply;
    }

}
