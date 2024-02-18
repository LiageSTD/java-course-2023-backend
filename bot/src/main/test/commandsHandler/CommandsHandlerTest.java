package commandsHandler;

import edu.java.bot.repository.UserRepository;
import edu.java.bot.service.commandsHandler.CommandsHandler;
import edu.java.bot.service.commandsHandler.commands.ListHandler;
import edu.java.bot.service.commandsHandler.commands.StartHandler;
import edu.java.bot.service.commandsHandler.commands.TrackHandler;
import edu.java.bot.service.commandsHandler.commands.UnsupportedCommand;
import edu.java.bot.service.commandsHandler.commands.UntrackHandler;
import edu.java.bot.service.commandsHandler.filters.UnregisteredUserFilter;
import edu.java.bot.service.linksHandler.LinksHandler;
import edu.java.bot.service.linksHandler.parsers.GitHubLinkParser;
import edu.java.bot.service.linksHandler.parsers.StackOverFlowLinkParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import edu.java.bot.service.UserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CommandsHandlerTest {
    private CommandsHandler commandsHandler;
    @Mock
    Update update;

    @BeforeEach
    void init() {
        UserService userService = new UserService(new UserRepository());
        LinksHandler linksHandler = new LinksHandler(List.of(
            new GitHubLinkParser(),
            new StackOverFlowLinkParser()
        ));
        commandsHandler = new CommandsHandler(
            List.of(new UnregisteredUserFilter(userService)),
            List.of(
                new ListHandler(userService),
                new StartHandler(userService),
                new TrackHandler(userService, linksHandler),
                new UntrackHandler(userService, linksHandler)
            ),
            new UnsupportedCommand()
        );
        Mockito.when(update.getMessage()).thenReturn(mock(Message.class));
        Mockito.when(update.getMessage().getChat()).thenReturn(mock(Chat.class));
        Mockito.when(update.getMessage().getChat().getId()).thenReturn(1L);
    }

    @Test
    void checkNonRegisteredUser() {
        Mockito.when(update.getMessage().getText()).thenReturn("/list");
        SendMessage reply = commandsHandler.handleTheMessage(update);
        Assertions.assertEquals(reply.getText(), "You have to register firstly. Use /start");
    }

    @Test
    void checkNonRegisteredUserToRegister() {
        Mockito.when(update.getMessage().getText()).thenReturn("/start");
        SendMessage reply = commandsHandler.handleTheMessage(update);
        Assertions.assertEquals(reply.getText(), "Hi! Use /help to see what i can!");
    }

    @Test
    void checkUnknownCommand() {
        Update update1 = new Update();
        Message message = new Message();
        message.setText("/start");
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        update1.setMessage(message);
        commandsHandler.handleTheMessage(update1);
        Mockito.when(update.getMessage().getText()).thenReturn("/skibiditoilet");
        SendMessage reply = commandsHandler.handleTheMessage(update);
        Assertions.assertEquals(reply.getText(), "Unsupported command");
    }
}
