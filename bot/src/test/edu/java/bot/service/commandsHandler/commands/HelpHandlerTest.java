package edu.java.bot.service.commandsHandler.commands;

import edu.java.bot.service.BotService;
import edu.java.bot.service.commandsHandler.commands.HelpHandler;
import edu.java.bot.service.commandsHandler.commands.ListHandler;
import edu.java.bot.service.commandsHandler.commands.StartHandler;
import edu.java.bot.service.commandsHandler.commands.TrackHandler;
import edu.java.bot.service.commandsHandler.commands.UntrackHandler;
import edu.java.bot.service.linksHandler.LinksHandler;
import edu.java.bot.service.linksHandler.parsers.GitHubLinkParser;
import edu.java.bot.service.linksHandler.parsers.StackOverFlowLinkParser;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class HelpHandlerTest {
    HelpHandler helpHandler;
    @Mock
    Update update;
    @Mock
    private BotService botService;

    @BeforeEach
    void init() {
        LinksHandler linksHandler = new LinksHandler(List.of(
            new GitHubLinkParser(),
            new StackOverFlowLinkParser()
        ));
        helpHandler = new HelpHandler(List.of(
            new ListHandler(botService),
            new StartHandler(botService),
            new TrackHandler(botService, linksHandler),
            new UntrackHandler(botService, linksHandler)
        ));
        Mockito.when(update.getMessage()).thenReturn(mock(Message.class));
        Mockito.when(update.getMessage().getChat()).thenReturn(mock(Chat.class));
        Mockito.when(update.getMessage().getChat().getId()).thenReturn(1L);
    }

    @Test
    void testHelpHandle() {
        Assertions.assertEquals(
            helpHandler.handle(update).getText(), "List of available commands:\n" +
                "/list: Show all of your tracked links\n" +
                "/start: Registration of user & greetings\n" +
                "/track: Add the current link to the tracked ones\n" +
                "/untrack: Removes the current link from the tracked ones\n");
        Assertions.assertEquals(
            helpHandler.handle(update).getChatId(), "1");

    }
}
