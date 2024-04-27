package edu.java.client.bot;

import edu.java.dto.bot.request.LinkUpdate;
import jakarta.validation.constraints.NotNull;

public interface BotClient {

    void sendUpdate(@NotNull LinkUpdate linkUpdate);
}
