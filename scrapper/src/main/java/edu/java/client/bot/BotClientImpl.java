package edu.java.client.bot;

import edu.java.dto.bot.request.LinkUpdateRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BotClientImpl implements BotClient {
    private final Sender sender;

    @Override
    public void sendUpdate(LinkUpdateRequest linkUpdateRequest) {
        sender.sendUpdate(linkUpdateRequest);
    }
}
