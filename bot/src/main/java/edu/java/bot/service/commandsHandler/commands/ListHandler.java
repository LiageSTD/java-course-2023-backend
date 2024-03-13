package edu.java.bot.service.commandsHandler.commands;

import edu.java.bot.service.UserService;
import edu.java.bot.service.linksHandler.links.Link;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

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
        String lolCodeStyleError = "List of your tracked links:\n";
        sb.append(lolCodeStyleError);
        int counter = 1;
        for (Link link : userService.getUserLinks(id)) {
            sb.append(counter++).append(". ").append("https://").append(link.getHost()).append(link.getPath()).append("\n");
        }
        if (sb.length() == lolCodeStyleError.length()) {
            reply.setText("There's no links to show :(");
        } else {
            reply.setText(sb.toString());
        }
        return reply;
    }
}
