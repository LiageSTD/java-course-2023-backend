package edu.java.client.bot;

import edu.java.dto.bot.request.LinkUpdate;
import org.springframework.web.service.annotation.PostExchange;

public interface BotClient {
    @PostExchange("/api/updates")
    void getUpdates(LinkUpdate linkUpdate);
}
