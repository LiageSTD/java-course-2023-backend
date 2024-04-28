package edu.java.client;

import edu.java.dto.bot.request.LinkUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
public class BotClientImpl {
    private final WebClient client;

    public void updateLink(LinkUpdateRequest linkUpdateRequest) {
        client.post()
            .uri("/updates")
            .bodyValue(linkUpdateRequest)
            .retrieve()
            .bodyToMono(Void.class)
            .block();
    }

}
