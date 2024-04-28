package edu.java.client.bot;

import edu.java.dto.bot.request.LinkUpdateRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface BotClient {
    @PostExchange("api/updates")
    void sendUpdate(@NotNull @RequestBody LinkUpdateRequest linkUpdateRequest);
}
