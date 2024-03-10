package edu.java.bot.service.commandsHandler;

import edu.java.bot.service.commandsHandler.commands.Command;
import edu.java.bot.service.commandsHandler.commands.SupportedCommand;
import edu.java.bot.service.commandsHandler.commands.UnsupportedCommand;
import edu.java.bot.service.commandsHandler.filters.Filter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@AllArgsConstructor
public class CommandsHandler {

    private final List<? extends Filter> filters;
    private final List<? extends SupportedCommand> commands;
    private final UnsupportedCommand unsupportedCommand;

    public SendMessage handleTheMessage(Update update) {
        for (Filter filter : filters) {
            if (!filter.fits(update)) {
                return filter.handle(update);
            }
            for (Command command : commands) {
                if (command.supports(update)) {
                    return command.handle(update);
                }
            }
        }
        for (Command command : commands) {
            if (command.supports(update)) {
                return command.handle(update);
            }
        }
        return unsupportedCommand.handle(update);
    }

}
