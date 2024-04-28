package edu.java.bot.client.scrapper;

import edu.java.dto.scrapper.request.AddLinkRequest;
import edu.java.dto.scrapper.request.RemoveLinkRequest;
import edu.java.dto.scrapper.response.LinkResponse;
import edu.java.dto.scrapper.response.ListLinksResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface ScrapperClient {
    @PostExchange("tg-chat/{id}")
    void registerChat(@PathVariable("id") Long id);

    @DeleteExchange("tg-chat/{id}")
    void deleteChat(@PathVariable("id") Long id);

    @GetExchange("tg-chat/{id}")
    boolean isChatRegistered(@PathVariable("id") Long id);

    @GetExchange("links")
    ListLinksResponse getAllLinks(@RequestHeader("Tg-Chat-Id") Long chatId);

    @PostExchange("links")
    LinkResponse trackLink(@RequestHeader("Tg-Chat-Id") Long chatId, @RequestBody AddLinkRequest request);

    @DeleteExchange("links")
    LinkResponse deleteLink(@RequestHeader("Tg-Chat-Id") Long id, @RequestBody RemoveLinkRequest link);

}
