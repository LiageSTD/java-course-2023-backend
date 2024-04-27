package edu.java.client.bot;

import edu.java.dto.bot.request.LinkUpdate;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface Sender {
    @PostExchange("/updates")
    void sendUpdate(@RequestBody @NotNull LinkUpdate link);
}
