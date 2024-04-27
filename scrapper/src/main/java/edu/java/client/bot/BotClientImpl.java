package edu.java.client.bot;

import edu.java.dto.bot.request.LinkUpdate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BotClientImpl implements BotClient {
    private final Sender sender;

    @Override
    public void sendUpdate(LinkUpdate linkUpdate) {
        sender.sendUpdate(linkUpdate);
    }
}
