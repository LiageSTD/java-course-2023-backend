package edu.java.bot.service.commandsHandler.commands;

import edu.java.bot.service.linksHandler.links.Link;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import edu.java.bot.service.UserService;

@Component
@AllArgsConstructor
public class ListHandler implements SupportedCommand {
    private final UserService userService;

    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return "Show all of your tracked links";
    }

    @Override
    public SendMessage handle(Update update) {
        SendMessage reply = new SendMessage();
        Long id = update.getMessage().getChat().getId();
        reply.setChatId(String.valueOf(id));
        StringBuilder sb = new StringBuilder();
        sb.append("List of your tracked links:\n");
        int counter = 1;
        for (Link link : userService.getUserLinks(id)) {
            sb.append(counter++).append(". ").append("https://").append(link.host()).append(link.path()).append("\n");
        }
        if (sb.length() == 28) {
            reply.setText("There's no links to show :(");
        } else {
            reply.setText(sb.toString());
        }
        return reply;
    }
}
