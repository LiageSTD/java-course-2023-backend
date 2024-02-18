package edu.java.bot.service.commandsHandler.commands;

import edu.java.bot.service.UserService;
import edu.java.bot.service.linksHandler.LinksHandler;
import edu.java.bot.service.linksHandler.links.Link;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@AllArgsConstructor
public class TrackHandler implements SupportedCommand {

    private final UserService userService;
    private final LinksHandler linksHandler;

    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return "Add the current link to the tracked ones";
    }

    @Override
    public SendMessage handle(Update update) {
        SendMessage reply = new SendMessage();
        Long id = update.getMessage().getChat().getId();
        reply.setChatId(String.valueOf(id));

        Link link = linksHandler.checkLink(update.getMessage().getText());
        if (link == null) {
            reply.setText("Incorrect link");
        } else {
            reply.setText("Link has been added");
            userService.addLinkToAUser(id, link);
        }
        return reply;
    }
}
