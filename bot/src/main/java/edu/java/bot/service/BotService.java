package edu.java.bot.service;

import edu.java.bot.client.scrapper.ScrapperClient;
import edu.java.dto.scrapper.request.AddLinkRequest;
import edu.java.dto.scrapper.request.RemoveLinkRequest;
import jakarta.validation.constraints.NotNull;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BotService {
    private final ScrapperClient scrapperClient;

    public void registerChat(Long id) {
        scrapperClient.registerChat(id);
    }

    public void unregisterChat(Long id) {
        scrapperClient.deleteChat(id);
    }

    public boolean isChatRegistered(Long id) {
        return scrapperClient.isChatRegistered(id);
    }

    public void addLink(Long chatId, @NotNull String link) {
        AddLinkRequest a = new AddLinkRequest(link);
        scrapperClient.trackLink(chatId, new AddLinkRequest(link));
    }

    public void removeLink(Long chatId, String link) {
        scrapperClient.deleteLink(chatId, new RemoveLinkRequest(link));
    }

    public Collection<String> getLinks(long chatId) {
        return scrapperClient.getAllLinks(chatId).getLinkResponse().stream()
            .map(linkResponse -> "https://" + linkResponse.getUrl().getHost() + linkResponse.getUrl().getPath())
            .toList();
    }
}
