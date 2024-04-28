package commandsHandler.commands;

import edu.java.bot.service.BotService;
import edu.java.bot.service.commandsHandler.commands.ListHandler;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ListHandlerTest {
    @InjectMocks
    ListHandler listHandler;
    @Mock
    Update update;
    @Mock
    private BotService userService;

    @BeforeEach
    void init() {
        Mockito.when(update.getMessage()).thenReturn(mock(Message.class));
        Mockito.when(update.getMessage().getChat()).thenReturn(mock(Chat.class));
        Mockito.when(update.getMessage().getChat().getId()).thenReturn(1L);
    }

    @Test
    void TestNullHandle() {
        Mockito.when(userService.getLinks(1L)).thenReturn(new ArrayList<>());
        SendMessage reply = listHandler.handle(update);
        Assertions.assertEquals(
            listHandler.handle(update).getText(), "There's no links to show :(");
        Assertions.assertEquals(
            listHandler.handle(update).getChatId(), "1");

    }

    @Test
    void TestSomeHandle() {
        Mockito.when(userService.getLinks(1L))
            .thenReturn(List.of(
                    "https://github.com/somePath",
                    "https://stackoverflow.com/somePath"
                )
            );
        Assertions.assertEquals(
            listHandler.handle(update).getText(),
            """
                List of your tracked links:
                1. https://github.com/somePath
                2. https://stackoverflow.com/somePath
                """
        );
        Assertions.assertEquals(
            listHandler.handle(update).getChatId(), "1");

    }
}
