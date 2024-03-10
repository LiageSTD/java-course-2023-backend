package edu.java.client;

import edu.java.dto.bot.request.LinkUpdate;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
public class BotClientImpl {
    private final WebClient client;

    public void updateLink(LinkUpdate linkUpdate) {
        client.post()
            .uri("/updates")
            .bodyValue(linkUpdate)
            .retrieve()
            .bodyToMono(Void.class)
            .block();
    }

}
